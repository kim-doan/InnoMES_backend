package com.innomes.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innomes.main.repository.custom.SAL110RepositoryCustom;
import com.innomes.main.sales.model.SAL110;

public interface SAL110Repository extends JpaRepository<SAL110, Integer>, SAL110RepositoryCustom {

}
