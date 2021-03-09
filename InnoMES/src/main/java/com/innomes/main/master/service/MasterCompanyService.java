package com.innomes.main.master.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.google.common.reflect.TypeToken;
import com.innomes.main.master.dto.MasterCompanyDTO;
import com.innomes.main.master.model.MST150;
import com.innomes.main.master.param.MasterCompanyParam;
import com.innomes.main.repository.MST150Repository;
import com.innomes.main.util.service.UtilService;

@Service
@Transactional
public class MasterCompanyService {
	@Autowired
	private MST150Repository mst150Repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UtilService util;
	
	//거래처 조회
	public Page<MasterCompanyDTO> getCompInfo(MasterCompanyParam masterCompanyParam, Pageable pageable){
		Page<MST150> output = mst150Repository.findAllLike(masterCompanyParam, pageable);
		List<MasterCompanyDTO> dtoList = util.convertModelAndDto(output.getContent(), MasterCompanyDTO.class);
		return new PageImpl<>(dtoList,pageable, output.getTotalElements());
	}
	public boolean setCompInfo(List<MasterCompanyParam> compParam) {
		boolean success = true;
		
		try {
			List<MST150> mst150List = util.convertModelAndDto(compParam, MST150.class);
			for (MST150 mst150: mst150List) {
				mst150.setCreateTime(new Date());
				mst150.setUpdateTime(new Date());
			}
			mst150Repository.saveAll(mst150List);
			mst150Repository.flush();
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return success;
	}
}
