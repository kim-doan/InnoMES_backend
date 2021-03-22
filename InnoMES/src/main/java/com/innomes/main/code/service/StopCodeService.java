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
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import com.innomes.main.code.model.COD300;
import com.innomes.main.code.param.COD300Param;
import com.innomes.main.exception.CStopCodeDuplicateException;
import com.innomes.main.exception.CStopCodeInfoSaveException;
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

	public boolean saveStopCode(List<COD300Param> cod300ParamList) {
		boolean success = true;
		
		List<COD300> cod300List = new ArrayList<COD300>();
		
		String stopCode;
		long totalCount = cod300Repository.count();
		try {
			for (COD300Param cod300Param : cod300ParamList) {
				if (cod300Param.getStopCode() == null || StringUtils.isEmpty(cod300Param.getStopCode().trim())) {
					totalCount++;
					stopCode = "STP" + String.format("%05d", totalCount);
				} else {
					stopCode = cod300Param.getStopCode();
				}
				COD300 cod300 = COD300.builder()
						.stopCode(stopCode)
						.displayCode(cod300Param.getDisplayCode())
						.stopName(cod300Param.getStopName())
						.stopType(cod300Param.getStopType())
						.stopClass(cod300Param.getStopClass())
						.procType(cod300Param.getProcType())
						.oeeYN(cod300Param.getOeeYN())
						.description(cod300Param.getDescription())
						.createUser(cod300Param.getCreateUser())
						.createTime(new Date())
						.updateUser(cod300Param.getUpdateUser())
						.updateTime(new Date())
						.used(cod300Param.getUsed())
						.build();
				cod300List.add(cod300);
				
				if((cod300ParamList.indexOf(cod300Param)+1) % batchSize == 0 && cod300ParamList.indexOf(cod300Param) > 0) {
					cod300Repository.saveAll(cod300List);
					cod300Repository.flush();
					cod300List.clear();
				}
			}
			cod300Repository.saveAll(cod300List);
			cod300Repository.flush();
			cod300List.clear();
			
		} catch (CStopCodeDuplicateException e) {
			throw new CStopCodeDuplicateException();
		} catch (CStopCodeInfoSaveException e) {
			throw new CStopCodeInfoSaveException();
		} catch (Exception e) {
			success = false;
		}			
		return success;
	}
}
