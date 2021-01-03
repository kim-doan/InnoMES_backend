package com.innomes.main.repository.impl;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.master.model.MST111;
import com.innomes.main.master.model.QMST110;
import com.innomes.main.master.model.QMST111;
import com.innomes.main.master.param.MasterItemParam;
import com.innomes.main.repository.custom.MST111RepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;


public class MST111RepositoryImpl extends QuerydslRepositorySupport implements MST111RepositoryCustom {
	public MST111RepositoryImpl() {
		super(MST111.class);
	}

	@Override
	public List<MST111> findAllLike(MasterItemParam masterProductParam) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QMST111 mst111 = QMST111.mST111;
		QMST110 mst110 = QMST110.mST110;
		
		
		 List<MST111> mst111List = query.from(mst111)
		.select(mst111)
		.innerJoin(mst111.mst110, mst110)
		.fetchJoin()
//		.where(builder)
		.fetch();
		
		return mst111List;
	}
}
