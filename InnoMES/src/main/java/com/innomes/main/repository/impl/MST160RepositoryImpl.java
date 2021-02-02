package com.innomes.main.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import com.innomes.main.master.model.MST160;
import com.innomes.main.master.model.QMST120;
import com.innomes.main.master.model.QMST160;
import com.innomes.main.master.param.MasterLocationParam;
import com.innomes.main.repository.custom.MST160RepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class MST160RepositoryImpl extends QuerydslRepositorySupport implements MST160RepositoryCustom{

	public MST160RepositoryImpl() {
		super(MST160.class);
	}

	public Page<MST160> findAllLike(MasterLocationParam masterLocationParam,Pageable pageable) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QMST160 mst160 = QMST160.mST160;
		QMST120 mst120 = QMST120.mST120;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(!StringUtils.isEmpty(masterLocationParam.getLocName())){
			builder.and(mst160.locName.like("%" + masterLocationParam.getLocName() + "%"));
		}
		if(!StringUtils.isEmpty(masterLocationParam.getLocCode())) {
			builder.and(mst160.locCode.like("%" + masterLocationParam.getLocCode() + "%"));
		}
		if(!StringUtils.isEmpty(masterLocationParam.getLocType())) {
			builder.and(mst160.locType.like("%" + masterLocationParam.getLocType() + "%"));
		}
		if(!StringUtils.isEmpty(masterLocationParam.getFtrCode())) {
			builder.and(mst160.ftrCode.like("%" + masterLocationParam.getFtrCode() + "%"));
		}
//		if(!StringUtils.isEmpty(masterLocationParam.getProcCode())) {
//			builder.and(mst160.mst120.procCode.like("%" + masterLocationParam.getProcCode() + "%"));
//		}
		if(masterLocationParam.getUsed() != null) {
			builder.and(mst160.used.eq(masterLocationParam.getUsed()));
		}
		QueryResults<MST160> mst160List = query.from(mst160)
				.select(mst160)
//				.leftJoin(mst160.mst120, mst120)
//				.fetchJoin()
				.where(builder)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetchResults();
		
		return new PageImpl<>(mst160List.getResults(), pageable, mst160List.getTotal());
	}
}
