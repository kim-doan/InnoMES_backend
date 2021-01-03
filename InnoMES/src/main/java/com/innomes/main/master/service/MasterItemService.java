package com.innomes.main.master.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.innomes.main.master.model.MST110;
import com.innomes.main.master.model.MST111;
import com.innomes.main.master.param.MasterItemParam;
import com.innomes.main.repository.MST110Repository;
import com.innomes.main.repository.MST111Repository;


@Service
@Transactional
public class MasterItemService {
	@Autowired
	private MST110Repository mst110Repository;
	
	@Autowired
	private MST111Repository mst111Repository;
	
	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private int batchSize;
	
	public boolean saveItemInfo(MST111[] mst111) {
		boolean success = true;
		
		List<MST111> mst111List = new ArrayList<MST111>();
		
		try {
			for(int i=0;i<mst111.length;i++) {
//				MST110 mst110 = MST110.builder()
//						.itemId(masterItemParam[i].getItemId())
//						.itemCode(masterItemParam[i].getItemCode())
//						.itemName(masterItemParam[i].getItemName())
//						.itemType(masterItemParam[i].getItemType())
//						.lotSize(masterItemParam[i].getLotSize())
//						.lotUnit(masterItemParam[i].getLotUnit())
//						.safetyQnt(masterItemParam[i].getSafetyQnt())
//						.safetyUnit(masterItemParam[i].getSafetyUnit())
//						.locCode(masterItemParam[i].getLocCode())
//						.invType(masterItemParam[i].getInvType())
//						.description(masterItemParam[i].getDescription())
//						.createUser(masterItemParam[i].getCreateUser())
//						.createTime(new Date())
//						.updateUser(masterItemParam[i].getUpdateUser())
//						.updateTime(new Date())
//						.used(masterItemParam[i].getUsed())
//						.build();
//				
//				MST111 mst111 = MST111.builder()
//						.prdtId(masterItemParam[i].getItemId())
//						.prdtType(masterItemParam[i].getPrdtType())
//						.prdtCtg(masterItemParam[i].getPrdtCtg())
//						.prdtGroup(masterItemParam[i].getPrdtGroup())
//						.attMatType(masterItemParam[i].getAttMatType())
//						.attStdType(masterItemParam[i].getAttStdType())
//						.attDiaType(masterItemParam[i].getAttDiaType())
//						.heatSpec(masterItemParam[i].getHeatSpec())
//						.surfaceSpec(masterItemParam[i].getSurfaceSpec())
//						.coatingSpec(masterItemParam[i].getCoatingSpec())
//						.batchSize(masterItemParam[i].getBatchSize())
//						.batchUnit(masterItemParam[i].getBatchUnit())
//						.build();
				
//				mst111.setMst110(mst110);
				
				mst111List.add(mst111[i]);
				
				if ((i + 1) % batchSize == 0 && i > 0) {
					mst111Repository.saveAll(mst111List);
					mst111Repository.flush();
					mst111List.clear();
				}
			}
			
			mst111Repository.saveAll(mst111List);
			mst111Repository.flush();
			mst111List.clear();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 롤백
			success = false;
		}
		
		return success;
	}
}
