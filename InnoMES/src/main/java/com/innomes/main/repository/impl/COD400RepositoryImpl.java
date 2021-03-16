package com.innomes.main.repository.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.code.model.COD400;
import com.innomes.main.code.model.QCOD400;
import com.innomes.main.code.param.COD400Param;
import com.innomes.main.repository.custom.COD400RepositoryCustom;
import com.microsoft.sqlserver.jdbc.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class COD400RepositoryImpl extends QuerydslRepositorySupport implements COD400RepositoryCustom {

	public COD400RepositoryImpl() {
		super(COD400.class);
	}

	@Override
	public Page<COD400> findAllLike(COD400Param cod400Param, Pageable pageable) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QCOD400 cod400 = QCOD400.cOD400;
		
		BooleanBuilder builder = new BooleanBuilder();
		if(!StringUtils.isEmpty(cod400Param.getFailCode())) {
			builder.and(cod400.failCode.like("%" + cod400Param.getFailCode() + "%"));
		}
		if(!StringUtils.isEmpty(cod400Param.getDisplayCode())) {
			builder.and(cod400.displayCode.like("%" + cod400Param.getDisplayCode() + "%"));
		}
		if(!StringUtils.isEmpty(cod400Param.getFailClass())) {
			builder.and(cod400.failClass.like("%" + cod400Param.getFailClass() + "%")).or(cod400.failClass.eq("GRP006000"));
		}
		if(!StringUtils.isEmpty(cod400Param.getFailType())) {
			builder.and(cod400.failType.like("%" + cod400Param.getFailType() + "%"));
		}
		if(!StringUtils.isEmpty(cod400Param.getFailName())) {
			builder.and(cod400.failName.like("%" + cod400Param.getFailName() + "%"));
		}
		
		builder.and(cod400.used.eq(1));
		
		QueryResults<COD400> result = query.from(cod400)
				.select(cod400)
				.where(builder)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetchResults();
		
		return new PageImpl<>(result.getResults(), pageable, result.getTotal());
	}

	@Override
	public List<COD400> findAll() {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QCOD400 cod400 = QCOD400.cOD400;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(cod400.used.eq(1));
		
		List<COD400> result = query.from(cod400)
				.select(cod400)
				.where(builder)
				.fetch();
		
		return result;
	}
}
