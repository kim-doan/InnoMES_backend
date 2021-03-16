package com.innomes.main.repository.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.innomes.main.code.model.COD100;
import com.innomes.main.code.param.CodeInfoParam;

public interface COD100RepositoryCustom {
	List<COD100> findAll();
	
	Page<COD100> findAllLike(CodeInfoParam codeInfoParam, Pageable pageable);
}
