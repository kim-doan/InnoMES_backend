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
import com.innomes.main.exception.CValiedationItemCodeException;
import com.innomes.main.master.dto.MasterMaterialDTO;
import com.innomes.main.master.dto.MasterProductDTO;
import com.innomes.main.master.model.MST110;
import com.innomes.main.master.model.MST111;
import com.innomes.main.master.model.MST112;
import com.innomes.main.master.param.MasterMaterialParam;
import com.innomes.main.repository.MST110Repository;
import com.innomes.main.repository.MST112Repository;

@Service
@Transactional
public class MasterMaterialService {
	@Autowired
	private MST112Repository mst112Repository;
	
	@Autowired
	private MasterProductService masterProductService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//자재 정보 조회
	public Page<MasterMaterialDTO> getMaterial(MasterMaterialParam masterMaterialParam, Pageable pageable){
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		Page<MST112> output = mst112Repository.findAllLike(masterMaterialParam, pageable);
		return new PageImpl<>(modelMapper.map(output.getContent(), new TypeToken<List<MasterMaterialDTO>>(){}.getType()),pageable, output.getTotalElements());
	}
	
	//자재정보 조회
	public List<MasterMaterialDTO> findAll() {
		List<MST112> content = mst112Repository.findAll();
		
		List<MasterMaterialDTO> dtoList = new ArrayList<MasterMaterialDTO>();
		
		for(int i=0;i<content.size();i++) {
			MasterMaterialDTO dto = MasterMaterialDTO.builder()
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
					.matId(content.get(i).getMatId())
					.matType(content.get(i).getMatType())
					.matCtg(content.get(i).getMatCtg())
					.matGroup(content.get(i).getMatGroup())
					.attMatType(content.get(i).getAttMatType())
					.attStdType(content.get(i).getAttStdType())
					.attDiaType(content.get(i).getAttDiaType())
					.incInspYN(content.get(i).getIncInspYN())
					.incVolStd(content.get(i).getIncVolStd())
					.incVolUnit(content.get(i).getIncVolUnit())
					.matProc(content.get(i).getMatProc())
					.build();
			
			dtoList.add(dto);
		}
		
		return dtoList;
	}
	
	//자재 정보 저장
	public boolean setMaterials(MasterMaterialParam[] masterMaterialParams) {
		boolean success = true;
		List<MST110> mst110List = new ArrayList<MST110>();
		List<MST112> mst112List = new ArrayList<MST112>();
		
		try {
			for (int i = 0; i < masterMaterialParams.length; i++) {
				
				//itemCode 중복체크 (삭제 아닐경우)
				if(masterMaterialParams[i].getUsed() == 1) {
					masterProductService.valiedationItemCode(masterMaterialParams[i].getItemCode());
				}
				
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
						.matProc(masterMaterialParams[i].getMatProc())
						.build();
				
				mst112.setMst110(mst110);
				
				mst112List.add(mst112);
			}
			mst112Repository.saveAll(mst112List);
			mst112Repository.flush();
			mst112List.clear();
			
		} catch(CValiedationItemCodeException e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 롤백
			throw new CValiedationItemCodeException();
		} catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 롤백
			success = false;
		}
		
		return success;
	}
}
