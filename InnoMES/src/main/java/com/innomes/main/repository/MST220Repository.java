package com.innomes.main.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.innomes.main.master.model.MST220;
import com.innomes.main.master.model.MST220PK;
import com.innomes.main.repository.custom.MST220RepositoryCustom;

public interface MST220Repository extends JpaRepository<MST220, MST220PK>, MST220RepositoryCustom {
	//투입 소재 조회 (BOM)
	List<MST220> findAllByPrdtIdAndProcCode(String prdtId, String procCode);
}
