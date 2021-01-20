package com.innomes.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innomes.main.repository.custom.SAL111RepositoryCustom;
import com.innomes.main.sales.model.SAL111;
import com.innomes.main.sales.model.SAL111PK;

public interface SAL111Repository extends JpaRepository<SAL111, SAL111PK>, SAL111RepositoryCustom {

}
