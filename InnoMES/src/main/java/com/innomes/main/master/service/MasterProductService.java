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

import com.innomes.main.exception.CProductSaveException;
import com.innomes.main.master.dto.MasterProductDTO;
import com.innomes.main.master.model.MST110;
import com.innomes.main.master.model.MST111;
import com.innomes.main.master.param.MasterProductParam;
import com.innomes.main.repository.MST111Repository;

@Service
@Transactional
public class MasterProductService {
	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private int batchSize;
	
	@Autowired
	private MST111Repository mst111Repository;
	
	//전체 조회
	public Page<MasterProductDTO> findAllLike(MasterProductParam masterItemParam, Pageable pageable) {
		Page<MST111> output = mst111Repository.findAllLike(masterItemParam, pageable);
		
		List<MST111> content = output.getContent();
		
		List<MasterProductDTO> dtoList = new ArrayList<MasterProductDTO>();
		
		for(int i=0;i<content.size();i++) {
			MasterProductDTO dto = MasterProductDTO.builder()
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
					.prdtId(content.get(i).getPrdtId())
					.prdtType(content.get(i).getPrdtType())
					.prdtCtg(content.get(i).getPrdtCtg())
					.prdtGroup(content.get(i).getPrdtGroup())
					.attMatType(content.get(i).getAttMatType())
					.attStdType(content.get(i).getAttStdType())
					.attDiaType(content.get(i).getAttDiaType())
					.heatSpec(content.get(i).getHeatSpec())
					.surfaceSpec(content.get(i).getSurfaceSpec())
					.coatingSpec(content.get(i).getCoatingSpec())
					.batchSize(content.get(i).getBatchSize())
					.batchUnit(content.get(i).getBatchUnit())
					.matProc(content.get(i).getMatProc())
					.build();
			
			dtoList.add(dto);
		}
		
		return new PageImpl<>(dtoList, pageable, output.getTotalElements());
	}
	
	public List<MasterProductDTO> findAll() {
		List<MST111> content = mst111Repository.findAll();
		
		List<MasterProductDTO> dtoList = new ArrayList<MasterProductDTO>();
		
		for(int i=0;i<content.size();i++) {
			MasterProductDTO dto = MasterProductDTO.builder()
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
					.prdtId(content.get(i).getPrdtId())
					.prdtType(content.get(i).getPrdtType())
					.prdtCtg(content.get(i).getPrdtCtg())
					.prdtGroup(content.get(i).getPrdtGroup())
					.attMatType(content.get(i).getAttMatType())
					.attStdType(content.get(i).getAttStdType())
					.attDiaType(content.get(i).getAttDiaType())
					.heatSpec(content.get(i).getHeatSpec())
					.surfaceSpec(content.get(i).getSurfaceSpec())
					.coatingSpec(content.get(i).getCoatingSpec())
					.batchSize(content.get(i).getBatchSize())
					.batchUnit(content.get(i).getBatchUnit())
					.matProc(content.get(i).getMatProc())
					.build();
			
			dtoList.add(dto);
		}
		
		return dtoList;
	}
	
	public boolean saveProduct(MasterProductParam[] masterProductParam) {
		boolean success = true;
		List<MST111> mst111List = new ArrayList<MST111>();
		
		try {
			for(int i=0;i<masterProductParam.length;i++) {
				
				MST110 mst110 = MST110.builder()
						.itemId(masterProductParam[i].getItemId())
						.itemCode(masterProductParam[i].getItemCode())
						.itemName(masterProductParam[i].getItemName())
						.itemType(masterProductParam[i].getItemType())
						.lotSize(masterProductParam[i].getLotSize())
						.lotUnit(masterProductParam[i].getLotUnit())
						.safetyQnt(masterProductParam[i].getSafetyQnt())
						.safetyUnit(masterProductParam[i].getSafetyUnit())
						.locCode(masterProductParam[i].getLocCode())
						.invType(masterProductParam[i].getInvType())
						.description(masterProductParam[i].getDescription())
						.createUser(masterProductParam[i].getCreateUser())
						.createTime(new Date())
						.updateUser(masterProductParam[i].getUpdateUser())
						.updateTime(new Date())
						.used(masterProductParam[i].getUsed())
						.build();
				
				MST111 mst111 = MST111.builder()
						.prdtId(masterProductParam[i].getItemId())
						.prdtType(masterProductParam[i].getPrdtType())
						.prdtCtg(masterProductParam[i].getPrdtCtg())
						.prdtGroup(masterProductParam[i].getPrdtGroup())
						.attMatType(masterProductParam[i].getAttMatType())
						.attStdType(masterProductParam[i].getAttStdType())
						.attDiaType(masterProductParam[i].getAttDiaType())
						.heatSpec(masterProductParam[i].getHeatSpec())
						.surfaceSpec(masterProductParam[i].getSurfaceSpec())
						.coatingSpec(masterProductParam[i].getCoatingSpec())
						.batchSize(masterProductParam[i].getBatchSize())
						.batchUnit(masterProductParam[i].getBatchUnit())
						.matProc(masterProductParam[i].getMatProc())
						.build();
				
				mst111.setMst110(mst110);
				
				mst111List.add(mst111);
				
				if ((i + 1) % batchSize == 0 && i > 0) {
					mst111Repository.saveAll(mst111List);
					mst111Repository.flush();
					mst111List.clear();
				}
			}
			
			mst111Repository.saveAll(mst111List);
			mst111Repository.flush();
			mst111List.clear();
		} catch(CProductSaveException e) { 
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 롤백
			throw new CProductSaveException();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 롤백
			success = false;
		}
		
		return success;
	}
}
