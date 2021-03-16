package com.innomes.main.repository.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.code.model.COD300;
import com.innomes.main.code.model.QCOD300;
import com.innomes.main.code.param.COD300Param;
import com.innomes.main.repository.custom.COD300RepositoryCustom;
import com.microsoft.sqlserver.jdbc.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class COD300RepositoryImpl extends QuerydslRepositorySupport implements COD300RepositoryCustom {

	public COD300RepositoryImpl() {
		super(COD300.class);
	}

	@Override
	public Page<COD300> findAllLike(COD300Param cod300Param, Pageable pageable) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QCOD300 cod300 = QCOD300.cOD300;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(!StringUtils.isEmpty(cod300Param.getStopCode())) {
			builder.and(cod300.stopCode.like("%" + cod300Param.getStopCode() + "%"));
		}
		if(!StringUtils.isEmpty(cod300Param.getDisplayCode())) {
			builder.and(cod300.displayCode.like("%" + cod300Param.getDisplayCode() + "%"));
		}
		if(!StringUtils.isEmpty(cod300Param.getStopName())) {
			builder.and(cod300.stopName.like("%" + cod300Param.getStopName() + "%"));
		}
		if(!StringUtils.isEmpty(cod300Param.getStopType())) {
			builder.and(cod300.stopType.like("%" + cod300Param.getStopType() + "%"));
		}
		if(!StringUtils.isEmpty(cod300Param.getStopClass())) {
			builder.and(cod300.stopClass.like("%" + cod300Param.getStopClass() + "%"));
		}
		if(!StringUtils.isEmpty(cod300Param.getProcType())) {
			builder.and(cod300.procType.like("%" + cod300Param.getProcType() + "%"));
		}
		if(cod300Param.getOeeYN() != null) {
			builder.and(cod300.oeeYN.eq(cod300Param.getOeeYN()));
		}
		builder.and(cod300.used.eq(1));
		
		QueryResults<COD300> result = query.from(cod300)
				.select(cod300)
				.where(builder)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetchResults();
		
		return new PageImpl<>(result.getResults(), pageable, result.getTotal());
	}

	@Override
	public List<COD300> findAll() {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QCOD300 cod300 = QCOD300.cOD300;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(cod300.used.eq(1));
		
		List<COD300> result = query.from(cod300)
				.select(cod300)
				.where(builder)
				.fetch();
		
		return result;
	}
}
