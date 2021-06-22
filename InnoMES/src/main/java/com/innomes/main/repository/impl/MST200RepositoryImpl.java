package com.innomes.main.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import com.innomes.main.master.model.MST200;
import com.innomes.main.master.model.MST200PK;
import com.innomes.main.master.model.QMST110;
import com.innomes.main.master.model.QMST111;
import com.innomes.main.master.model.QMST200;
import com.innomes.main.master.model.QMST210;
import com.innomes.main.master.param.MasterManufactureProcessParam;
import com.innomes.main.repository.custom.MST200RepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class MST200RepositoryImpl extends QuerydslRepositorySupport implements MST200RepositoryCustom {

	public MST200RepositoryImpl() {
		super(MST200.class);
	}

	@Override
	public Optional<MST200> getManufactureProcess(MasterManufactureProcessParam masterManufactureProcessParam) {
//		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
//		
//		QMST200 mst200 = QMST200.mST200;
//		QMST210 mst210 = QMST210.mST210;
//		QMST111 mst111 = QMST111.mST111;
//		QMST110 mst110 = QMST110.mST110;
//		
//		BooleanBuilder builder = new BooleanBuilder();
//		
//		if(!StringUtils.isEmpty(masterManufactureProcessParam.getPrdtId())) {
//			builder.and(mst200.prdtId.eq(masterManufactureProcessParam.getPrdtId()));
//		}
//		
//		builder.and(mst200.used.eq(1));
//		
//		Optional<MST200> result = Optional.ofNullable(query.from(mst200)
//				.select(mst200)
//				.where(builder)
//				.leftJoin(mst200.mst210, mst210)
//				.fetchJoin()
//				.leftJoin(mst200.mst111, mst111)
//				.fetchJoin()
//				.innerJoin(mst111.mst110, mst110)
//				.fetchJoin()
//				.fetchOne());
//		
//		return result;
		return null;
	}

	@Override
	public List<MST200> getManufactureProcessRevLog(MasterManufactureProcessParam masterManufactureProcessParam) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QMST200 mst200 = QMST200.mST200;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(mst200.prdtId.eq(masterManufactureProcessParam.getPrdtId()));
		
		List<MST200> result = query.from(mst200)
				.select(mst200)
				.where(builder)
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

	@Override
	public Long delManufactureProcess(MST200PK pk, int used) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QMST200 mst200 = QMST200.mST200;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(mst200.prdtId.eq(pk.getPrdtId()));
		builder.and(mst200.routingRev.eq(pk.getRoutingRev()));
		
		return query.update(mst200).set(mst200.used, used).where(builder).execute();
	}
}
