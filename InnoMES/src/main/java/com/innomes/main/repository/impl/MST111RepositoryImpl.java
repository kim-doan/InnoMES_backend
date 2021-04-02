package com.innomes.main.repository.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.master.model.MST111;
import com.innomes.main.master.model.QMST110;
import com.innomes.main.master.model.QMST111;
import com.innomes.main.master.model.QMST120;
import com.innomes.main.master.model.QMST200;
import com.innomes.main.master.model.QMST210;
import com.innomes.main.master.param.MasterProductParam;
import com.innomes.main.repository.custom.MST111RepositoryCustom;
import com.microsoft.sqlserver.jdbc.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;


public class MST111RepositoryImpl extends QuerydslRepositorySupport implements MST111RepositoryCustom {
	public MST111RepositoryImpl() {
		super(MST111.class);
	}

	@Override
	public Page<MST111> findAllLike(MasterProductParam masterProductParam, Pageable pageable) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QMST111 mst111 = QMST111.mST111;
		QMST110 mst110 = QMST110.mST110;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(!StringUtils.isEmpty(masterProductParam.getItemId())) {
			builder.and(mst110.itemId.like("%" + masterProductParam.getItemId() + "%"));
		}
		
		//mst110
		if(!StringUtils.isEmpty(masterProductParam.getItemCode())) {
			builder.and(mst110.itemCode.like("%" + masterProductParam.getItemCode() + "%"));
		}
		if(!StringUtils.isEmpty(masterProductParam.getItemName())) {
			builder.and(mst110.itemName.like("%" + masterProductParam.getItemName() + "%"));
		}
		if(!StringUtils.isEmpty(masterProductParam.getItemType())) {
			builder.and(mst110.itemType.like("%" + masterProductParam.getItemType() + "%"));
		}
		if(masterProductParam.getInvType() != null) {
			builder.and(mst110.invType.eq(masterProductParam.getInvType()));
		}
		
		//mst111
		if(!StringUtils.isEmpty(masterProductParam.getPrdtType())) {
			builder.and(mst111.prdtType.like("%" + masterProductParam.getPrdtType() + "%"));
		}
		if(!StringUtils.isEmpty(masterProductParam.getPrdtCtg())) {
			builder.and(mst111.prdtCtg.like("%" + masterProductParam.getPrdtCtg() + "%"));
		}
		if(!StringUtils.isEmpty(masterProductParam.getPrdtGroup())) {
			builder.and(mst111.prdtGroup.like("%" + masterProductParam.getPrdtGroup() + "%"));
		}
		
		builder.and(mst111.mst110.used.eq(1));
		
		 QueryResults<MST111> result = query.from(mst111)
		.select(mst111)
		.innerJoin(mst111.mst110, mst110)
		.fetchJoin()
		.where(builder)
		.offset(pageable.getOffset())
		.limit(pageable.getPageSize())
		.orderBy(mst111.mst110.createTime.desc())
		.fetchResults();
		
		return new PageImpl<>(result.getResults(), pageable, result.getTotal());
	}

	@Override
	public List<MST111> findAll() {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QMST111 mst111 = QMST111.mST111;
		QMST110 mst110 = QMST110.mST110;
		
		BooleanBuilder builder = new BooleanBuilder();
		
//		builder.and(mst111.mst110.used.eq(1));
		
		 List<MST111> result = query.from(mst111)
		.select(mst111)
		.innerJoin(mst111.mst110, mst110)
		.fetchJoin()
		.where(builder)
		.fetch();
		 
		return result;
	}
	
	@Override
	public Page<MST111> getManufactureItem(MasterProductParam masterProductParam, Pageable pageable) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		QMST200 mst200 = QMST200.mST200;
		QMST210 mst210 = QMST210.mST210;
		QMST110 mst110 = QMST110.mST110;
		QMST111 mst111 = QMST111.mST111;
		QMST120 mst120 = QMST120.mST120;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(!StringUtils.isEmpty(masterProductParam.getPrdtId())) {
			builder.and(mst111.prdtId.eq(masterProductParam.getPrdtId()));
		}
		
		if(!StringUtils.isEmpty(masterProductParam.getItemCode())) {
			builder.and(mst111.mst110.itemCode.like("%" + masterProductParam.getItemCode() + "%"));
		}
		
		if(!StringUtils.isEmpty(masterProductParam.getItemName())) {
			builder.and(mst111.mst110.itemName.like("%" + masterProductParam.getItemName() + "%"));
		}
		
		if(!StringUtils.isEmpty(masterProductParam.getPrdtType())) {
			builder.and(mst111.prdtType.eq(masterProductParam.getPrdtType()));
		}
		
		if(!StringUtils.isEmpty(masterProductParam.getPrdtCtg())) {
			builder.and(mst111.prdtCtg.eq(masterProductParam.getPrdtCtg()));
		}
		
		if(!StringUtils.isEmpty(masterProductParam.getPrdtGroup())) {
			builder.and(mst111.prdtGroup.eq(masterProductParam.getPrdtGroup()));
		}
		
		if(!StringUtils.isEmpty(masterProductParam.getPrdtStatus())) {
			builder.and(mst200.prdtStatus.eq(masterProductParam.getPrdtStatus()));
		}
		
		if(!StringUtils.isEmpty(masterProductParam.getAttMatType())) {
			builder.and(mst111.attMatType.eq(masterProductParam.getAttMatType()));
		}
		
		if(!StringUtils.isEmpty(masterProductParam.getAttStdType())) {
			builder.and(mst111.attStdType.eq(masterProductParam.getAttStdType()));
		}
		
		if(!StringUtils.isEmpty(masterProductParam.getAttDiaType())) {
			builder.and(mst111.attDiaType.eq(masterProductParam.getAttDiaType()));
		}
		
		if(!StringUtils.isEmpty(masterProductParam.getHeatSpec())) {
			builder.and(mst111.heatSpec.eq(masterProductParam.getHeatSpec()));
		}
		
		if(!StringUtils.isEmpty(masterProductParam.getSurfaceSpec())) {
			builder.and(mst111.surfaceSpec.eq(masterProductParam.getSurfaceSpec()));
		}
		
		if(!StringUtils.isEmpty(masterProductParam.getCoatingSpec())) {
			builder.and(mst111.coatingSpec.eq(masterProductParam.getCoatingSpec()));
		}
		
		builder.and(mst200.used.eq(1).or(mst200.prdtId.isNull()));
		builder.and(mst111.mst110.used.eq(1));
		
		QueryResults<MST111> result = query.selectDistinct(mst111)
				.from(mst111)
				.innerJoin(mst111.mst110, mst110)
				.fetchJoin()
				.leftJoin(mst111.mst210, mst210)
				.leftJoin(mst210.mst200, mst200)
				.leftJoin(mst210.mst120, mst120)
				.where(builder)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetchResults();
		
		return new PageImpl<>(result.getResults(), pageable, result.getTotal());
	}
}
