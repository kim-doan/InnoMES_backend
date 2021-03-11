package com.innomes.main.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.purchase.model.PUR100;
import com.innomes.main.purchase.param.PurchaseRequestParam;
import com.innomes.main.repository.custom.PUR100RepositoryCustom;

public class PUR100RepositoryImpl extends QuerydslRepositorySupport implements PUR100RepositoryCustom{
	public PUR100RepositoryImpl() {
		super(PUR100.class);
	}

	@Override
	public Page<PUR100> findAllLike(PurchaseRequestParam purchaseRequestParam, Pageable pageable) {
		return null;
	}
}
