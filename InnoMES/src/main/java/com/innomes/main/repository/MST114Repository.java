package com.innomes.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innomes.main.master.model.MST114;
import com.innomes.main.repository.custom.MST114RepositoryCustom;

public interface MST114Repository extends JpaRepository<MST114, String>, MST114RepositoryCustom {

}
