package com.innomes.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innomes.main.repository.custom.SYS800RepositoryCustom;
import com.innomes.main.system.model.SYS800;

public interface SYS800Repository extends JpaRepository<SYS800, String>, SYS800RepositoryCustom {
	// 유저 아이디로 조회
	Optional<SYS800> findByUserId(String userId);
}
