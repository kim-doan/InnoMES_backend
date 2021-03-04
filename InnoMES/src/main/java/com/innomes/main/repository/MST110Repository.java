package com.innomes.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innomes.main.master.model.MST110;
import com.innomes.main.repository.custom.MST110RepositoryCustom;

public interface MST110Repository extends JpaRepository<MST110, String>, MST110RepositoryCustom {
	List<MST110> findByItemCodeAndUsed(String itemCode, int used);
}
