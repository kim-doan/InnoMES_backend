package com.innomes.main.repository.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.innomes.main.code.model.COD300;
import com.innomes.main.code.param.StopCodeParam;

public interface COD300RepositoryCustom {
	List<COD300> findAll();
	
	Page<COD300> findAllLike(StopCodeParam cod300Param, Pageable pageable);
}
