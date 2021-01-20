package com.innomes.main.repository.custom;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.innomes.main.master.model.MST112;
import com.innomes.main.master.param.MasterMaterialParam;

public interface MST112RepositoryCustom {
	List<MST112> findAllLike(MasterMaterialParam masterMaterialParam, Pageable pageable);
}
