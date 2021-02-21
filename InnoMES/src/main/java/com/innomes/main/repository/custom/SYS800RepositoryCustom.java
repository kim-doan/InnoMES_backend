package com.innomes.main.repository.custom;

import java.util.List;
import java.util.Optional;

import com.innomes.main.system.model.SYS800;

public interface SYS800RepositoryCustom {
	//전체조회
	List<SYS800> findAll();
	
	//단건조회
	Optional<SYS800> findByIdCustom(String userNo);
	
	//로그인 가능한 사용자 조회
	Optional<SYS800> findByLoginUserId(String userId);
	
	// 계정 삭제
	public long delAccount(String userNo, int used);
}
