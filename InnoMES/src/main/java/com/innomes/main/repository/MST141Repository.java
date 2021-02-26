package com.innomes.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innomes.main.master.model.MST141;
import com.innomes.main.repository.custom.MST141RepositoryCustom;

public interface MST141Repository extends JpaRepository<MST141, String>, MST141RepositoryCustom {
	
}
