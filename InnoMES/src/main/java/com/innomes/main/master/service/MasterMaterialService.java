package com.innomes.main.master.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.google.common.reflect.TypeToken;
import com.innomes.main.master.dto.MasterMaterialDTO;
import com.innomes.main.master.model.MST110;
import com.innomes.main.master.model.MST112;
import com.innomes.main.master.param.MasterMaterialParam;
import com.innomes.main.repository.MST112Repository;

@Service
@Transactional
public class MasterMaterialService {
	@Autowired
	private MST112Repository mst112Repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//자재 정보 조회
	public Page<MasterMaterialDTO> getMaterial(MasterMaterialParam masterMaterialParam, Pageable pageable){
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		Page<MST112> output = mst112Repository.findAllLike(masterMaterialParam, pageable);
		return new PageImpl<>(modelMapper.map(output.getContent(), new TypeToken<List<MasterMaterialDTO>>(){}.getType()),pageable, output.getTotalElements());
	}
	
	//자재 정보 저장
	public boolean setMaterials(MasterMaterialParam[] masterMaterialParams) {
		boolean success = true;
		List<MST110> mst110List = new ArrayList<MST110>();
		List<MST112> mst112List = new ArrayList<MST112>();
		
		try {
			for (int i = 0; i < masterMaterialParams.length; i++) {
				
				MST110 mst110 = MST110.builder()
						.itemId(masterMaterialParams[i].getItemId())
						.itemCode(masterMaterialParams[i].getItemCode())
						.itemName(masterMaterialParams[i].getItemName())
						.itemType(masterMaterialParams[i].getItemType())
						.lotSize(masterMaterialParams[i].getLotSize())
						.lotUnit(masterMaterialParams[i].getLotUnit())
						.safetyQnt(masterMaterialParams[i].getSafetyQnt())
						.safetyUnit(masterMaterialParams[i].getSafetyUnit())
						.locCode(masterMaterialParams[i].getLocCode())
						.invType(masterMaterialParams[i].getInvType())
						.description(masterMaterialParams[i].getDescription())
						.createUser(masterMaterialParams[i].getCreateUser())
						.createTime(new Date())
						.updateUser(masterMaterialParams[i].getUpdateUser())
						.updateTime(new Date())
						.used(masterMaterialParams[i].getUsed())
						.build();
				MST112 mst112 = MST112.builder()
						.matId(masterMaterialParams[i].getItemId())
						.matType(masterMaterialParams[i].getMatType())
						.matCtg(masterMaterialParams[i].getMatCtg())
						.matGroup(masterMaterialParams[i].getMatGroup())
						.attMatType(masterMaterialParams[i].getAttMatType())
						.attStdType(masterMaterialParams[i].getAttStdType())
						.attDiaType(masterMaterialParams[i].getAttDiaType())
						.incInspYN(masterMaterialParams[i].getIncInspYN())
						.incVolStd(masterMaterialParams[i].getIncVolStd())
						.incVolUnit(masterMaterialParams[i].getIncVolUnit())
						.lotYN(masterMaterialParams[i].getLotYN())
						.matProc(masterMaterialParams[i].getMatProc())
						.build();
				
				mst112.setMst110(mst110);
				
				mst112List.add(mst112);
			}
			mst112Repository.saveAll(mst112List);
			mst112Repository.flush();
			mst112List.clear();
			
		} catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 롤백
			success = false;
		}
		
		return success;
	}
}
