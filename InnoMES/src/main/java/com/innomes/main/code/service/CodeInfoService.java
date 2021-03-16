package com.innomes.main.code.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.innomes.main.code.model.COD100;
import com.innomes.main.code.param.CodeInfoParam;
import com.innomes.main.repository.COD100Repository;

@Transactional
@Service
public class CodeInfoService {

	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private int batchSize;
	
	@Autowired
	private COD100Repository cod100Repository;
	
	public Page<COD100> findAllLike(CodeInfoParam codeInfoParam, Pageable pageable) {
		Page<COD100> output = cod100Repository.findAllLike(codeInfoParam, pageable);
		List<COD100> content = output.getContent();
		return new PageImpl<>(content, pageable, output.getTotalElements());
	}
}
