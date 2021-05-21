package com.innomes.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innomes.main.purchase.model.PUR111;
import com.innomes.main.purchase.model.PUR111PK;
import com.innomes.main.repository.custom.PUR111RepositoryCustom;

public interface PUR111Repository extends JpaRepository<PUR111, PUR111PK>, PUR111RepositoryCustom {

}
