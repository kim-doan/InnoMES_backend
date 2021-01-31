package com.innomes.main.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import com.innomes.main.master.model.QMST110;
import com.innomes.main.repository.custom.SAL111RepositoryCustom;
import com.innomes.main.sales.model.QSAL110;
import com.innomes.main.sales.model.QSAL111;
import com.innomes.main.sales.model.SAL111;
import com.innomes.main.sales.param.SalesOrderParam;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class SAL111RepositoryImpl extends QuerydslRepositorySupport implements SAL111RepositoryCustom {

	public SAL111RepositoryImpl() {
		super(SAL111.class);
	}

	@Override
	public Page<SAL111> getSalesOrder(SalesOrderParam salesOrderParam, Pageable pageable) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		QSAL110 sal110 = QSAL110.sAL110;
		QSAL111 sal111 = QSAL111.sAL111;
		QSAL111 sal111B = new QSAL111("sal111B");
		QMST110 mst110 = QMST110.mST110;
		//거래처 도메인 필요
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(salesOrderParam.getOrdId() != null) {
			builder.and(sal110.ordId.eq(salesOrderParam.getOrdId()));
		}
		
		if(!StringUtils.isEmpty(salesOrderParam.getItemId())) {
			builder.and(sal110.itemId.eq(salesOrderParam.getItemId()));
		}
		
//		if(!StringUtils.isEmpty(salesOrderParam.getItemCode())) {
//			builder.and(mst110.itemCode.eq(salesOrderParam.getItemCode()));
//		}
//		
//		if(!StringUtils.isEmpty(salesOrderParam.getItemName())) {
//			builder.and(mst110.itemName.eq(salesOrderParam.getItemName()));
//		}
		
		builder.and(sal111B.ordId.isNull());
		builder.and(sal110.used.eq(1));
		
		QueryResults<SAL111> result = query.from(sal111)
				.select(sal111)
				.where(builder)
				.innerJoin(sal111.sal110, sal110)
				.fetchJoin()
				.leftJoin(sal111B).on(sal111B.ordId.eq(sal111.ordId)
						.and(sal111B.ordSeq.gt(sal111.ordSeq)))
				.fetchJoin()
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetchResults();
		
		return new PageImpl<>(result.getResults(), pageable, result.getTotal());
	}

	@Override
	public Double sumOrdQnt(Integer ordId) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QSAL111 sal111 = QSAL111.sAL111;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(sal111.ordId.eq(ordId));
		builder.and(sal111.used.eq(1));
		
		Double result = query.from(sal111)
				.select(sal111.ordQnt.sum())
				.where(builder)
				.fetchOne();
		
		return result;
	}

	@Override
	public Integer maxOrdSeq(Integer ordId) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QSAL111 sal111 = QSAL111.sAL111;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(sal111.ordId.eq(ordId));
		
		Integer result = query.from(sal111)
				.select(sal111.ordSeq.max())
				.where(builder)
				.fetchOne();
		
		return result;
	}

}
