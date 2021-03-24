package com.innomes.main.master.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.innomes.main.exception.CValiedationItemCodeException;
import com.innomes.main.master.dto.MasterProductDTO;
import com.innomes.main.master.dto.MasterSpareDTO;
import com.innomes.main.master.model.MST110;
import com.innomes.main.master.model.MST111;
import com.innomes.main.master.model.MST114;
import com.innomes.main.master.param.MasterSpareParam;
import com.innomes.main.repository.MST114Repository;

@Service
@Transactional
public class MasterSpareService {
	@Autowired
	private MST114Repository mst114Repository;
	
	@Autowired
	private MasterProductService masterProductService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	//예비품 전체 조회
	public Page<MasterSpareDTO> getSpares(MasterSpareParam masterSpareParam, Pageable pageable){
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		Page<MST114> output = mst114Repository.findAllLike(masterSpareParam, pageable);
		return new PageImpl<>(modelMapper.map(output.getContent(), new TypeToken<List<MasterSpareDTO>>(){}.getType()),pageable, output.getTotalElements());
	}
	
	public List<MasterSpareDTO> findAll() {
		List<MST114> content = mst114Repository.findAll();
		
		List<MasterSpareDTO> dtoList = new ArrayList<MasterSpareDTO>();
		
		for(int i=0;i<content.size();i++) {
			MasterSpareDTO dto = MasterSpareDTO.builder()
					.itemId(content.get(i).getMst110().getItemId())
					.itemCode(content.get(i).getMst110().getItemCode())
					.itemName(content.get(i).getMst110().getItemName())
					.itemType(content.get(i).getMst110().getItemType())
					.lotSize(content.get(i).getMst110().getLotSize())
					.lotUnit(content.get(i).getMst110().getLotUnit())
					.safetyQnt(content.get(i).getMst110().getSafetyQnt())
					.safetyUnit(content.get(i).getMst110().getSafetyUnit())
					.locCode(content.get(i).getMst110().getLocCode())
					.invType(content.get(i).getMst110().getInvType())
					.description(content.get(i).getMst110().getDescription())
					.createUser(content.get(i).getMst110().getCreateUser())
					.createTime(content.get(i).getMst110().getCreateTime())
					.updateUser(content.get(i).getMst110().getUpdateUser())
					.updateTime(content.get(i).getMst110().getUpdateTime())
					.used(content.get(i).getMst110().getUsed())
					.partId(content.get(i).getPartId())
					.partType(content.get(i).getPartType())
					.partCtg(content.get(i).getPartCtg())
					.partGroup(content.get(i).getPartGroup())
					.partShiftCycle(content.get(i).getPartShiftCycle())
					.cycleUnit(content.get(i).getCycleUnit())
					.incInspYN(content.get(i).getIncInspYN())
					.build();
			
			dtoList.add(dto);
		}
		
		return dtoList;
	}
	
	//예비품 INSERT & UPDATE
	public boolean saveSpare(MasterSpareParam[] masterSpareParams) {
		boolean success = true;
		List<MST114> mst114List = new ArrayList<MST114>();
		
		
		try {
			for (MasterSpareParam param : masterSpareParams) {
				
				//itemCode 중복체크 (삭제 아닐경우)
				if(param.getUsed() == 1) {
					masterProductService.valiedationItemCode(param.getItemCode());
				}
				
				MST110 mst110 = MST110.builder()
						.itemId(param.getItemId())
						.itemCode(param.getItemCode())
						.itemName(param.getItemName())
						.itemType(param.getItemType())
						.lotSize(param.getLotSize())
						.lotUnit(param.getLotUnit())
						.safetyQnt(param.getSafetyQnt())
						.safetyUnit(param.getSafetyUnit())
						.locCode(param.getLocCode())
						.invType(param.getInvType())
						.description(param.getDescription())
						.createUser(param.getCreateUser())
						.createTime(new Date())
						.updateUser(param.getUpdateUser())
						.updateTime(new Date())
						.used(param.getUsed())
						.build();
				
				MST114 mst114 = MST114.builder()
						.partId(param.getItemId())
						.partType(param.getPartType())
						.partCtg(param.getPartCtg())
						.partGroup(param.getPartGroup())
						.partShiftCycle(param.getPartShiftCycle())
						.cycleUnit(param.getCycleUnit())
						.build();
				
				mst114.setMst110(mst110);
				
				mst114List.add(mst114);
			}
			mst114Repository.saveAll(mst114List);
			mst114Repository.flush();
			mst114List.clear();
		} catch(CValiedationItemCodeException e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 롤백
			throw new CValiedationItemCodeException();
		} catch (Exception e){
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 롤백
			success = false;
		}
		
		return success;
	}
}
