package com.innomes.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innomes.main.master.model.MST151;
import com.innomes.main.repository.custom.MST151RepositoryCustom;

public interface MST151Repository extends JpaRepository<MST151, String>, MST151RepositoryCustom{

}
