package com.innomes.main.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.innomes.main.repository.custom.SYS800RepositoryCustom;
import com.innomes.main.system.model.QSYS800;
import com.innomes.main.system.model.SYS800;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class SYS800RepositoryImpl extends QuerydslRepositorySupport implements SYS800RepositoryCustom {

	public SYS800RepositoryImpl() {
		super(SYS800.class);
	}

	@Override
	public List<SYS800> findAll() {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QSYS800 sys800 = QSYS800.sYS800;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(sys800.used.eq(1));
		
		List<SYS800> result = query.from(sys800)
				.select(sys800)
				.innerJoin(sys800.roles)
				.fetchJoin()
				.where(builder)
				.fetch();
		
		return result;
	}
	
	@Override
	public Optional<SYS800> findByIdCustom(String userNo) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QSYS800 sys800 = QSYS800.sYS800;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(sys800.userNo.eq(userNo));
		
		Optional<SYS800> result = Optional.ofNullable(query.from(sys800)
				.select(sys800)
				.innerJoin(sys800.roles)
				.fetchJoin()
				.where(builder)
				.fetchOne());
				
		return result;
	}
	
	@Override
	public Optional<SYS800> findByLoginUserId(String userId) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QSYS800 sys800 = QSYS800.sYS800;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(sys800.userId.eq(userId));
		builder.and(sys800.used.eq(1));
		
		Optional<SYS800> result = Optional.ofNullable(query.from(sys800)
				.select(sys800)
				.where(builder)
				.fetchOne());
		
		return result;
	}

	@Override
	public long delAccount(String userNo, int used) {
		JPAQueryFactory query = new JPAQueryFactory(this.getEntityManager());
		
		QSYS800 sys800 = QSYS800.sYS800;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(sys800.userNo.eq(userNo));
		
		return query.update(sys800).set(sys800.used, used).where(builder).execute();
	}
}
