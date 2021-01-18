package com.innomes.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innomes.main.master.model.MST112;
import com.innomes.main.repository.custom.MST112RepositoryCustom;

public interface MST112Repository extends JpaRepository<MST112, String>, MST112RepositoryCustom {

}
