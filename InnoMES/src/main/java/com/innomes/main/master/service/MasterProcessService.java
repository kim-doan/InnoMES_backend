package com.innomes.main.master.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.innomes.main.exception.CProcessSaveException;
import com.innomes.main.exception.CToolSaveException;
import com.innomes.main.master.dto.MasterProcessDTO;
import com.innomes.main.master.model.MST120;
import com.innomes.main.master.param.MasterProcessParam;
import com.innomes.main.repository.MST120Repository;


@Service
@Transactional
public class MasterProcessService {
	
	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private int batchSize;
	
	@Autowired
	private MST120Repository mst120Repository;
	
	public Page<MasterProcessDTO> getMaterProcessList(MasterProcessParam masterProcessParam,Pageable pageable){
		
		Page<MST120> output = mst120Repository.findAllLike(masterProcessParam, pageable);
		
		List<MasterProcessDTO> dtoList = new ArrayList<MasterProcessDTO>();
		for(MST120 mst120 : output) {
			MasterProcessDTO dto = MasterProcessDTO.builder()
					.procCode(mst120.getProcCode())
					.procName(mst120.getProcName())
					.procType(mst120.getProcType())
					.inOutType(mst120.getInOutType())
					.prdtionYN(mst120.getPrdtionYN())
					.supplyYN(mst120.getSupplyYN())
					.workOrderYN(mst120.getWorkOrderYN())
					.defaultYN(mst120.getDefaultYN())
					.inspFinishedYN(mst120.getInspFinishedYN())
					.asYN(mst120.getAsYN())
					.description(mst120.getDescription())
					.procTypeName("")
					.build();
			dtoList.add(dto);
		}
		return new PageImpl<MasterProcessDTO>(dtoList,pageable,output.getTotalElements());
	}
	
	public boolean saveMasterProcess(List<MasterProcessParam> paramList) {
		boolean success = true;

		List<MST120> mst120List = new ArrayList<MST120>();

		try {
			for (MasterProcessParam param : paramList) {
				
		
		 MST120 mst120 = MST120.builder() .procCode(param.getProcCode())
											.procName(param.getProcName())
											.procType(param.getProcType())
											.inOutType(param.getInOutType())
											.prdtionYN(param.getPrdtionYN())
											.supplyYN(param.getSupplyYN())
											.workOrderYN(param.getWorkOrderYN())
											.defaultYN(param.getDefaultYN())
											.inspFinishedYN(param.getInspFinishedYN())
											.asYN(param.getAsYN())
											.description(param.getDescription())
											.used(param.getUsed())
											.createUser(param.getCreateUser()) 
											.createTime(new Date())
											.updateUser(param.getUpdateUser()) 
											.updateTime(new Date())
											.build();
		
				
		 mst120List.add(mst120);
				int curIndex = paramList.indexOf(param);
				if ((curIndex + 1) % batchSize == 0 && curIndex > 0) {
					mst120Repository.saveAll(mst120List);
					mst120Repository.flush();
					mst120List.clear();
				}
			}
			mst120Repository.saveAll(mst120List);
			mst120Repository.flush();
			mst120List.clear();
			
		}catch (CProcessSaveException e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 롤백
			throw new CProcessSaveException();
		}catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 롤백
			success = false;
		}
		return success;
	}
}
