package com.innomes.main.repository.impl;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.purchase.model.PUR110;
import com.innomes.main.repository.custom.PUR110RepositoryCustom;

public class PUR110RepositoryImpl extends QuerydslRepositorySupport implements PUR110RepositoryCustom {
	public PUR110RepositoryImpl() {
		super(PUR110.class);
	}
}
