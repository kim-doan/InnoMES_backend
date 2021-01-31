package com.innomes.main.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.innomes.main.master.model.MST151;
import com.innomes.main.master.param.MasterPriceParam;

public interface MST151RepositoryCustom {
	Page<MST151> findAllLike(MasterPriceParam masterPriceParam, Pageable pageable);
}
