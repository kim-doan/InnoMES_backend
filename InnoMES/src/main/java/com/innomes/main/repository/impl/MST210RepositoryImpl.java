package com.innomes.main.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import com.innomes.main.master.model.MST200;
import com.innomes.main.master.model.MST210;
import com.innomes.main.master.model.QMST200;
import com.innomes.main.master.model.QMST210;
import com.innomes.main.master.param.MasterManufactureProcessParam;
import com.innomes.main.repository.custom.MST210RepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class MST210RepositoryImpl extends QuerydslRepositorySupport implements MST210RepositoryCustom {

	public MST210RepositoryImpl() {
		super(MST210.class);
	}

//	@Override
//	public Page<MST210> getManufactureProcess(MasterManufactureProcessParam masterManufactureProcessParam,
//			Pageable pageable) {
//		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
//		
//		QMST200 mst200 = QMST200.mST200;
//		QMST210 mst210 = QMST210.mST210;
//		
//		BooleanBuilder builder = new BooleanBuilder();
//		
//		if(!StringUtils.isEmpty(masterManufactureProcessParam.getPrdtId())) {
//			builder.and(mst200.prdtId.eq(masterManufactureProcessParam.getPrdtId()));
//		}
//		
//		builder.and(mst200.used.eq(1));
//		
//		QueryResults<MST210> result = query.from(mst210)
//				.select(mst210)
//				.where(builder)
//				.leftJoin(mst210.mst200, mst200)
//				.fetchJoin()
//				.offset(pageable.getOffset())
//				.limit(pageable.getPageSize())
//				.fetchResults();
//		
//		return new PageImpl<MST210>(result.getResults(), pageable, result.getTotal());
//	}

}
