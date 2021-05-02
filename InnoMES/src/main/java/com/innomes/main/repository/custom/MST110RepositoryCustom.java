package com.innomes.main.repository.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.innomes.main.master.model.MST110;
import com.innomes.main.master.param.MasterItemParam;
import com.innomes.main.master.param.MasterPriceItemParam;

public interface MST110RepositoryCustom {
	List<MST110> findAllLike(MasterItemParam masterItemParam);
	Page<MST110> findAllPriceItem(MasterPriceItemParam masterPriceItemParam, Pageable pageable);
}
