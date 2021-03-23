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
import com.innomes.main.code.param.StopCodeParam;
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

	public Page<COD300> findAllLike(StopCodeParam code300Param, Pageable pageable) {
		return cod300Repository.findAllLike(code300Param, pageable);
	}

	public boolean saveStopCode(List<StopCodeParam> stopCodeParamList) {
		boolean success = true;
		
		List<COD300> cod300List = new ArrayList<COD300>();
		
		String stopCode;
		long totalCount = cod300Repository.count();
		try {
			for (StopCodeParam stopCodeParam : stopCodeParamList) {
				if (stopCodeParam.getStopCode() == null || StringUtils.isEmpty(stopCodeParam.getStopCode().trim())) {
					totalCount++;
					stopCode = "STP" + String.format("%05d", totalCount);
				} else {
					stopCode = stopCodeParam.getStopCode();
				}
				COD300 cod300 = COD300.builder()
						.stopCode(stopCode)
						.displayCode(stopCodeParam.getDisplayCode())
						.stopName(stopCodeParam.getStopName())
						.stopType(stopCodeParam.getStopType())
						.stopClass(stopCodeParam.getStopClass())
						.procType(stopCodeParam.getProcType())
						.oeeYN(stopCodeParam.getOeeYN())
						.description(stopCodeParam.getDescription())
						.createUser(stopCodeParam.getCreateUser())
						.createTime(new Date())
						.updateUser(stopCodeParam.getUpdateUser())
						.updateTime(new Date())
						.used(stopCodeParam.getUsed())
						.build();
				cod300List.add(cod300);
				
				if((stopCodeParamList.indexOf(stopCodeParam)+1) % batchSize == 0 && stopCodeParamList.indexOf(stopCodeParam) > 0) {
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
