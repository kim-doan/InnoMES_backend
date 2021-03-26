package com.innomes.main.repository.custom;

import java.util.List;

import com.innomes.main.master.dto.MasterBomDTO;
import com.innomes.main.master.model.MST220;
import com.innomes.main.master.param.MasterBomParam;

public interface MST220RepositoryCustom {
	List<MST220> getBomList(MasterBomParam masterBomParam);
}