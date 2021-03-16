package com.innomes.main.repository.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.code.model.COD200;
import com.innomes.main.code.model.QCOD200;
import com.innomes.main.code.param.COD200Param;
import com.innomes.main.repository.custom.COD200RepositoryCustom;
import com.microsoft.sqlserver.jdbc.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class COD200RepositoryImpl extends QuerydslRepositorySupport implements COD200RepositoryCustom {

	public COD200RepositoryImpl() {
		super(COD200.class);
	}

	@Override
	public Page<COD200> findAllLike(COD200Param cod200Param, Pageable pageable) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QCOD200 cod200 = QCOD200.cOD200;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(!StringUtils.isEmpty(cod200Param.getBadCode())) {
			builder.and(cod200.badCode.like("%" + cod200Param.getBadCode() + "%"));
		}
		if(!StringUtils.isEmpty(cod200Param.getDisplayCode())) {
			builder.and(cod200.displayCode.like("%" + cod200Param.getDisplayCode() + "%"));
		}
		if(!StringUtils.isEmpty(cod200Param.getBadName())) {
			builder.and(cod200.badName.like("%" + cod200Param.getBadName() + "%"));
		}
		if(!StringUtils.isEmpty(cod200Param.getBadType())) {
			builder.and(cod200.badType.like("%" + cod200Param.getBadType() + "%"));
		}
		if(!StringUtils.isEmpty(cod200Param.getBadClass())) {
			builder.and(cod200.badClass.like("%" + cod200Param.getBadClass() + "%"));
		}
		if(!StringUtils.isEmpty(cod200Param.getProcType())) {
			builder.and(cod200.procType.like("%" + cod200Param.getProcType() + "%"));
		}
		
		builder.and(cod200.used.eq(1));
		
		QueryResults<COD200> result = query.from(cod200)
				.select(cod200)
				.where(builder)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetchResults();
		
		return new PageImpl<>(result.getResults(), pageable, result.getTotal());
	}

	@Override
	public List<COD200> findAll() {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QCOD200 cod200 = QCOD200.cOD200;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(cod200.used.eq(1));
		
		List<COD200> result = query.from(cod200)
				.select(cod200)
				.where(builder)
				.fetch();
		
		return result;
	}
}
