package com.innomes.main.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import com.innomes.main.master.model.MST200;
import com.innomes.main.master.model.MST200PK;
import com.innomes.main.master.model.MST210;
import com.innomes.main.master.model.MST210PK;
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

	@Override
	public long delManufactureRoute(String prdtId, int used) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QMST210 mst210 = QMST210.mST210;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(mst210.prdtId.eq(prdtId));
		
		return query.update(mst210).set(mst210.used, used).where(builder).execute();
	}
}
