package com.innomes.main.repository.impl;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.master.model.MST111;
import com.innomes.main.master.model.MST113;
import com.innomes.main.master.model.QMST110;
import com.innomes.main.master.model.QMST113;
import com.innomes.main.master.param.MasterToolParam;
import com.innomes.main.repository.custom.MST113RepositoryCustom;
import com.microsoft.sqlserver.jdbc.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class MST113RepositoryImpl extends QuerydslRepositorySupport implements MST113RepositoryCustom {

	public MST113RepositoryImpl() {
		super(MST111.class);
	}

	@Override
	public Page<MST113> findAllLike(MasterToolParam masterToolParam, Pageable pageable) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QMST113 mst113 = QMST113.mST113;
		QMST110 mst110 = QMST110.mST110;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		//mst110
		if(!StringUtils.isEmpty(masterToolParam.getItemCode())) {
			builder.and(mst110.itemCode.like("%" + masterToolParam.getItemCode() + "%"));
		}
		if(!StringUtils.isEmpty(masterToolParam.getItemName())) {
			builder.and(mst110.itemName.like("%" + masterToolParam.getItemName() + "%"));
		}
		if(!StringUtils.isEmpty(masterToolParam.getItemType())) {
			builder.and(mst110.itemType.like("%" + masterToolParam.getItemType() + "%"));
		}
		if(masterToolParam.getInvType() != 0) {
			builder.and(mst110.invType.eq(masterToolParam.getInvType()));
		}
		//mst113
		if (!StringUtils.isEmpty(masterToolParam.getToolType())) {
			builder.and(mst113.toolType.like("%" + masterToolParam.getToolType() + "%"));
		}
		if (!StringUtils.isEmpty(masterToolParam.getToolCtg())) {
			builder.and(mst113.toolCtg.like("%" + masterToolParam.getToolCtg() + "%"));
		}
		if (!StringUtils.isEmpty(masterToolParam.getToolGroup())) {
			builder.and(mst113.toolGroup.like("%" + masterToolParam.getToolGroup() + "%"));
		}
		
		builder.and(mst113.mst110.used.eq(1));
		
		QueryResults<MST113> result = query.from(mst113)
				.select(mst113)
				.innerJoin(mst113.mst110, mst110)
				.fetchJoin()
				.where(builder)
				.orderBy(mst113.mst110.createTime.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetchResults();
		
		return new PageImpl<>(result.getResults(), pageable, result.getTotal());
	}

	@Override
	public List<MST113> findAll() {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QMST113 mst113 = QMST113.mST113;
		QMST110 mst110 = QMST110.mST110;
		
		BooleanBuilder builder = new BooleanBuilder();
		
//		builder.and(mst113.mst110.used.eq(1));
		
		List<MST113> result = query.from(mst113)
				.select(mst113)
				.innerJoin(mst113.mst110, mst110)
				.fetchJoin()
				.where(builder)
				.fetch();
		
		return result;
	}

}
