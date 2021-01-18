package com.innomes.main.repository.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.master.model.QMST110;
import com.innomes.main.repository.custom.SAL101RepositoryCustom;
import com.innomes.main.sales.model.QSAL100;
import com.innomes.main.sales.model.QSAL101;
import com.innomes.main.sales.model.SAL100;
import com.innomes.main.sales.model.SAL101;
import com.innomes.main.sales.param.SalesPlanParam;
import com.microsoft.sqlserver.jdbc.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class SAL101RepositoryImpl extends QuerydslRepositorySupport implements SAL101RepositoryCustom {

	public SAL101RepositoryImpl() {
		super(SAL101.class);
	}

	@Override
	public Page<SAL101> getSalesPlan(SalesPlanParam salesPlanParam, Pageable pageable) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		QSAL100 sal100 = QSAL100.sAL100;
		QSAL101 sal101 = QSAL101.sAL101;
		QSAL101 sal101B = new QSAL101("sal101B");
		QMST110 mst110 = QMST110.mST110;
		//거래처 도메인 필요
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(salesPlanParam.getSalPlanNo() != null) {
			builder.and(sal100.salPlanNo.eq(salesPlanParam.getSalPlanNo()));
		}
		
		if(salesPlanParam.getPlanYear() != null) {
			builder.and(sal100.planYear.eq(salesPlanParam.getPlanYear()));
		}
		
		if(salesPlanParam.getPlanMonth() != null) {
			builder.and(sal100.planMonth.eq(salesPlanParam.getPlanMonth()));
		}
		
		if(!StringUtils.isEmpty(salesPlanParam.getCompId())) {
			builder.and(sal100.compId.eq(salesPlanParam.getCompId()));
		}
		
		if(!StringUtils.isEmpty(salesPlanParam.getItemId())) {
			builder.and(sal100.itemId.eq(salesPlanParam.getItemId()));
		}
		
		builder.and(sal101B.salPlanNo.isNull());
		builder.and(sal100.used.eq(1));
		
		QueryResults<SAL101> result = query.from(sal101)
				.select(sal101)
				.where(builder)
				.innerJoin(sal101.sal100, sal100)
				.fetchJoin()
				.leftJoin(sal101B).on(sal101B.salPlanNo.eq(sal101.salPlanNo)
						.and(sal101B.salPlanSeq.gt(sal101.salPlanSeq)))
				.fetchJoin()
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetchResults();
		
		return new PageImpl<>(result.getResults(), pageable, result.getTotal());
	}
	
	@Override
	public Double sumPlanQnt(Integer salPlanNo) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QSAL101 sal101 = QSAL101.sAL101;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(sal101.salPlanNo.eq(salPlanNo));
		
		Double result = query.from(sal101)
				.select(sal101.planQnt.max())
				.where(builder)
				.fetchOne();
		
		return result;
	}
}
