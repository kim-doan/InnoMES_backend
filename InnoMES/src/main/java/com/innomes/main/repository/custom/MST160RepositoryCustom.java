package com.innomes.main.repository.custom;

import java.util.List;

import com.innomes.main.master.model.MST160;
import com.innomes.main.master.param.MasterLocationParam;

public interface MST160RepositoryCustom {
	List<MST160> findAllLike(MasterLocationParam masterLocationParam);
}
