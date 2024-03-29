package com.innomes.main.repository.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.code.model.COD100;
import com.innomes.main.code.model.QCOD100;
import com.innomes.main.code.param.CodeInfoParam;
import com.innomes.main.repository.custom.COD100RepositoryCustom;
import com.microsoft.sqlserver.jdbc.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class COD100RepositoryImpl extends QuerydslRepositorySupport implements COD100RepositoryCustom {

	public COD100RepositoryImpl() {
		super(COD100.class);
	}
	private BooleanBuilder setBuilder(QCOD100 cod100,CodeInfoParam codeInfoParam) {
		BooleanBuilder builder = new BooleanBuilder();
		if(!StringUtils.isEmpty(codeInfoParam.getCode())) {
			builder.and(cod100.code.like("%" + codeInfoParam.getCode() + "%"));
		}
		if(codeInfoParam.getDisplayType() != null) {
			if(codeInfoParam.getDisplayType() == 0) {
				builder.and(cod100.codeCtg.isNotNull());
				builder.and(cod100.fixLevel.goe(cod100.codeLevel));
			} else {
				builder.and(cod100.codeCtg.isNotNull());
			}
		}
		if(codeInfoParam.getCodeType() != null) {
			builder.and(cod100.codeType.eq(codeInfoParam.getCodeType()));
		}
		if(!StringUtils.isEmpty(codeInfoParam.getPCode())) {
			builder.and(cod100.pCode.like("%" + codeInfoParam.getPCode() + "%"));
		}
		if(!StringUtils.isEmpty(codeInfoParam.getCodeKR())) {
			builder.and(cod100.codeKR.like("%" + codeInfoParam.getCodeKR() + "%"));
		}
		if(!StringUtils.isEmpty(codeInfoParam.getCodeCH())) {
			builder.and(cod100.codeCH.like("%" + codeInfoParam.getCodeCH() + "%"));
		}
		if(!StringUtils.isEmpty(codeInfoParam.getCodeEN())) {
			builder.and(cod100.codeEN.like("%" + codeInfoParam.getCodeEN() + "%"));
		}
		if(!StringUtils.isEmpty(codeInfoParam.getCodeJP())) {
			builder.and(cod100.codeJP.like("%" + codeInfoParam.getCodeJP() + "%"));
		}
		if(!StringUtils.isEmpty(codeInfoParam.getCodeCtg())) {
			builder.and(cod100.codeCtg.like("%" + codeInfoParam.getCodeCtg() + "%"));
		}
		if(!StringUtils.isEmpty(codeInfoParam.getDisplayCode())) {
			builder.and(cod100.displayCode.like("%" + codeInfoParam.getDisplayCode() + "%"));
		}
		if(codeInfoParam.getUsed() != null) {
			builder.and(cod100.used.eq(codeInfoParam.getUsed()));
		}
		return builder;
	}
	
	@Override
	public List<COD100> findAllLike(CodeInfoParam codeInfoParam){
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QCOD100 cod100 = QCOD100.cOD100;
		
		BooleanBuilder builder = setBuilder(cod100,codeInfoParam);
		
		List<COD100> cod100List = query.from(cod100)
				.select(cod100)
				.where(builder)
				.fetch();
		
		return cod100List;
	}
	@Override
	public Page<COD100> findAllLike(CodeInfoParam codeInfoParam, Pageable pageable) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QCOD100 cod100 = QCOD100.cOD100;
		
		BooleanBuilder builder = setBuilder(cod100,codeInfoParam);
		
		QueryResults<COD100> result = query.from(cod100)
				.select(cod100)
				.where(builder)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetchResults();
		
		return new PageImpl<>(result.getResults(), pageable, result.getTotal());
	}

	@Override
	public List<COD100> findAll() {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QCOD100 cod100 = QCOD100.cOD100;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(cod100.used.eq(1));
		
		List<COD100> result = query.from(cod100)
				.select(cod100)
				.where(builder)
				.fetch();
		
		return result;
	}
}
