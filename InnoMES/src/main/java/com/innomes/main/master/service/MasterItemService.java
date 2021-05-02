package com.innomes.main.master.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innomes.main.master.dto.MasterItemDTO;
import com.innomes.main.master.model.MST110;
import com.innomes.main.master.param.MasterItemParam;
import com.innomes.main.repository.MST110Repository;

@Service
@Transactional
public class MasterItemService {
	@Autowired
	private MST110Repository mst110Repository;
	
//	public List<MasterItemDTO> findAllItem(MasterItemParam masterItemParam) {
//		List<MST110> output = mst110Repository.findAllLike(masterItemParam);
//	}
}
