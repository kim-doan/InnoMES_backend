package com.innomes.main.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.master.model.MST141;
import com.innomes.main.master.model.QMST141;
import com.innomes.main.master.param.MasterTeamParam;
import com.innomes.main.repository.custom.MST141RepositoryCustom;
import com.microsoft.sqlserver.jdbc.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class MST141RepositoryImpl extends QuerydslRepositorySupport implements MST141RepositoryCustom{
	
	public MST141RepositoryImpl() {
		super(MST141.class);
	}
	
	@Override
	public Page<MST141> findAllLike(MasterTeamParam masterTeamParam, Pageable pageable) {
	
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		QMST141 mst141 = QMST141.mST141;
		BooleanBuilder builder = new BooleanBuilder();
		
		if(!StringUtils.isEmpty(masterTeamParam.getTeamCode())) {
			builder.and(mst141.teamCode.like(masterTeamParam.getTeamCode()));
		}
		if(!StringUtils.isEmpty(masterTeamParam.getTeamName())) {
			builder.and(mst141.teamName.like("%" + masterTeamParam.getTeamName() + "%"));
		}
		
		builder.and(mst141.used.eq(1));
		
		QueryResults<MST141> result = query.from(mst141)
				.select(mst141)
				.where(builder)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetchResults();
		
		return new PageImpl<>(result.getResults(), pageable, result.getTotal());
	}
}
