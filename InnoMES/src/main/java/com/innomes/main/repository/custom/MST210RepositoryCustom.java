package com.innomes.main.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.innomes.main.master.model.MST200PK;
import com.innomes.main.master.model.MST210;
import com.innomes.main.master.model.MST210PK;
import com.innomes.main.master.model.QMST200;
import com.innomes.main.master.param.MasterManufactureProcessParam;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

public interface MST210RepositoryCustom {

	long delManufactureRoute(String prdtId, int used);
}
