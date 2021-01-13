package com.innomes.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innomes.main.code.model.COD100;
import com.innomes.main.repository.custom.COD100RepositoryCustom;

public interface COD100Repository extends JpaRepository<COD100, String>, COD100RepositoryCustom {

}
