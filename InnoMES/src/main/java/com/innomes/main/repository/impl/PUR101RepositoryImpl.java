package com.innomes.main.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.purchase.model.PUR101;
import com.innomes.main.purchase.model.QPUR101;
import com.innomes.main.purchase.param.PurchaseRequestParam;
import com.innomes.main.repository.custom.PUR101RepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class PUR101RepositoryImpl extends QuerydslRepositorySupport implements PUR101RepositoryCustom {
	public PUR101RepositoryImpl() {
		super(PUR101.class);
	}

	@Override
	public Page<PUR101> findById(PurchaseRequestParam purchaseRequestParam, Pageable pageable) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		QPUR101 pur101 = QPUR101.pUR101;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(pur101.reqNo.eq(purchaseRequestParam.getReqNo()));
		builder.and(pur101.used.eq(1));
		
		QueryResults<PUR101> result = query.from(pur101)
				.select(pur101)
				.where(builder)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetchResults();
		
		return new PageImpl<PUR101>(result.getResults(), pageable, result.getTotal());
	}

}
