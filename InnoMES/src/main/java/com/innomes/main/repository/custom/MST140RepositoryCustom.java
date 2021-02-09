package com.innomes.main.repository.custom;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.innomes.main.master.model.MST140;
import com.innomes.main.master.param.MasterUserParam;


public interface MST140RepositoryCustom {
	Optional<MST140> findById(String userNo);
	
	Page<MST140> findAllLike(MasterUserParam masterUserParam, Pageable pageable);
	
	List<MST140> findAll();
}
