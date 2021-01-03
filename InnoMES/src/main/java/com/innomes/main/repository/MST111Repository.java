package com.innomes.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innomes.main.master.model.MST110;
import com.innomes.main.master.model.MST111;
import com.innomes.main.repository.custom.MST111RepositoryCustom;

public interface MST111Repository extends JpaRepository<MST111, String>, MST111RepositoryCustom {

}
