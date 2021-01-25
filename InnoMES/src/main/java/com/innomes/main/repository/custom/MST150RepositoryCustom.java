package com.innomes.main.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.innomes.main.master.model.MST150;
import com.innomes.main.master.param.MasterCompanyParam;

public interface MST150RepositoryCustom {
	Page<MST150> findAllLike(MasterCompanyParam companyParam, Pageable pageable);
}
