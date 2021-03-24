package com.innomes.main.repository.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.innomes.main.code.model.COD200;
import com.innomes.main.code.param.BadCodeParam;

public interface COD200RepositoryCustom {
	List<COD200> findAll();
	
	Page<COD200> findAllLike(BadCodeParam badCodeParam, Pageable pageable);
}
