package com.innomes.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innomes.main.master.model.MST210;
import com.innomes.main.master.model.MST210PK;
import com.innomes.main.repository.custom.MST210RepositoryCustom;

public interface MST210Repository extends JpaRepository<MST210, MST210PK>, MST210RepositoryCustom {

}
