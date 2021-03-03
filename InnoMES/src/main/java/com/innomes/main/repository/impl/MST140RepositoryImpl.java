package com.innomes.main.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.master.model.MST140;
import com.innomes.main.master.model.QMST140;
import com.innomes.main.master.model.QMST141;
import com.innomes.main.master.model.QMST142;
import com.innomes.main.master.param.MasterUserParam;
import com.innomes.main.repository.custom.MST140RepositoryCustom;
import com.innomes.main.system.model.QSYS800;
import com.innomes.main.system.model.SYS810;
import com.microsoft.sqlserver.jdbc.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class MST140RepositoryImpl extends QuerydslRepositorySupport implements MST140RepositoryCustom {
	public MST140RepositoryImpl() {
		super(MST140.class);
	}
	
	@Override
	public Page<MST140> findAllLike(MasterUserParam masterUserParam, Pageable pageable){
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		QMST140 mst140 = QMST140.mST140;
		QSYS800 sys800 = QSYS800.sYS800;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(!StringUtils.isEmpty(masterUserParam.getUserNo())) {
			builder.and(mst140.userNo.like(masterUserParam.getUserNo()));
		}
		if(!StringUtils.isEmpty(masterUserParam.getUserId())) {
			builder.and(sys800.userId.like("%" + masterUserParam.getUserId() + "%"));
		}
		if(!StringUtils.isEmpty(masterUserParam.getUserType())) {
			builder.and(mst140.userType.like("%" + masterUserParam.getUserType() + "%"));
		}
		if(!StringUtils.isEmpty(masterUserParam.getUserName())) {
			builder.and(mst140.userName.like("%" + masterUserParam.getUserName() + "%"));
		}
		if(!StringUtils.isEmpty(masterUserParam.getDepartmentCode())) {
			builder.and(mst140.departmentCode.like("%" + masterUserParam.getDepartmentCode() + "%"));
		}
		if(!StringUtils.isEmpty(masterUserParam.getGradeCode())) {
			builder.and(mst140.gradeCode.like("%" + masterUserParam.getGradeCode() + "%"));
		}
		if(!StringUtils.isEmpty(masterUserParam.getTeamCode())) {
			builder.and(mst140.teamCode.like("%" + masterUserParam.getTeamCode() + "%"));
		}
		
//		builder.and(qmst140.used.eq(0));
		
		 QueryResults<MST140> result = query.from(mst140)
				.select(mst140)
				.where(builder)
				.innerJoin(mst140.sys800, sys800)
				.fetchJoin()
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.orderBy(mst140.createTime.desc(), mst140.userNo.asc())
				.fetchResults();
		
		return new PageImpl<>(result.getResults(), pageable, result.getTotal());
	}
	
	@Override
	public List<MST140> findAll() {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		QMST140 mst140 = QMST140.mST140;
		QMST141	mst141	= QMST141.mST141;
		QSYS800 sys800 = QSYS800.sYS800;
		
		 List<MST140> result = query.from(mst140)
					.select(mst140)
					.innerJoin(mst140.sys800, sys800)
					.fetchJoin()
					.innerJoin(mst140.sys800.roles)
					.fetchJoin()
					.leftJoin(mst140.mst141, mst141)
					.fetchJoin()
					.fetch().stream().distinct().collect(Collectors.toList());
		 
		 return result;
	}

	@Override
	public Optional<MST140> findByIdCustom(String userNo) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		QMST140 mst140 = QMST140.mST140;
		QMST141	mst141	= QMST141.mST141;
		QSYS800 sys800 = QSYS800.sYS800;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(mst140.userNo.eq(userNo));
		
		Optional<MST140> result = Optional.ofNullable(query.from(mst140)
				.select(mst140)
				.where(builder)
				.leftJoin(mst140.sys800, sys800)
				.fetchJoin()
				.leftJoin(sys800.roles)
				.fetchJoin()
				.leftJoin(mst140.mst141, mst141)
				.fetchJoin()
				.fetchOne());
		
		return result;
	}
}
