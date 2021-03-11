package com.innomes.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innomes.main.purchase.model.PUR100;
import com.innomes.main.repository.custom.PUR100RepositoryCustom;

public interface PUR100Repository extends JpaRepository<PUR100, String>, PUR100RepositoryCustom {

}
