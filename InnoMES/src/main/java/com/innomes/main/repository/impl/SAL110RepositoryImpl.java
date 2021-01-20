package com.innomes.main.repository.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.exception.CSalesOrderSaveException;
import com.innomes.main.repository.custom.SAL110RepositoryCustom;
import com.innomes.main.sales.model.QSAL110;
import com.innomes.main.sales.model.QSAL111;
import com.innomes.main.sales.model.SAL110;
import com.innomes.main.sales.param.SalesOrderParam;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class SAL110RepositoryImpl extends QuerydslRepositorySupport implements SAL110RepositoryCustom {

	public SAL110RepositoryImpl() {
		super(SAL110.class);
	}

	@Override
	public List<SAL110> getSalesOrderLog(SalesOrderParam salesOrderParam) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		QSAL110 sal110 = QSAL110.sAL110;
		QSAL111 sal111 = QSAL111.sAL111;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(salesOrderParam.getOrdId() != null) {
			builder.and(sal110.ordId.eq(salesOrderParam.getOrdId()));
		} else {
			throw new CSalesOrderSaveException();
		}
		
		builder.and(sal110.used.eq(1));
		
		List<SAL110> result = query.from(sal110)
				.select(sal110)
				.where(builder)
				.innerJoin(sal110.sal111, sal111)
				.fetchJoin()
				.fetch().stream().distinct().collect(Collectors.toList());
		
		return result;
	}
}
