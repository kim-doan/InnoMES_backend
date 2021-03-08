package com.innomes.main.repository.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.innomes.main.master.model.MST151;
import com.innomes.main.master.param.MasterPriceItemParam;
import com.innomes.main.master.param.MasterPriceParam;

public interface MST151RepositoryCustom {
	Page<MST151> findAllLike(MasterPriceItemParam masterPriceItemParam, Pageable pageable);
	List<MST151> findPriceRevList(String priceType);
}
