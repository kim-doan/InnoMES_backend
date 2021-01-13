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

import com.innomes.main.exception.CProductSaveException;
import com.innomes.main.master.dto.MasterItemDTO;
import com.innomes.main.master.dto.MasterProductDTO;
import com.innomes.main.master.model.MST110;
import com.innomes.main.master.model.MST111;
import com.innomes.main.master.param.MasterItemParam;
import com.innomes.main.master.param.MasterProductParam;
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
	public List<MasterProductDTO> findAll(MasterProductParam masterItemParam, Pageable pageable) {
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
	
	public boolean saveProduct(MasterProductParam[] masterProductParam) {
		boolean success = true;
		List<MST110> mst110List = new ArrayList<MST110>();
		List<MST111> mst111List = new ArrayList<MST111>();
		try {
			for(int i=0;i<masterProductParam.length;i++) {
				System.out.println(masterProductParam[i].toString());
				
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
				mst110.setMst111(mst111);
				
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
