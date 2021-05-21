package com.innomes.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innomes.main.purchase.model.PUR110;
import com.innomes.main.repository.custom.PUR110RepositoryCustom;

public interface PUR110Repository extends JpaRepository<PUR110, String>, PUR110RepositoryCustom {

}
