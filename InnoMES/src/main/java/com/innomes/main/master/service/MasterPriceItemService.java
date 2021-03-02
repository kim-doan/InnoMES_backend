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

import com.innomes.main.master.dto.MasterPriceItemDTO;
import com.innomes.main.master.dto.MasterProductDTO;
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
	
	
	public Page<MasterProductDTO> getPriceItem(MasterPriceItemParam masterPriceItemParam, Pageable pageable){
		Page<MST110> output = mst110Repository.findAllPriceItem(masterPriceItemParam, pageable);
		List<MST110> content = output.getContent();
		List<MasterProductDTO> dtoList = new ArrayList<MasterProductDTO>();
		for(MST110 mst110 : content) {
			MasterProductDTO dto = (MasterProductDTO.builder()
					.itemId(mst110.getItemId())
					.itemCode(mst110.getItemCode())
					.itemName(mst110.getItemName())
					.itemType(mst110.getItemType())
					.build());
			
			dtoList.add(dto);
		}
		
		return new PageImpl<>(dtoList, pageable, output.getTotalElements());
	}
	
	public Page<MasterPriceItemDTO> getPriceInfo(MasterPriceItemParam masterPriceItemParam, Pageable pageable){
		Page<MST151> output = mst151Repository.findAllLike(masterPriceItemParam, pageable);
		List<MST151> content = output.getContent();
		List<MasterPriceItemDTO> dtoList = new ArrayList<MasterPriceItemDTO>();
		
		for(int i = 0; i<content.size(); i++) {
			MasterPriceItemDTO dto = (MasterPriceItemDTO.builder()
					.itemId(content.get(i).getMst110().getItemId())
					.itemCode(content.get(i).getMst110().getItemCode())
					.itemName(content.get(i).getMst110().getItemName())
					.compId(content.get(i).getCompId())
					.compItemId(content.get(i).getCompItemId())
					.priceStd(content.get(i).getPriceStd())
					.priceRevCause(content.get(i).getPriceRevCause())
					.priceRevUser(content.get(i).getPriceRevUser())
					.priceUnit(content.get(i).getPriceUnit())
					.deliveryType(content.get(i).getDeliveryType())
					.deliveryDay(content.get(i).getDeliveryDay())
					.priceRev(content.get(i).getPriceRev())
					.description(content.get(i).getDescription())
					.createUser(content.get(i).getCreateUser())
					.createTime(content.get(i).getCreateTime())
					.updateUser(content.get(i).getUpdateUser())
					.updateTime(content.get(i).getUpdateTime())
					.build());
			
			dtoList.add(dto);
		}
		
		
		return new PageImpl<>(dtoList, pageable, output.getTotalElements());
	}
}