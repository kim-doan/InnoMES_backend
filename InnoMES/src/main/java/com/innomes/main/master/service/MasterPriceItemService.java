package com.innomes.main.master.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.innomes.main.master.dto.MasterPriceDTO;
import com.innomes.main.master.dto.MasterPriceItemDTO;
import com.innomes.main.master.model.MST110;
import com.innomes.main.master.model.MST151;
import com.innomes.main.master.param.MasterPriceItemParam;
import com.innomes.main.repository.MST110Repository;
import com.innomes.main.repository.MST151Repository;

@Service
@Transactional
public class MasterPriceItemService {
	
	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private int batchSize;
	
	@Autowired
	private MST151Repository mst151Repository;
	
	@Autowired
	private MST110Repository mst110Repository;
	
	//판매단가정보 메인그리드
	public Page<MasterPriceItemDTO> getSellItemList(MasterPriceItemParam masterPriceItemParam, Pageable pageable){
		
		//TPS002001 : 판매단가
		masterPriceItemParam.setPriceType("TPS002001");
		
		Page<MST110> output = mst110Repository.findAllPriceItem(masterPriceItemParam, pageable);
		List<MST110> content = output.getContent();
		List<MasterPriceItemDTO> dtoList = new ArrayList<MasterPriceItemDTO>();
		for(MST110 mst110 : content) {
			MasterPriceItemDTO dto = (MasterPriceItemDTO.builder()
					.itemId(mst110.getItemId())
					.itemCode(mst110.getItemCode())
					.itemName(mst110.getItemName())
					.itemType(mst110.getItemType())
					.build());
			
			dtoList.add(dto);
		}
		
		return new PageImpl<>(dtoList, pageable, output.getTotalElements());
	}
	
	//판매단가정보 서브그리드
	public Page<MasterPriceDTO> getSellPriceInfo(MasterPriceItemParam masterPriceItemParam, Pageable pageable){
		
		//TPS002001 : 판매단가
		masterPriceItemParam.setPriceType("TPS002001");
		
		Page<MST151> output = mst151Repository.findAllLike(masterPriceItemParam, pageable);
		List<MST151> content = output.getContent();
		List<MasterPriceDTO> dtoList = new ArrayList<MasterPriceDTO>();
		
		for(MST151 mst151 : content) {
			MasterPriceDTO dto = (MasterPriceDTO.builder()
					.itemId(mst151.getItemId())
					.itemCode(mst151.getMst110().getItemCode())
					.itemName(mst151.getMst110().getItemName())
					.compId(mst151.getCompId())
					/*	
					 * 	*************************
					 * 
					 *		COMP_NAME 추가해야함 ( Pool 사용 )
					 *
					 *	*************************
					 */
					.priceRev(mst151.getPriceRev())
					.priceType(mst151.getPriceType())
					.priceRevCause(mst151.getPriceRevCause())
					.priceRevUser(mst151.getPriceRevUser())
					.compItemId(mst151.getCompItemId())
					.priceStd(mst151.getPriceStd())
					.priceUnit(mst151.getPriceUnit())
					.deliveryType(mst151.getDeliveryType())
					.deliveryDay(mst151.getDeliveryDay())
					.description(mst151.getDescription())
					.build());
			
			dtoList.add(dto);
		}
		
		return new PageImpl<>(dtoList, pageable, output.getTotalElements());
	}
}
