package com.innomes.main.repository.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.innomes.main.master.dto.MasterProductDTO;
import com.innomes.main.master.model.MST111;
import com.innomes.main.master.param.MasterItemParam;
import com.innomes.main.master.param.MasterProductParam;

public interface MST111RepositoryCustom {
	Page<MST111> findAllLike(MasterProductParam masterProductParam, Pageable pageable);
}
