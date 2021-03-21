package com.innomes.main.master.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.innomes.main.exception.CPriceSaveException;
import com.innomes.main.master.dto.MasterPriceDTO;
import com.innomes.main.master.dto.MasterPriceItemDTO;
import com.innomes.main.master.model.MST110;
import com.innomes.main.master.model.MST151;
import com.innomes.main.master.model.MST151PK;
import com.innomes.main.master.param.MasterPriceItemParam;
import com.innomes.main.pool.service.MasterPoolService;
import com.innomes.main.repository.MST110Repository;
import com.innomes.main.repository.MST151Repository;

@Service
@Transactional
public class MasterPriceService {
	
	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private int batchSize;
	
	@Autowired
	private MST151Repository mst151Repository;
	
	@Autowired
	private MST110Repository mst110Repository;
	
	@Autowired
	private MasterPoolService masterPoolService;
	
	//판매단가정보 품목리스트 (메인그리드)
	public Page<MasterPriceItemDTO> getSellItemList(MasterPriceItemParam masterPriceItemParam, Pageable pageable) {
		masterPriceItemParam.setPriceType("TPS002001");		//판매단가
		return getItemList(masterPriceItemParam, pageable);
	}
	
	//판매단가정보 거래처별 단가리스트 (서브그리드)
	public Page<MasterPriceDTO> getSellPriceList(MasterPriceItemParam masterPriceItemParam, Pageable pageable){
		masterPriceItemParam.setPriceType("TPS002001");		//판매단가
		return getPriceList(masterPriceItemParam, pageable);
	}
	
	//판매단가정보 거래처별 단가리스트 (이력보기)
	public List<MasterPriceDTO> getSellPriceRevLog(MasterPriceItemParam masterPriceItemParam) {
		masterPriceItemParam.setPriceType("TPS002001"); // 판매단가
		return getPriceRevLog(masterPriceItemParam);
	}
	
	//구매단가정보 품목리스트(메인그리드)
	public Page<MasterPriceItemDTO> getPurchaseItemList(MasterPriceItemParam masterPriceItemParam, Pageable pageable) {
		masterPriceItemParam.setPriceType("TPS002002");		//구매단가
		return getItemList(masterPriceItemParam, pageable);
	}
	
	//구매단가정보 거래처별 단가리스트 (서브그리드)
	public Page<MasterPriceDTO> getPurchasePriceList(MasterPriceItemParam masterPriceItemParam, Pageable pageable) {
		masterPriceItemParam.setPriceType("TPS002002");		//구매단가
		return getPriceList(masterPriceItemParam, pageable);
	}
	
	//구매단가정보 거래처별 단가리스트 (이력보기)
	public List<MasterPriceDTO> getPurchasePriceRevLog(MasterPriceItemParam masterPriceItemParam) {
		masterPriceItemParam.setPriceType("TPS002002"); // 구매단가
		return getPriceRevLog(masterPriceItemParam);
	}
	
	//단가정보 개정
	public boolean setPriceInfo(MasterPriceItemParam[] masterPriceItemParams) {
		
		boolean success = true;
		List<MST151> mst151List = new ArrayList<MST151>();
		
		try {
			for(int i=0;i< masterPriceItemParams.length;i++) {
				MST151PK pk = MST151PK.builder()
						.itemId(masterPriceItemParams[i].getItemId())
						.compId(masterPriceItemParams[i].getCompId())
						.priceType(masterPriceItemParams[i].getPriceType())
						.build();
				
				if(i == 0) {
					mst151Repository.delBeforeRev(pk, 0); // 이전 리비전 used 0
				}
				
				MST151 mst151 = MST151.builder()
						.itemId(masterPriceItemParams[i].getItemId())
						.compId(masterPriceItemParams[i].getCompId())
						.priceRev(mst151Repository.findMaxRev(pk) == null ? 1 : mst151Repository.findMaxRev(pk) + 1)
						.priceType(masterPriceItemParams[i].getPriceType())
						.priceRevCause(masterPriceItemParams[i].getPriceRevCause())
						.priceRevUser(masterPriceItemParams[i].getPriceRevUser())
						.compItemId(masterPriceItemParams[i].getCompItemId())
						.priceStd(masterPriceItemParams[i].getPriceStd())
						.priceUnit(masterPriceItemParams[i].getPriceUnit())
						.deliveryType(masterPriceItemParams[i].getDeliveryType())
						.deliveryDay(masterPriceItemParams[i].getDeliveryDay())
						.description(masterPriceItemParams[i].getDescription())
						.createUser(masterPriceItemParams[i].getCreateUser())
						.createTime(new Date())
						.updateUser(masterPriceItemParams[i].getUpdateUser())
						.updateTime(new Date())
						.used(masterPriceItemParams[i].getUsed())
						.build();
				
				mst151List.add(mst151);
				
				if ((i + 1) % batchSize == 0 && i > 0) {
					mst151Repository.saveAll(mst151List);
					mst151Repository.flush();
					mst151List.clear();
				}
			}
			mst151Repository.saveAll(mst151List);
			mst151Repository.flush();
			mst151List.clear();
		} catch (CPriceSaveException e) {
			throw new CPriceSaveException();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 롤백
			success = false;
		}
		
		return success;
	}
	
	
	private Page<MasterPriceDTO> getPriceList(MasterPriceItemParam masterPriceItemParam, Pageable pageable) {
		
		Page<MST151> output = mst151Repository.findAllLike(masterPriceItemParam, pageable);
		List<MST151> content = output.getContent();
		List<MasterPriceDTO> dtoList = new ArrayList<MasterPriceDTO>();
		
		for(MST151 mst151 : content) {
			MasterPriceDTO dto = (MasterPriceDTO.builder()
					.itemId(mst151.getItemId())
					.itemCode(mst151.getMst110().getItemCode())
					.itemName(mst151.getMst110().getItemName())
					.compId(mst151.getCompId())
					.compName(masterPoolService.getMST150(mst151.getCompId()).getCompName())
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
					.createUser(mst151.getCreateUser())
					.createTime(mst151.getCreateTime())
					.updateUser(mst151.getUpdateUser())
					.updateTime(mst151.getUpdateTime())
					.used(mst151.getUsed())
					.build());	
			dtoList.add(dto);
		}
		return new PageImpl<>(dtoList, pageable, output.getTotalElements());
	}
	
	private List<MasterPriceDTO> getPriceRevLog(MasterPriceItemParam masterPriceItemParam) {
		
		List<MST151> output = mst151Repository.findPriceRevLog(masterPriceItemParam);
		List<MasterPriceDTO> dtoList = new ArrayList<MasterPriceDTO>();
		
		for(MST151 mst151 : output) {
			MasterPriceDTO dto = (MasterPriceDTO.builder()
					.itemId(mst151.getItemId())
					.itemCode(mst151.getMst110().getItemCode())
					.itemName(mst151.getMst110().getItemName())
					.compId(mst151.getCompId())
					.compName(masterPoolService.getMST150(mst151.getCompId()).getCompName())
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
					.createUser(mst151.getCreateUser())
					.createTime(mst151.getCreateTime())
					.updateUser(mst151.getUpdateUser())
					.updateTime(mst151.getUpdateTime())
					.used(mst151.getUsed())
					.build());	
			dtoList.add(dto);
		}
		return dtoList;
	}
	
	private Page<MasterPriceItemDTO> getItemList(MasterPriceItemParam masterPriceItemParam, Pageable pageable) {
		
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
}
