package com.innomes.main.master.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.innomes.main.master.dto.MasterLocationDTO;
import com.innomes.main.master.model.MST120;
import com.innomes.main.master.model.MST160;
import com.innomes.main.master.param.MasterLocationParam;
import com.innomes.main.repository.MST160Repository;

@Service
@Transactional
public class MasterLocationService {
	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private int batchSize;
	
	@Autowired
	private MST160Repository mst160Repository;
	
	public Page<MasterLocationDTO> getMasterLocationList(MasterLocationParam masterLocationParam, Pageable pageable){
		
		Page<MST160> mst160List = mst160Repository.findAllLike(masterLocationParam, pageable);
		
		List<MasterLocationDTO> dtoList = new ArrayList<MasterLocationDTO>();
		
		for(MST160 mst160 : mst160List) {
			MasterLocationDTO dto = MasterLocationDTO.builder()
					.locCode(mst160.getLocCode())
					.locName(mst160.getLocName())
					.locType(mst160.getLocType())
					.ftrCode(mst160.getFtrCode())
					.procCode(mst160.getProcCode())
					.pLocCode(mst160.getPLocCode())
					.locVol(mst160.getLocVol())
					.volUnit(mst160.getVolUnit())
					.description(mst160.getDescription())
					.createUser(mst160.getCreateUser())
					.createTime(mst160.getCreateTime())
					.updateUser(mst160.getUpdateUser())
					.updateTime(mst160.getUpdateTime())
					.used(mst160.getUsed())
					.build();					
			
//			MST120 mst120 = mst160.getMst120();
//			
//			if(mst120 != null) {
//				dto.setProcCode(mst120.getProcCode());
//				dto.setProcName(mst120.getProcName());
//				dto.setProcType(mst120.getProcType());
//				dto.setInOutType(mst120.getInOutType());
//				dto.setPrdtionYN(mst120.getPrdtionYN());
//				dto.setSupplyYN(mst120.getSupplyYN());
//			}
			dtoList.add(dto);
		}
		
		return new PageImpl<>(dtoList, pageable, mst160List.getTotalElements());
	}
}
