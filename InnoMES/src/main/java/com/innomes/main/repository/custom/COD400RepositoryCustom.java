package com.innomes.main.repository.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.innomes.main.code.model.COD400;
import com.innomes.main.code.param.COD400Param;

public interface COD400RepositoryCustom {
	List<COD400> findAll();
	
	Page<COD400> findAllLike(COD400Param cod400Param, Pageable pageable);
}
