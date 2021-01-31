package com.innomes.main.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.master.model.MST150;
import com.innomes.main.master.model.QMST150;
import com.innomes.main.master.param.MasterCompanyParam;
import com.innomes.main.repository.custom.MST150RepositoryCustom;
import com.microsoft.sqlserver.jdbc.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class MST150RepositoryImpl extends QuerydslRepositorySupport implements MST150RepositoryCustom {
	public MST150RepositoryImpl() {
		super(MST150.class);
	}
	
	@Override
	public Page<MST150> findAllLike(MasterCompanyParam masterCompanyParam, Pageable pageable){
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QMST150 mst150 = QMST150.mST150;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(!StringUtils.isEmpty(masterCompanyParam.getCompId())) {
			builder.and(mst150.compId.like("%" + masterCompanyParam.getCompId() + "%"));
		}
		if(!StringUtils.isEmpty(masterCompanyParam.getCompName())) {
			builder.and(mst150.compName.like("%" + masterCompanyParam.getCompName() + "%"));
		}
		
		QueryResults<MST150> result = query.from(mst150)
				.select(mst150)
				.where(builder)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetchResults();
		
		return new PageImpl<>(result.getResults(), pageable, result.getTotal());
	}
}
