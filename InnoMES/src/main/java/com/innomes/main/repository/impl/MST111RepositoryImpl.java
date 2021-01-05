package com.innomes.main.repository.impl;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.master.dto.MasterProductDTO;
import com.innomes.main.master.model.MST111;
import com.innomes.main.master.model.QMST110;
import com.innomes.main.master.model.QMST111;
import com.innomes.main.master.param.MasterItemParam;
import com.innomes.main.repository.custom.MST111RepositoryCustom;
import com.microsoft.sqlserver.jdbc.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;


public class MST111RepositoryImpl extends QuerydslRepositorySupport implements MST111RepositoryCustom {
	public MST111RepositoryImpl() {
		super(MST111.class);
	}

	@Override
	public List<MST111> findAllLike(MasterItemParam masterItemParam, Pageable pageable) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QMST111 mst111 = QMST111.mST111;
		QMST110 mst110 = QMST110.mST110;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(!StringUtils.isEmpty(masterItemParam.getItemId())) {
			builder.and(mst110.itemId.like("%" + masterItemParam.getItemId() + "%"));
		}
		
		//mst110
		if(!StringUtils.isEmpty(masterItemParam.getItemCode())) {
			builder.and(mst110.itemCode.like("%" + masterItemParam.getItemCode() + "%"));
		}
		if(!StringUtils.isEmpty(masterItemParam.getItemName())) {
			builder.and(mst110.itemName.like("%" + masterItemParam.getItemName() + "%"));
		}
		if(!StringUtils.isEmpty(masterItemParam.getItemType())) {
			builder.and(mst110.itemType.like("%" + masterItemParam.getItemType() + "%"));
		}
		if(masterItemParam.getInvType() != null) {
			builder.and(mst110.invType.eq(masterItemParam.getInvType()));
		}
		
		//mst111
		if(!StringUtils.isEmpty(masterItemParam.getPrdtType())) {
			builder.and(mst111.prdtType.like("%" + masterItemParam.getPrdtType() + "%"));
		}
		if(!StringUtils.isEmpty(masterItemParam.getPrdtCtg())) {
			builder.and(mst111.prdtCtg.like("%" + masterItemParam.getPrdtCtg() + "%"));
		}
		if(!StringUtils.isEmpty(masterItemParam.getPrdtGroup())) {
			builder.and(mst111.prdtGroup.like("%" + masterItemParam.getPrdtGroup() + "%"));
		}
		
		
		 List<MST111> result = query.from(mst111)
		.select(mst111)
		.innerJoin(mst111.mst110, mst110)
		.fetchJoin()
		.where(builder)
		.offset(pageable.getOffset())
		.limit(pageable.getPageSize())
		.fetch();
		
		return result;
	}
}
