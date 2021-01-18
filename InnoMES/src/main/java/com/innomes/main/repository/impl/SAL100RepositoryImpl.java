package com.innomes.main.repository.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.master.model.QMST110;
import com.innomes.main.repository.custom.SAL100RepositoryCustom;
import com.innomes.main.sales.model.QSAL100;
import com.innomes.main.sales.model.QSAL101;
import com.innomes.main.sales.model.SAL100;
import com.innomes.main.sales.model.SAL101;
import com.innomes.main.sales.param.SalesPlanParam;
import com.microsoft.sqlserver.jdbc.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class SAL100RepositoryImpl extends QuerydslRepositorySupport implements SAL100RepositoryCustom {

	public SAL100RepositoryImpl() {
		super(SAL100.class);
	}
}
