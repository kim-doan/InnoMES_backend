package com.innomes.main.code.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.innomes.main.code.model.COD200;
import com.innomes.main.code.param.COD200Param;
import com.innomes.main.repository.COD200Repository;

@Transactional
@Service
public class BadCodeService {

	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private int batchSize;
	
	@Autowired
	private COD200Repository cod200Repository;
	
	public Page<COD200> findAllLike(COD200Param code200Param, Pageable pageable) {
		return cod200Repository.findAllLike(code200Param, pageable);
	}
}
