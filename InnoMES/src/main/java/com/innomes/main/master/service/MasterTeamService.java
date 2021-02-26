package com.innomes.main.master.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.google.common.reflect.TypeToken;
import com.innomes.main.master.dto.MasterTeamDTO;
import com.innomes.main.master.model.MST141;
import com.innomes.main.master.param.MasterTeamParam;
import com.innomes.main.repository.MST141Repository;

@Service
@Transactional
public class MasterTeamService {
	@Autowired
	private MST141Repository mst141Repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Page<MasterTeamDTO> getTeam(MasterTeamParam masterTeamParam, Pageable pageable){
		Page<MST141> output = mst141Repository.findAllLike(masterTeamParam, pageable);
		List<MasterTeamDTO> dtoList = modelMapper.map(output.getContent(),new TypeToken<List<MasterTeamDTO>>(){}.getType());
		return new PageImpl<>(dtoList,pageable, output.getTotalElements());
	}
	
	public boolean setTeam(MasterTeamParam[] masterTeamParams) {

		try {
			List<MST141> mst141List = new ArrayList<MST141>();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		
		return true;
	}
}
