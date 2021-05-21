package com.innomes.main.repository.impl;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.master.model.QMST110;
import com.innomes.main.purchase.model.PUR111;
import com.innomes.main.purchase.model.QPUR110;
import com.innomes.main.purchase.model.QPUR111;
import com.innomes.main.purchase.param.PurchaseOrderParam;
import com.innomes.main.repository.custom.PUR111RepositoryCustom;
import com.microsoft.sqlserver.jdbc.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class PUR111RepositoryImpl extends QuerydslRepositorySupport implements PUR111RepositoryCustom{
	public PUR111RepositoryImpl() {
		super(PUR111.class);
	}

	@Override
	public Page<PUR111> findAllLike(PurchaseOrderParam purchaseOrderParam, Pageable pageable, String itemType) {
		
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		QPUR110 pur110 = QPUR110.pUR110;
		QPUR111 pur111 = QPUR111.pUR111;
		QMST110 mst110 = QMST110.mST110;
		
		/*
		 * 	검색조건
		 * 	01.	발주일자
		 * 	02. 납기일자
		 * 	03.	발주자
		 * 	04.	구매처
		 */
		Date orderStartDate = purchaseOrderParam.getOrderStartDate();
		Date orderEndDate = purchaseOrderParam.getOrderEndDate();	
		Date dueStartDate = purchaseOrderParam.getDueStartDate();	
		Date dueEndDate = purchaseOrderParam.getDueEndDate();
		String orderUser = purchaseOrderParam.getOrderUser();
		String compId = purchaseOrderParam.getCompId();
		String poNo = purchaseOrderParam.getPoNo();
		
		BooleanBuilder builder = new BooleanBuilder();
		if(orderStartDate != null && orderEndDate != null) {
			builder.and(pur110.orderDate.between(orderStartDate, orderEndDate));
		}
		else if(orderStartDate == null && orderEndDate != null) {
			builder.and(pur110.orderDate.lt(orderEndDate));
		}
		else if(orderStartDate != null && orderEndDate == null) {
			builder.and(pur110.orderDate.gt(orderStartDate));
		}
		if(dueStartDate != null && dueEndDate != null) {
			builder.and(pur110.dueDate.between(dueStartDate, dueEndDate));
		}
		else if(dueStartDate == null && dueEndDate != null) {
			builder.and(pur110.dueDate.lt(dueEndDate));
		}
		else if(dueStartDate != null && dueEndDate == null) {
			builder.and(pur110.dueDate.gt(dueStartDate));
		}
		if(!StringUtils.isEmpty(orderUser)) {
			builder.and(pur110.orderUser.eq(orderUser));
		}
		if(!StringUtils.isEmpty(compId)) {
			builder.and(pur110.compId.eq(compId));
		}
		if(!StringUtils.isEmpty(itemType)) {
			builder.and(mst110.itemType.eq(itemType));
		}
		if(!StringUtils.isEmpty(poNo)) {
			builder.and(pur110.poNo.eq(poNo));
		}
		
		builder.and(pur110.used.eq(1));
		builder.and(pur111.used.eq(1));
		
		QueryResults<PUR111> result = query.from(pur111)
				.select(pur111)
				.innerJoin(pur111.pur110, pur110)
				.fetchJoin()
				.innerJoin(pur111.mst110, mst110)
				.fetchJoin()
				.where(builder)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetchResults();
		
		
		return new PageImpl<PUR111>(result.getResults(), pageable, result.getTotal());
	}
	
	
}
