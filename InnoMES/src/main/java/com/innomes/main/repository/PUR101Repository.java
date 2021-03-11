package com.innomes.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innomes.main.purchase.model.PUR101;
import com.innomes.main.purchase.model.PUR101PK;
import com.innomes.main.repository.custom.PUR101RepositoryCustom;

public interface PUR101Repository extends JpaRepository<PUR101, PUR101PK>, PUR101RepositoryCustom{

}
