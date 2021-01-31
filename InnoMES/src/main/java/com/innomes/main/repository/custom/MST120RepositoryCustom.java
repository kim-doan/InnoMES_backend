package com.innomes.main.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.innomes.main.master.model.MST120;
import com.innomes.main.master.param.MasterProcessParam;

public interface MST120RepositoryCustom {
	Page<MST120> findAllLike(MasterProcessParam masterProcessParam, Pageable pageable);
}
