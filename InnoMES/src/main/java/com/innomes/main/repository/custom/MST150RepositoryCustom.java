package com.innomes.main.repository.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.innomes.main.master.model.MST150;
import com.innomes.main.master.param.MasterCompanyParam;
import com.innomes.main.master.param.MasterPriceItemParam;

public interface MST150RepositoryCustom {
	//페이지 조회
	Page<MST150> findAllLike(MasterCompanyParam companyParam, Pageable pageable);
	
	//전체 조회
	List<MST150> findAll();
}
