package com.innomes.main.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.innomes.main.master.model.MST160;
import com.innomes.main.master.param.MasterLocationParam;

public interface MST160RepositoryCustom {
	Page<MST160> findAllLike(MasterLocationParam masterLocationParam,Pageable pageable);
}
