package com.innomes.main.repository.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.master.model.MST114;
import com.innomes.main.master.model.QMST110;
import com.innomes.main.master.model.QMST114;
import com.innomes.main.master.param.MasterSpareParam;
import com.innomes.main.repository.custom.MST114RepositoryCustom;
import com.microsoft.sqlserver.jdbc.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class MST114RepositoryImpl extends QuerydslRepositorySupport implements MST114RepositoryCustom{
	public MST114RepositoryImpl() {
		super(MST114.class);
	}
	
	@Override
	public Page<MST114> findAllLike(MasterSpareParam masterSpareParam, Pageable pageable) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QMST114 mst114 = QMST114.mST114;
		QMST110 mst110 = QMST110.mST110;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(!StringUtils.isEmpty(masterSpareParam.getItemId())) {
			builder.and(mst110.itemId.like("%" + masterSpareParam.getItemId() + "%"));
		}
		
		//MST110
		if(!StringUtils.isEmpty(masterSpareParam.getItemCode())) {
			builder.and(mst110.itemCode.like("%" + masterSpareParam.getItemCode() + "%"));
		}
		if(!StringUtils.isEmpty(masterSpareParam.getItemName())) {
			builder.and(mst110.itemName.like("%" + masterSpareParam.getItemName() + "%"));
		}
		if(!StringUtils.isEmpty(masterSpareParam.getItemType())) {
			builder.and(mst110.itemType.like("%" + masterSpareParam.getItemType() + "%"));
		}
		if(masterSpareParam.getInvType() != null) {
			builder.and(mst110.invType.eq(masterSpareParam.getInvType()));
		}
		
		//MST114
		if(!StringUtils.isEmpty(masterSpareParam.getPartType())) {
			builder.and(mst114.partType.like("%" + masterSpareParam.getPartType() + "%"));
		}
		if(!StringUtils.isEmpty(masterSpareParam.getPartCtg())) {
			builder.and(mst114.partCtg.like("%" + masterSpareParam.getPartCtg() + "%"));
		}
		if(!StringUtils.isEmpty(masterSpareParam.getPartGroup())) {
			builder.and(mst114.partGroup.like("%" + masterSpareParam.getPartGroup() + "%"));
		}
		
		builder.and(mst114.mst110.used.eq(1));
		
		QueryResults<MST114> result = query.from(mst114)
				.select(mst114)
				.innerJoin(mst114.mst110,mst110)
				.fetchJoin()
				.where(builder)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetchResults();
		
		return new PageImpl<>(result.getResults(), pageable, result.getTotal());
	}

	@Override
	public List<MST114> findAll() {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QMST114 mst114 = QMST114.mST114;
		QMST110 mst110 = QMST110.mST110;
		
		BooleanBuilder builder = new BooleanBuilder();
		
//		builder.and(mst114.mst110.used.eq(1));
		
		List<MST114> result = query.from(mst114)
				.select(mst114)
				.innerJoin(mst114.mst110,mst110)
				.fetchJoin()
				.where(builder)
				.fetch();
		
		return result;
	}
}
