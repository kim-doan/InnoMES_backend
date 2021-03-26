package com.innomes.main.master.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.innomes.main.master.dto.MasterBomDTO;
import com.innomes.main.master.model.MST220;
import com.innomes.main.master.param.MasterBomParam;
import com.innomes.main.pool.service.MasterPoolService;
import com.innomes.main.repository.MST220Repository;

@Service
@Transactional
public class MasterBomService {
	@Autowired
	private MST220Repository mst220Repository;
	
	@Autowired
	private MasterPoolService masterPoolService;
	
	public List<MasterBomDTO> getBomList(MasterBomParam masterBomParam) {
		List<MST220> contentList = mst220Repository.getBomList(masterBomParam);
		
		List<MasterBomDTO> dtoList = new ArrayList<MasterBomDTO>();
		
		for (MST220 content : contentList) {
			MasterBomDTO dto = MasterBomDTO.builder()
					.prdtId(content.getPrdtId())
					.procCode(content.getProcCode())
					.bomSeq(content.getBomSeq())
					.swapSeq(content.getSwapSeq())
					.itemId(content.getItemId())
					.itemCode(masterPoolService.getMST110(content.getItemId()).getItemCode())
					.itemName(masterPoolService.getMST110(content.getItemId()).getItemName())
					.inQnt(content.getInQnt())
					.inUnit(content.getInUnit())
					.description(content.getDescription())
					.createUser(content.getCreateUser())
					.createTime(content.getCreateTime())
					.updateUser(content.getUpdateUser())
					.updateTime(content.getUpdateTime())
					.used(content.getUsed())
					.build();
			
			dtoList.add(dto);
		}
		
		return dtoList;
	}
}
