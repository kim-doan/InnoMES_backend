package com.innomes.main.repository.custom;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.innomes.main.master.model.MST114;
import com.innomes.main.master.param.MasterSpareParam;

public interface MST114RepositoryCustom {
	List<MST114> findAllLike(MasterSpareParam masterSpareParam, Pageable pageable);
}
