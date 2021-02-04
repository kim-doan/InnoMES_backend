package com.innomes.main.repository.custom;

import java.util.List;

import com.innomes.main.master.model.MST140;
import com.innomes.main.master.param.MasterUserParam;


public interface MST140RepositoryCustom {
	List<MST140> findAllLike(MasterUserParam masterUserParam);
}
