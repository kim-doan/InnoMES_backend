package com.innomes.main.repository.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import com.innomes.main.master.model.MST200;
import com.innomes.main.master.model.QMST110;
import com.innomes.main.master.model.QMST111;
import com.innomes.main.master.model.QMST200;
import com.innomes.main.master.model.QMST210;
import com.innomes.main.master.param.MasterManufactureProcessParam;
import com.innomes.main.repository.custom.MST200RepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class MST200RepositoryImpl extends QuerydslRepositorySupport implements MST200RepositoryCustom {

	public MST200RepositoryImpl() {
		super(MST200.class);
	}

	@Override
	public List<MST200> getManufactureProcess(MasterManufactureProcessParam masterManufactureProcessParam) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QMST200 mst200 = QMST200.mST200;
		QMST210 mst210 = QMST210.mST210;
		QMST111 mst111 = QMST111.mST111;
		QMST110 mst110 = QMST110.mST110;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(!StringUtils.isEmpty(masterManufactureProcessParam.getPrdtId())) {
			builder.and(mst200.prdtId.eq(masterManufactureProcessParam.getPrdtId()));
		}
		
		builder.and(mst200.used.eq(1));
		
		List<MST200> result = query.from(mst200)
				.select(mst200)
				.where(builder)
				.leftJoin(mst200.mst210, mst210)
				.fetchJoin()
				.leftJoin(mst200.mst111, mst111)
				.fetchJoin()
				.innerJoin(mst111.mst110, mst110)
				.fetchJoin()
				.fetch();
		
		return result;
	}

	@Override
	public Integer getMaxRoutingRev(String prdtId) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QMST200 mst200 = QMST200.mST200;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(mst200.prdtId.eq(prdtId));
		
		Integer result = query.from(mst200)
				.select(mst200.routingRev.max())
				.where(builder)
				.fetchOne();
		
		return result;
	}
}
