package com.innomes.main.repository.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.master.model.MST112;
import com.innomes.main.master.model.QMST110;
import com.innomes.main.master.model.QMST112;
import com.innomes.main.master.param.MasterMaterialParam;
import com.innomes.main.repository.custom.MST112RepositoryCustom;
import com.microsoft.sqlserver.jdbc.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class MST112RepositoryImpl extends QuerydslRepositorySupport implements MST112RepositoryCustom {
	
	public MST112RepositoryImpl() {
		super(MST112.class);
	}
	
	@Override
	public Page<MST112> findAllLike(MasterMaterialParam masterMaterialParam, Pageable pageable){
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QMST112 mst112 = QMST112.mST112;
		QMST110 mst110 = QMST110.mST110;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(!StringUtils.isEmpty(masterMaterialParam.getItemId())) {
			builder.and(mst110.itemId.like("%" + masterMaterialParam.getItemId() + "%"));
		}
		
		//MST110
		if(!StringUtils.isEmpty(masterMaterialParam.getItemCode())) {
			builder.and(mst110.itemCode.like("%" + masterMaterialParam.getItemCode() + "%"));
		}
		if(!StringUtils.isEmpty(masterMaterialParam.getItemName())) {
			builder.and(mst110.itemName.like("%" + masterMaterialParam.getItemName() + "%"));
		}
		if(!StringUtils.isEmpty(masterMaterialParam.getItemType())) {
			builder.and(mst110.itemType.like("%" + masterMaterialParam.getItemType() + "%"));
		}
		if(masterMaterialParam.getInvType() != null) {
			builder.and(mst110.invType.eq(masterMaterialParam.getInvType()));
		}
		
		//MST112
		if(!StringUtils.isEmpty(masterMaterialParam.getMatType())) {
			builder.and(mst112.matType.like("%" + masterMaterialParam.getMatType() + "%"));
		}
		if(!StringUtils.isEmpty(masterMaterialParam.getMatCtg())) {
			builder.and(mst112.matCtg.like("%" + masterMaterialParam.getMatCtg() + "%"));
		}
		if(!StringUtils.isEmpty(masterMaterialParam.getMatGroup())) {
			builder.and(mst112.matGroup.like("%" + masterMaterialParam.getMatGroup() + "%"));
		}
		if(!StringUtils.isEmpty(masterMaterialParam.getAttMatType())) {
			builder.and(mst112.attMatType.like("%" + masterMaterialParam.getAttMatType() + "%"));
		}
		if(!StringUtils.isEmpty(masterMaterialParam.getAttStdType())) {
			builder.and(mst112.attStdType.like("%" + masterMaterialParam.getAttStdType() + "%"));
		}
		if(!StringUtils.isEmpty(masterMaterialParam.getAttDiaType())) {
			builder.and(mst112.attDiaType.like("%" + masterMaterialParam.getAttDiaType() + "%"));
		}
		if(!StringUtils.isEmpty(masterMaterialParam.getMatProc())) {
			builder.and(mst112.matProc.like("%" + masterMaterialParam.getMatProc() + "%"));
		}
		
		builder.and(mst112.mst110.used.eq(1));
		
		QueryResults<MST112> result = query.from(mst112)
				.select(mst112)
				.innerJoin(mst112.mst110, mst110)
				.fetchJoin()
				.where(builder)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetchResults();
		
		return new PageImpl<MST112>(result.getResults(), pageable, result.getTotal());
	}

	@Override
	public List<MST112> findAll() {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QMST112 mst112 = QMST112.mST112;
		QMST110 mst110 = QMST110.mST110;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		//builder.and(mst112.mst110.used.eq(1));
		
		List<MST112> result = query.from(mst112)
				.select(mst112)
				.innerJoin(mst112.mst110, mst110)
				.fetchJoin()
				.where(builder)
				.fetch();
		
		return result;
	}
}
