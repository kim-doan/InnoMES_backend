package com.innomes.main.repository.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.master.model.MST151;
import com.innomes.main.master.model.QMST110;
import com.innomes.main.master.model.QMST151;
import com.innomes.main.master.param.MasterPriceItemParam;
import com.innomes.main.master.param.MasterPriceParam;
import com.innomes.main.repository.custom.MST151RepositoryCustom;
import com.microsoft.sqlserver.jdbc.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class MST151RepositoryImpl extends QuerydslRepositorySupport implements MST151RepositoryCustom{
	public MST151RepositoryImpl() {
		super(MST151.class);
	}
	
	@Override
	public Page<MST151> findAllLike(MasterPriceItemParam masterPriceParam, Pageable pageable){
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QMST151 mst151 = QMST151.mST151;
		QMST151 mst151B = new QMST151("mst151B");
		QMST110 mst110 = QMST110.mST110;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(!StringUtils.isEmpty(masterPriceParam.getItemId())) {
			builder.and(mst151.itemId.like("%" + masterPriceParam.getItemId() + "%"));
		}
//		if(!StringUtils.isEmpty(masterPriceParam.getCompId())) {
//			builder.and(mst151.compId.like("%" + masterPriceParam.getCompId() + "%"));
//		}
		
		builder.and(mst151.priceType.eq(masterPriceParam.getPriceType()));
		builder.and(mst151B.compId.isNull());
		builder.and(mst151.used.eq(1));
		
		QueryResults<MST151> result = query.from(mst151)
				.select(mst151)
				.innerJoin(mst151.mst110, mst110)
				.fetchJoin()
				.leftJoin(mst151B).on(mst151B.compId.eq(mst151.compId)
						.and(mst151B.itemId.eq(mst151.itemId))
						.and(mst151B.priceType.eq(mst151.priceType))
						.and(mst151B.priceRev.gt(mst151.priceRev)))
				.fetchJoin()
				.where(builder)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetchResults();
		
		return new PageImpl<>(result.getResults(), pageable, result.getTotal());
		
	}

	@Override
	public List<MST151> findPriceRevList(String priceType) {
		
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QMST151 mst151 = QMST151.mST151;
		QMST151 mst151B = new QMST151("mst151B");
		
		BooleanBuilder builder = new BooleanBuilder();
		if(!StringUtils.isEmpty(priceType)) {
			builder.and(mst151.priceType.eq(priceType));
		}
		builder.and(mst151B.compId.isNull());
		builder.and(mst151.used.eq(0));
		
		return query.from(mst151)
				.select(mst151)
				.leftJoin(mst151B).on(mst151B.compId.eq(mst151.compId)
						.and(mst151B.itemId.eq(mst151.itemId))
						.and(mst151B.priceType.eq(mst151.priceType))
						.and(mst151B.priceRev.gt(mst151.priceRev)))
				.fetchJoin()
				.where(builder)
				.fetch();
	}
}
