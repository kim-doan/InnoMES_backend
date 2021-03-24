package com.innomes.main.master.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.innomes.main.exception.CToolSaveException;
import com.innomes.main.exception.CValiedationItemCodeException;
import com.innomes.main.master.dto.MasterProductDTO;
import com.innomes.main.master.dto.MasterToolDTO;
import com.innomes.main.master.model.MST110;
import com.innomes.main.master.model.MST111;
import com.innomes.main.master.model.MST113;
import com.innomes.main.master.param.MasterToolParam;
import com.innomes.main.repository.MST113Repository;

@Service
@Transactional
public class MasterToolService {
	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private int batchSize;
	
	@Autowired
	private MasterProductService masterProductService;
	
	@Autowired
	private MST113Repository mst113Repository;
	
	public Page<MasterToolDTO> getToolList(MasterToolParam masterToolParam, Pageable pageable){
		
		Page<MST113> output = mst113Repository.findAllLike(masterToolParam, pageable);
		
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
					.build();
			dtoList.add(dto);
		}
		return new PageImpl<>(dtoList, pageable, output.getTotalElements());
	}
	
	public List<MasterToolDTO> findAll() {
		List<MST113> content = mst113Repository.findAll();
		
		List<MasterToolDTO> dtoList = new ArrayList<MasterToolDTO>();
		
		for(int i=0;i<content.size();i++) {
			MasterToolDTO dto = MasterToolDTO.builder()
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
					.toolId(content.get(i).getToolId())
					.toolType(content.get(i).getToolType())
					.toolCtg(content.get(i).getToolCtg())
					.toolGroup(content.get(i).getToolGroup())
					.toolProc(content.get(i).getToolProc())
					.prdtionProc(content.get(i).getPrdtionProc())
					.repItemId(content.get(i).getRepItemId())
					.procType(content.get(i).getProcType())
					.incInspYN(content.get(i).getIncInspYN())
					.toolLifeCnt(content.get(i).getToolLifeCnt())
					.toolWarningRate(content.get(i).getToolWarningRate())
					.toolChkCycle(content.get(i).getToolChkCycle())
					.toolRecycleCnt(content.get(i).getToolRecycleCnt())
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
						.lotUnit("UNT018001") // EA (공구)
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
				
				MST113 mst113 = MST113.builder()
						.toolId(param.getToolId())
						.toolType(param.getToolType())
						.toolCtg(param.getToolCtg())
						.toolGroup(param.getToolGroup())
						.toolProc(param.getToolProc())
						.prdtionProc(param.getPrdtionProc())
						.toolLifeCnt(param.getToolLifeCnt())
						.toolWarningRate(param.getToolWarningRate())
						.toolChkCycle(param.getToolChkCycle())
						.toolRecycleCnt(param.getToolRecycleCnt())
						.repItemId(param.getRepItemId())
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
		} catch(CValiedationItemCodeException e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 롤백
			throw new CValiedationItemCodeException();
		}catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 롤백
			success = false;
		}
		return success;
	}      
}
