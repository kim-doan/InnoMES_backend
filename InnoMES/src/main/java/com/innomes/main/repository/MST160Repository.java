package com.innomes.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innomes.main.master.model.MST160;
import com.innomes.main.repository.custom.MST160RepositoryCustom;

public interface MST160Repository extends JpaRepository<MST160, String>, MST160RepositoryCustom {

}
