package com.innomes.main.system.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.innomes.main.repository.SYS800Repository;
import com.innomes.main.system.model.SYS800;


@Service
@Transactional
public class AccountService {
	@Autowired
	private SYS800Repository sys800Repository;
	
	@Cacheable(value = "sys800")
	public List<SYS800> GetSYS800All(){ return sys800Repository.findAll();}
}
