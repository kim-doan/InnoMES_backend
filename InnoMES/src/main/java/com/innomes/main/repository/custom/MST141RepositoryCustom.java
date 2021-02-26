package com.innomes.main.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.innomes.main.master.model.MST141;
import com.innomes.main.master.param.MasterTeamParam;

public interface MST141RepositoryCustom {
	Page<MST141> findAllLike(MasterTeamParam masterTeamParam, Pageable pageable);
}
