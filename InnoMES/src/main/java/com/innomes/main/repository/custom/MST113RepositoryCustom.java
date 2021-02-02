package com.innomes.main.repository.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.innomes.main.master.model.MST113;
import com.innomes.main.master.param.MasterToolParam;

public interface MST113RepositoryCustom {
	Page<MST113> findAllLike(MasterToolParam masterToolParam, Pageable pageable);
	
	List<MST113> findAll();
}
