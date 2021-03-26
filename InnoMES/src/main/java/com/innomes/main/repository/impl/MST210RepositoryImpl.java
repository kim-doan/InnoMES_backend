package com.innomes.main.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import com.innomes.main.master.model.MST200;
import com.innomes.main.master.model.MST210;
import com.innomes.main.master.model.QMST200;
import com.innomes.main.master.model.QMST210;
import com.innomes.main.master.param.MasterManufactureProcessParam;
import com.innomes.main.repository.custom.MST210RepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class MST210RepositoryImpl extends QuerydslRepositorySupport implements MST210RepositoryCustom {

	public MST210RepositoryImpl() {
		super(MST210.class);
	}

}
