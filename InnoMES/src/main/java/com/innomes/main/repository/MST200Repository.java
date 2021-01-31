package com.innomes.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innomes.main.master.model.MST200;
import com.innomes.main.master.model.MST200PK;
import com.innomes.main.repository.custom.MST200RepositoryCustom;

public interface MST200Repository extends JpaRepository<MST200, MST200PK>, MST200RepositoryCustom {

}
