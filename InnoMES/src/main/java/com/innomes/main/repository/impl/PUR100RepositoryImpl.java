package com.innomes.main.repository.impl;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.purchase.model.PUR100;
import com.innomes.main.purchase.model.QPUR100;
import com.innomes.main.purchase.param.PurchaseRequestParam;
import com.innomes.main.repository.custom.PUR100RepositoryCustom;
import com.microsoft.sqlserver.jdbc.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class PUR100RepositoryImpl extends QuerydslRepositorySupport implements PUR100RepositoryCustom{
	public PUR100RepositoryImpl() {
		super(PUR100.class);
	}

	@Override
	public Page<PUR100> findAllLike(PurchaseRequestParam purchaseRequestParam, Pageable pageable) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		QPUR100 pur100 = QPUR100.pUR100;
		
		/*
		 *	화면상 검색조건
		 *	01. 청구기간	[startDate ~ endDate]
		 *	02. 청구인	[룩업]
		 *	03. 청구부서	[룩업]
		 */
		Date startDate = purchaseRequestParam.getStartDate();
		Date endDate = purchaseRequestParam.getEndDate();
		String reqUser = purchaseRequestParam.getReqUser();
		String reqDept = purchaseRequestParam.getReqDepartment();
		String reqType = purchaseRequestParam.getReqType();
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(startDate != null && endDate != null) {
			builder.and(pur100.reqDate.between(startDate, endDate));
		}
		else if(startDate == null && endDate != null) {
			builder.and(pur100.reqDate.lt(endDate));
		}
		else if(startDate != null && endDate == null) {
			builder.and(pur100.reqDate.gt(startDate));
		}
		if(!StringUtils.isEmpty(reqUser)) {
			builder.and(pur100.reqUser.eq(reqUser));
		}
		if(!StringUtils.isEmpty(reqDept)) {
			builder.and(pur100.reqDepartment.eq(reqDept));
		}
		
		
		builder.and(pur100.reqType.eq(reqType));
		builder.and(pur100.used.eq(1));
		
		QueryResults<PUR100> result = query.from(pur100)
				.select(pur100)
				.where(builder)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetchResults();
		
		return new PageImpl<PUR100>(result.getResults(), pageable, result.getTotal());
	}

	@Override
	public List<PUR100> findAll() {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		QPUR100 pur100 = QPUR100.pUR100;
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(pur100.used.eq(1));
		return query.from(pur100).select(pur100).where(builder).fetch();
	}
}
