package com.innomes.main.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.master.model.MST110;
import com.innomes.main.master.model.MST111;
import com.innomes.main.master.model.QMST110;
import com.innomes.main.master.param.MasterPriceItemParam;
import com.innomes.main.repository.custom.MST110RepositoryCustom;
import com.microsoft.sqlserver.jdbc.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class MST110RepositoryImpl extends QuerydslRepositorySupport implements MST110RepositoryCustom {
	public MST110RepositoryImpl() {
		super(MST111.class);
	}
	@Override
	public Page<MST110> findAllPriceItem(MasterPriceItemParam masterPriceItemParam, Pageable pageable){
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QMST110 mst110 = QMST110.mST110;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(!StringUtils.isEmpty(masterPriceItemParam.getItemCode())) {
			builder.and(mst110.itemCode.like("%" + masterPriceItemParam.getItemCode() + "%"));
		}
		if(!StringUtils.isEmpty(masterPriceItemParam.getItemName())) {
			builder.and(mst110.itemName.like("%" + masterPriceItemParam.getItemName() + "%"));
		}
		
		builder.and(mst110.used.eq(1));
		if(!masterPriceItemParam.getPriceType().equals("TPS002002")) { // 구매 단가 조회가 아닌경우
			//판매와 외주 단가는 제품만 보인다.
			builder.and(mst110.itemType.eq("ITM001"));
		}
		
		QueryResults<MST110> result = query.from(mst110)
				.select(mst110)
				.where(builder)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.orderBy(mst110.createTime.desc())
				.fetchResults();
		
		return new PageImpl<>(result.getResults(), pageable, result.getTotal());
	}
}
