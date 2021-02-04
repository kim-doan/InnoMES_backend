package com.innomes.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innomes.main.system.model.SYS810;
import com.innomes.main.system.model.SYS810PK;

public interface SYS810Repository extends JpaRepository<SYS810, SYS810PK>{
	void deleteByUserNo(String userNo);
}
