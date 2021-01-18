package com.innomes.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innomes.main.repository.custom.SAL100RepositoryCustom;
import com.innomes.main.sales.model.SAL100;

public interface SAL100Repository extends JpaRepository<SAL100, Integer>, SAL100RepositoryCustom {

}
