package com.innomes.main.code.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.innomes.main.code.model.COD300;
import com.innomes.main.code.param.COD300Param;
import com.innomes.main.repository.COD300Repository;

@Transactional
@Service
public class StopCodeService {

	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private int batchSize;
	
	@Autowired
	private COD300Repository cod300Repository;
	
	public Page<COD300> findAllLike(COD300Param code300Param, Pageable pageable) {
		return cod300Repository.findAllLike(code300Param, pageable);
	}
}
