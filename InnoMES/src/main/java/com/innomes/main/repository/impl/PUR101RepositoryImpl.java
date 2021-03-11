package com.innomes.main.repository.impl;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.purchase.model.PUR101;
import com.innomes.main.repository.custom.PUR101RepositoryCustom;

public class PUR101RepositoryImpl extends QuerydslRepositorySupport implements PUR101RepositoryCustom {
	public PUR101RepositoryImpl() {
		super(PUR101.class);
	}
}
