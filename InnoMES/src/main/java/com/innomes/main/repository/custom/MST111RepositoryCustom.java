package com.innomes.main.repository.custom;

import java.util.List;

import com.innomes.main.master.model.MST111;
import com.innomes.main.master.param.MasterItemParam;

public interface MST111RepositoryCustom {
	List<MST111> findAllLike(MasterItemParam masterProductParam);
}
