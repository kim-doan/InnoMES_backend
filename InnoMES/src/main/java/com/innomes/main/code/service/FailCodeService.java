package com.innomes.main.code.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.innomes.main.code.model.COD400;
import com.innomes.main.code.param.COD400Param;
import com.innomes.main.repository.COD400Repository;

@Transactional
@Service
public class FailCodeService {

	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private int batchSize;
	
	@Autowired
	private COD400Repository cod400Repository;
	
	public Page<COD400> findAllLike(COD400Param code400Param, Pageable pageable) {
		return cod400Repository.findAllLike(code400Param, pageable);
	}
}
