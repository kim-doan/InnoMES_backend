package com.innomes.main.repository.impl;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.master.model.MST220;
import com.innomes.main.repository.custom.MST220RepositoryCustom;

public class MST220RepositoryImpl extends QuerydslRepositorySupport implements MST220RepositoryCustom {

	public MST220RepositoryImpl() {
		super(MST220.class);
	}	
}
