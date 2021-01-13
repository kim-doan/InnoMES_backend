package com.innomes.main.master.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.innomes.main.master.dto.MasterItemDTO;
import com.innomes.main.master.dto.MasterProductDTO;
import com.innomes.main.master.model.MST110;
import com.innomes.main.master.model.MST111;
import com.innomes.main.master.param.MasterItemParam;
import com.innomes.main.repository.MST110Repository;
import com.innomes.main.repository.MST111Repository;

@Service
@Transactional
public class MasterProductService {
	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private int batchSize;
	
	@Autowired
	private MST111Repository mst111Repository;
	
	//전체 조회
	public List<MasterProductDTO> findAll(MasterItemParam masterItemParam, Pageable pageable) {
		List<MST111> output = mst111Repository.findAllLike(masterItemParam, pageable);
		
		List<MasterProductDTO> dtoList = new ArrayList<MasterProductDTO>();
		
		for(int i=0;i<output.size();i++) {
			MasterProductDTO dto = MasterProductDTO.builder()
					.itemId(output.get(i).getMst110().getItemId())
					.itemCode(output.get(i).getMst110().getItemCode())
					.itemName(output.get(i).getMst110().getItemName())
					.itemType(output.get(i).getMst110().getItemType())
					.lotSize(output.get(i).getMst110().getLotSize())
					.lotUnit(output.get(i).getMst110().getLotUnit())
					.safetyQnt(output.get(i).getMst110().getSafetyQnt())
					.safetyUnit(output.get(i).getMst110().getSafetyUnit())
					.locCode(output.get(i).getMst110().getLocCode())
					.invType(output.get(i).getMst110().getInvType())
					.description(output.get(i).getMst110().getDescription())
					.createUser(output.get(i).getMst110().getCreateUser())
					.createTime(output.get(i).getMst110().getCreateTime())
					.updateUser(output.get(i).getMst110().getUpdateUser())
					.updateTime(output.get(i).getMst110().getUpdateTime())
					.used(output.get(i).getMst110().getUsed())
					.prdtId(output.get(i).getPrdtId())
					.prdtType(output.get(i).getPrdtType())
					.prdtCtg(output.get(i).getPrdtCtg())
					.prdtGroup(output.get(i).getPrdtGroup())
					.attMatType(output.get(i).getAttMatType())
					.attStdType(output.get(i).getAttStdType())
					.attDiaType(output.get(i).getAttDiaType())
					.heatSpec(output.get(i).getHeatSpec())
					.surfaceSpec(output.get(i).getSurfaceSpec())
					.coatingSpec(output.get(i).getCoatingSpec())
					.batchSize(output.get(i).getBatchSize())
					.batchUnit(output.get(i).getBatchUnit())
					.matProc(output.get(i).getMatProc())
					.build();
			
			dtoList.add(dto);
		}
		
		return dtoList;
	}
}
