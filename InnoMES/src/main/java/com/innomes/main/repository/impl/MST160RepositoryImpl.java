package com.innomes.main.repository.impl;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.master.model.MST160;
import com.innomes.main.master.param.MasterLocationParam;
import com.innomes.main.repository.custom.MST160RepositoryCustom;

public class MST160RepositoryImpl extends QuerydslRepositorySupport implements MST160RepositoryCustom{

	public MST160RepositoryImpl() {
		super(MST160.class);
	}

	@Override
	public List<MST160> findAllLike(MasterLocationParam masterLocationParam) {
		// TODO Auto-generated method stub
		return null;
	}

}
