package com.innomes.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innomes.main.master.model.MST150;
import com.innomes.main.repository.custom.MST150RepositoryCustom;

public interface MST150Repository extends JpaRepository<MST150, String>, MST150RepositoryCustom {

}
