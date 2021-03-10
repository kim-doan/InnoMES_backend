package com.innomes.main.repository.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.master.model.MST120;
import com.innomes.main.master.model.QMST120;
import com.innomes.main.master.param.MasterProcessParam;
import com.innomes.main.repository.custom.MST120RepositoryCustom;
import com.microsoft.sqlserver.jdbc.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class MST120RepositoryImpl extends QuerydslRepositorySupport implements MST120RepositoryCustom {

	public MST120RepositoryImpl() {
		super(MST120.class);
	}

	public Page<MST120> findAllLike(MasterProcessParam masterProcessParam, Pageable pageable) {

		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		QMST120 mst120 = QMST120.mST120;

		BooleanBuilder builder = new BooleanBuilder();
		
		if(masterProcessParam.getDefaultYN() != null) {
			builder.and(mst120.defaultYN.eq(masterProcessParam.getDefaultYN()));
		}
		if(!StringUtils.isEmpty(masterProcessParam.getProcCode())) {
			builder.and(mst120.procCode.like("%" + masterProcessParam.getProcCode() + "%"));
		}
		if(!StringUtils.isEmpty(masterProcessParam.getProcName())) {
			builder.and(mst120.procName.like("%" + masterProcessParam.getProcName() + "%"));
		}
		if(!StringUtils.isEmpty(masterProcessParam.getProcType())) {
			builder.and(mst120.procType.like("%" + masterProcessParam.getProcType() + "%"));
		}
		builder.and(mst120.used.eq(1));
		
		QueryResults<MST120> result = query.from(mst120)
				.select(mst120)
				.where(builder)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetchResults();
		
		return new PageImpl<>(result.getResults(), pageable, result.getTotal());

	}

	@Override
	public List<MST120> findAll() {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		QMST120 mst120 = QMST120.mST120;

		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(mst120.used.eq(1));
		
		List<MST120> result = query.from(mst120)
				.select(mst120)
				.where(builder)
				.fetch();
		
		return result;
	}

}
