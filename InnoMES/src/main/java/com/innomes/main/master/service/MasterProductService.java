package com.innomes.main.master.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.innomes.main.master.dto.MasterItemDTO;
import com.innomes.main.master.model.MST110;
import com.innomes.main.master.model.MST111;
import com.innomes.main.master.param.MasterItemParam;
import com.innomes.main.repository.MST110Repository;
import com.innomes.main.repository.MST111Repository;

@Service
@Transactional
public class MasterProductService {
	
	@Autowired
	private MST111Repository mst111Repository;
	
	public List<MST111> findAll(MasterItemParam masterProductParam) {
//		List<MST111> mst111 = mst111Repository.findAll();
//		List<MasterItemDTO> result = new ArrayList<MasterItemDTO>();
//		
//		for(int i=0;i<mst111.size();i++) {
//			MasterItemDTO el = MasterItemDTO.builder()
//					.itemCode(mst111.get(i).getMst110().getItemCode())
//					.itemName(mst111.get(i).getMst110().getItemName())
//					.build();
//			
//			result.add(el);
//		}
		
		return mst111Repository.findAllLike(masterProductParam);
	}
}
