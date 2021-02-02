package com.innomes.main.repository.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.innomes.main.master.dto.MasterProductDTO;
import com.innomes.main.master.model.MST111;
import com.innomes.main.master.param.MasterItemParam;
import com.innomes.main.master.param.MasterProductParam;

public interface MST111RepositoryCustom {
	//제품정보 조회(페이지)
	Page<MST111> findAllLike(MasterProductParam masterProductParam, Pageable pageable);
	
	//제품정보
	List<MST111> findAll();
	
	//제품 - 제조공정정보, 라우팅 조회
	Page<MST111> getManufactureItem(MasterProductParam masterProductParam, Pageable pageable);
}
