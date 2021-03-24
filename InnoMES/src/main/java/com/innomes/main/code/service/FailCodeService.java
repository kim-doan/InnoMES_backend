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

import com.innomes.main.code.model.COD400;
import com.innomes.main.code.param.FailCodeParam;
import com.innomes.main.exception.CStopCodeDuplicateException;
import com.innomes.main.exception.CStopCodeInfoSaveException;
import com.innomes.main.repository.COD400Repository;

@Transactional
@Service
public class FailCodeService {

	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private int batchSize;
	
	@Autowired
	private COD400Repository cod400Repository;
	
	public Page<COD400> findAllLike(FailCodeParam failCodeParam, Pageable pageable) {
		return cod400Repository.findAllLike(failCodeParam, pageable);
	}
	public boolean saveFailCode(List<FailCodeParam> failCodeParamList) {
		boolean success = true;
		
		List<COD400> cod400List = new ArrayList<COD400>();
		long totalCount = cod400Repository.count();
		try {
			for (FailCodeParam failCodeParam : failCodeParamList) {
				COD400 cod400 = COD400.builder()
						.failCode(setAutoCode(failCodeParam.getFailCode(),"STP",totalCount))
						.displayCode(failCodeParam.getDisplayCode())
						.failName(failCodeParam.getFailName())
						.failType(failCodeParam.getFailType())
						.failClass(failCodeParam.getFailClass())
						.description(failCodeParam.getDescription())
						.createUser(failCodeParam.getCreateUser())
						.createTime(new Date())
						.updateUser(failCodeParam.getUpdateUser())
						.updateTime(new Date())
						.used(failCodeParam.getUsed())
						.build();
				cod400List.add(cod400);
				
				if((failCodeParamList.indexOf(failCodeParam)+1) % batchSize == 0 && failCodeParamList.indexOf(failCodeParam) > 0) {
					cod400Repository.saveAll(cod400List);
					cod400Repository.flush();
					cod400List.clear();
				}
			}
			cod400Repository.saveAll(cod400List);
			cod400Repository.flush();
			cod400List.clear();
			
		} catch (CStopCodeDuplicateException e) {
			throw new CStopCodeDuplicateException();
		} catch (CStopCodeInfoSaveException e) {
			throw new CStopCodeInfoSaveException();
		} catch (Exception e) {
			success = false;
		}			
		return success;
	}
	public String setAutoCode(String code,String prefix ,long totalCnt) {
		if (code == null || StringUtils.isEmpty(code.trim())) {
			totalCnt++;
			code = "FAL" + String.format("%05d", totalCnt);
		}
		return code;
	}
}
