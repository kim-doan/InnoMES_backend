package com.innomes.main.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.master.model.MST151;
import com.innomes.main.master.model.QMST151;
import com.innomes.main.master.param.MasterPriceParam;
import com.microsoft.sqlserver.jdbc.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class MST151RepositoryImpl extends QuerydslRepositorySupport implements MST151RepositoryCustom{
	public MST151RepositoryImpl() {
		super(MST151.class);
	}
	
	@Override
	public Page<MST151> findAllLike(MasterPriceParam masterPriceParam, Pageable pageable){
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QMST151 mst151 = QMST151.mST151;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(!StringUtils.isEmpty(masterPriceParam.getItemId())) {
			builder.and(mst151.itemId.like("%" + masterPriceParam.getItemId() + "%"));
		}
		if(!StringUtils.isEmpty(masterPriceParam.getCompId())) {
			builder.and(mst151.compId.like("%" + masterPriceParam.getCompId() + "%"));
		}
		
		QueryResults<MST151> result = query.from(mst151)
				.select(mst151)
				.where(builder)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetchResults();
		
		return new PageImpl<>(result.getResults(), pageable, result.getTotal());
		
	}
}
