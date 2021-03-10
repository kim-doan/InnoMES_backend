package com.innomes.main.repository.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.innomes.main.master.model.MST120;
import com.innomes.main.master.param.MasterProcessParam;

public interface MST120RepositoryCustom {
	//페이지 조회
	Page<MST120> findAllLike(MasterProcessParam masterProcessParam, Pageable pageable);
	
	//전체 조회
	List<MST120> findAll();
}
