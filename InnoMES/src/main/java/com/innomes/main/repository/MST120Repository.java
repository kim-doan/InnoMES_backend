package com.innomes.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innomes.main.master.model.MST120;
import com.innomes.main.repository.custom.MST120RepositoryCustom;

public interface MST120Repository extends JpaRepository<MST120, String>, MST120RepositoryCustom {

}
