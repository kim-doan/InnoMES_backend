package com.innomes.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innomes.main.master.model.MST113;
import com.innomes.main.repository.custom.MST113RepositoryCustom;

public interface MST113Repository extends JpaRepository<MST113, String>, MST113RepositoryCustom{

}
