package com.innomes.main.repository.impl;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.master.dto.MasterBomDTO;
import com.innomes.main.master.model.MST220;
import com.innomes.main.master.model.QMST220;
import com.innomes.main.master.param.MasterBomParam;
import com.innomes.main.repository.custom.MST220RepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class MST220RepositoryImpl extends QuerydslRepositorySupport implements MST220RepositoryCustom {

	public MST220RepositoryImpl() {
		super(MST220.class);
	}

	@Override
	public List<MST220> getBomList(MasterBomParam masterBomParam) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QMST220 mst220 = QMST220.mST220;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(mst220.prdtId.eq(masterBomParam.getPrdtId()));
		builder.and(mst220.procCode.eq(masterBomParam.getProcCode()));
		builder.and(mst220.used.eq(1));
		
		List<MST220> result = query.from(mst220)
				.select(mst220)
				.where(builder)
				.orderBy(mst220.bomSeq.asc())
				.fetch();
		
		return result;
	}	
}
