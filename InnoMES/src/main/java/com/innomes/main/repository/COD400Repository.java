package com.innomes.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innomes.main.code.model.COD400;
import com.innomes.main.repository.custom.COD400RepositoryCustom;

public interface COD400Repository extends JpaRepository<COD400, String>, COD400RepositoryCustom {

}
