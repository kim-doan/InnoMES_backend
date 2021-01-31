package com.innomes.main.repository.impl;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import com.innomes.main.master.model.MST160;
import com.innomes.main.master.model.QMST160;
import com.innomes.main.master.param.MasterLocationParam;
import com.innomes.main.repository.custom.MST160RepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import io.netty.util.internal.StringUtil;

public class MST160RepositoryImpl extends QuerydslRepositorySupport implements MST160RepositoryCustom{

	public MST160RepositoryImpl() {
		super(MST160.class);
	}

	@Override
	public List<MST160> findAllLike(MasterLocationParam masterLocationParam) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QMST160 mst160 = QMST160.mST160;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(!StringUtils.isEmpty(masterLocationParam.getMst160().getLocName())){
			builder.and(mst160.locName.eq(masterLocationParam.getMst160().getLocName()));
		}
		builder.and(mst160.used.eq(1));
		
		
		return null;
	}

}
