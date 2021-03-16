package com.innomes.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innomes.main.code.model.COD300;
import com.innomes.main.repository.custom.COD300RepositoryCustom;

public interface COD300Repository extends JpaRepository<COD300, String>, COD300RepositoryCustom {

}
