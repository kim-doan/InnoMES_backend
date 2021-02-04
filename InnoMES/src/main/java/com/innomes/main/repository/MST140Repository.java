package com.innomes.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.innomes.main.master.model.MST140;
import com.innomes.main.repository.custom.MST140RepositoryCustom;

import io.lettuce.core.dynamic.annotation.Param;

public interface MST140Repository extends JpaRepository<MST140, String>, MST140RepositoryCustom {
	
}
