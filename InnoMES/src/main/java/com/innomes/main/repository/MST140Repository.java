package com.innomes.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.innomes.main.master.model.MST140;

import io.lettuce.core.dynamic.annotation.Param;

public interface MST140Repository extends JpaRepository<MST140, String> {
	
	@Query("select a "
			+ "from MST140 a "
			+ "where a.used != 1")
	List<MST140> findUsedAll();
	List<MST140> findByUserNoLike(String userNo);
	List<MST140> findByUsernameLike(String username);
	List<MST140> findByTeamCodeLike(String teamCode);
	
	//유저 아이디로 조회
	Optional<MST140> findByUsername(String username);
	
	//로그인 용 유저 아이디 조회
	@Query("select a "
			+ "from MST140 a "
			+ "where a.used = 0 and a.username = :username")
	Optional<MST140> findByLoginUsername(@Param("username") String username);
}
