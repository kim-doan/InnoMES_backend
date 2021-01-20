package com.innomes.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innomes.main.repository.custom.SAL101RepositoryCustom;
import com.innomes.main.sales.model.SAL101;
import com.innomes.main.sales.model.SAL101PK;

public interface SAL101Repository extends JpaRepository<SAL101, SAL101PK>, SAL101RepositoryCustom {
	
}
