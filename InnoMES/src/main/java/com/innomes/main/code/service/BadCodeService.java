package com.innomes.main.code.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.innomes.main.code.model.COD200;
import com.innomes.main.code.param.BadCodeParam;
import com.innomes.main.exception.CBadCodeDuplicateException;
import com.innomes.main.exception.CBadCodeInfoSaveException;
import com.innomes.main.repository.COD200Repository;

@Transactional
@Service
public class BadCodeService {

	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private int batchSize;
	
	@Autowired
	private COD200Repository cod200Repository;
	
	public Page<COD200> findAllLike(BadCodeParam badCodeParam, Pageable pageable) {
		return cod200Repository.findAllLike(badCodeParam, pageable);
	}
	public boolean saveBadCode(List<BadCodeParam> badCodeParamList) {
		boolean success = true;
		
		List<COD200> cod200List = new ArrayList<COD200>();
		long totalCount = cod200Repository.count();
		try {
			for (BadCodeParam badCodeParam : badCodeParamList) {
				COD200 cod200 = COD200.builder()
						.badCode(setAutoCode(badCodeParam.getBadCode(),totalCount))
						.displayCode(badCodeParam.getDisplayCode())
						.badName(badCodeParam.getBadName())
						.badType(badCodeParam.getBadType())
						.badClass(badCodeParam.getBadClass())
						.procType(badCodeParam.getProcType())
						.description(badCodeParam.getDescription())
						.createUser(badCodeParam.getCreateUser())
						.createTime(new Date())
						.updateUser(badCodeParam.getUpdateUser())
						.updateTime(new Date())
						.used(badCodeParam.getUsed())
						.build();
				cod200List.add(cod200);
				
				if((badCodeParamList.indexOf(badCodeParam)+1) % batchSize == 0 && badCodeParamList.indexOf(badCodeParam) > 0) {
					cod200Repository.saveAll(cod200List);
					cod200Repository.flush();
					cod200List.clear();
				}
			}
			cod200Repository.saveAll(cod200List);
			cod200Repository.flush();
			cod200List.clear();
			
		} catch (CBadCodeDuplicateException e) {
			throw new CBadCodeDuplicateException();
		} catch (CBadCodeInfoSaveException e) {
			throw new CBadCodeInfoSaveException();
		} catch (Exception e) {
			success = false;
		}			
		return success;
	}
	public String setAutoCode(String code,long totalCnt) {
		if (code == null || StringUtils.isEmpty(code.trim())) {
			totalCnt++;
			code = "BAD" + String.format("%05d", totalCnt);
		}
		return code;
	}
}
