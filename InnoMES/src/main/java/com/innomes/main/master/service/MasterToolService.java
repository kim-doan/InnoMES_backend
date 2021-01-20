package com.innomes.main.master.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.innomes.main.exception.CToolSaveException;
import com.innomes.main.master.dto.MasterToolDTO;
import com.innomes.main.master.model.MST110;
import com.innomes.main.master.model.MST113;
import com.innomes.main.master.param.MasterToolParam;
import com.innomes.main.repository.MST113Repository;

@Service
@Transactional
public class MasterToolService {
	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private int batchSize;
	
	@Autowired
	private MST113Repository mst113Repository;
	
	public List<MasterToolDTO> getToolList(MasterToolParam masterToolParam, Pageable pageable){
		
		List<MST113> output = mst113Repository.findAllLike(masterToolParam, pageable);
		
		List<MasterToolDTO> dtoList = new ArrayList<MasterToolDTO>();
		
		for(MST113 mst113 : output) {
			MasterToolDTO dto = MasterToolDTO.builder()
					.itemId(mst113.getMst110().getItemId())
					.itemCode(mst113.getMst110().getItemCode())
					.itemName(mst113.getMst110().getItemName())
					.itemType(mst113.getMst110().getItemType())
					.lotSize(mst113.getMst110().getLotSize())
					.lotUnit(mst113.getMst110().getLotUnit())
					.safetyQnt(mst113.getMst110().getSafetyQnt())
					.safetyUnit(mst113.getMst110().getSafetyUnit())
					.locCode(mst113.getMst110().getLocCode())
					.invType(mst113.getMst110().getInvType())
					.description(mst113.getMst110().getDescription())
					.toolId(mst113.getToolId())
					.toolType(mst113.getToolType())	                
					.toolCtg(mst113.getToolCtg())					                
					.toolGroup(mst113.getToolGroup())					                
					.toolProc(mst113.getToolProc())					              
					.toolLifeCnt(mst113.getToolLifeCnt())					                
					.toolWarningRate(mst113.getToolWarningRate())					                
					.toolChkCycle(mst113.getToolChkCycle())
					.toolRecycleCnt(mst113.getToolRecycleCnt())
					.repItemId(mst113.getRepItemId())
					.prdtionProc(mst113.getPrdtionProc())
					.lotYN(mst113.getLotYN())
					.build();
			dtoList.add(dto);
		}
		return dtoList;
	}
	public boolean saveTool(List<MasterToolParam> paramList) {
		
		boolean success = true;
		
		List<MST110> mst110List = new ArrayList<MST110>();
		List<MST113> mst113List = new ArrayList<MST113>();
		try {
			for(MasterToolParam param : paramList) {
				MST110 mst110 = MST110.builder()
						.itemId(param.getMst110().getItemId())
						.itemCode(param.getMst110().getItemCode())
						.itemName(param.getMst110().getItemName())
						.itemType(param.getMst110().getItemType())
						.lotSize(param.getMst110().getLotSize())
						.lotUnit(param.getMst110().getLotUnit())
						.safetyQnt(param.getMst110().getSafetyQnt())
						.safetyUnit(param.getMst110().getSafetyUnit())
						.locCode(param.getMst110().getLocCode())
						.invType(param.getMst110().getInvType())
						.description(param.getMst110().getDescription())
						.createUser(param.getMst110().getCreateUser())
						.createTime(new Date())
						.updateUser(param.getMst110().getUpdateUser())
						.updateTime(new Date())
						.used(param.getMst110().getUsed())
						.build();
				
				MST113 mst113 = MST113.builder()
						.toolId(param.getMst113().getToolId())
						.toolType(param.getMst113().getToolType())
						.toolCtg(param.getMst113().getToolCtg())
						.toolGroup(param.getMst113().getToolGroup())
						.toolProc(param.getMst113().getToolProc())
						.prdtionProc(param.getMst113().getPrdtionProc())
						.toolLifeCnt(param.getMst113().getToolLifeCnt())
						.toolWarningRate(param.getMst113().getToolWarningRate())
						.toolChkCycle(param.getMst113().getToolChkCycle())
						.toolRecycleCnt(param.getMst113().getToolRecycleCnt())
						.lotYN(param.getMst113().getLotYN())
						.repItemId(param.getMst113().getRepItemId())
						.build();
				//mst110.setMst113(mst113);
				mst113.setMst110(mst110);
				
				mst113List.add(mst113);
				
				int curIndex = paramList.indexOf(param);
				if ((curIndex + 1) % batchSize == 0 && curIndex > 0) {
					mst113Repository.saveAll(mst113List);
					mst113Repository.flush();
					mst113List.clear();
				}
			}
			mst113Repository.saveAll(mst113List);
			mst113Repository.flush();
			mst113List.clear();
		}catch(CToolSaveException e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 롤백
			throw new CToolSaveException();
		}catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 롤백
			success = false;
		}
		return success;
	}      
}
