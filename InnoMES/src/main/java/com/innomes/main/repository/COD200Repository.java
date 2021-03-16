package com.innomes.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innomes.main.code.model.COD200;
import com.innomes.main.repository.custom.COD200RepositoryCustom;

public interface COD200Repository extends JpaRepository<COD200, String>, COD200RepositoryCustom {

}
