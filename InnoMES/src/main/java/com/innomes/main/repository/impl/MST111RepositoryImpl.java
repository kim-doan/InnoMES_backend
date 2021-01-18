package com.innomes.main.repository.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.master.dto.MasterProductDTO;
import com.innomes.main.master.model.MST111;
import com.innomes.main.master.model.QMST110;
import com.innomes.main.master.model.QMST111;
import com.innomes.main.master.param.MasterItemParam;
import com.innomes.main.master.param.MasterProductParam;
import com.innomes.main.repository.custom.MST111RepositoryCustom;
import com.microsoft.sqlserver.jdbc.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;


public class MST111RepositoryImpl extends QuerydslRepositorySupport implements MST111RepositoryCustom {
	public MST111RepositoryImpl() {
		super(MST111.class);
	}

	@Override
	public Page<MST111> findAllLike(MasterProductParam masterProductParam, Pageable pageable) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QMST111 mst111 = QMST111.mST111;
		QMST110 mst110 = QMST110.mST110;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(!StringUtils.isEmpty(masterProductParam.getItemId())) {
			builder.and(mst110.itemId.like("%" + masterProductParam.getItemId() + "%"));
		}
		
		//mst110
		if(!StringUtils.isEmpty(masterProductParam.getItemCode())) {
			builder.and(mst110.itemCode.like("%" + masterProductParam.getItemCode() + "%"));
		}
		if(!StringUtils.isEmpty(masterProductParam.getItemName())) {
			builder.and(mst110.itemName.like("%" + masterProductParam.getItemName() + "%"));
		}
		if(!StringUtils.isEmpty(masterProductParam.getItemType())) {
			builder.and(mst110.itemType.like("%" + masterProductParam.getItemType() + "%"));
		}
		if(masterProductParam.getInvType() != null) {
			builder.and(mst110.invType.eq(masterProductParam.getInvType()));
		}
		
		//mst111
		if(!StringUtils.isEmpty(masterProductParam.getPrdtType())) {
			builder.and(mst111.prdtType.like("%" + masterProductParam.getPrdtType() + "%"));
		}
		if(!StringUtils.isEmpty(masterProductParam.getPrdtCtg())) {
			builder.and(mst111.prdtCtg.like("%" + masterProductParam.getPrdtCtg() + "%"));
		}
		if(!StringUtils.isEmpty(masterProductParam.getPrdtGroup())) {
			builder.and(mst111.prdtGroup.like("%" + masterProductParam.getPrdtGroup() + "%"));
		}
		
		
		 QueryResults<MST111> result = query.from(mst111)
		.select(mst111)
		.innerJoin(mst111.mst110, mst110)
		.fetchJoin()
		.where(builder)
		.offset(pageable.getOffset())
		.limit(pageable.getPageSize())
		.fetchResults();
		
		return new PageImpl<>(result.getResults(), pageable, result.getTotal());
	}
}
