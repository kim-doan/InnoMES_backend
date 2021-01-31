package com.innomes.main.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.innomes.main.sales.model.SAL111;
import com.innomes.main.sales.param.SalesOrderParam;

public interface SAL111RepositoryCustom {
	//수주내역 조회
	Page<SAL111> getSalesOrder(SalesOrderParam salesOrderParam, Pageable pageable);
	
	//수주내역 수주량 SUM
	Double sumOrdQnt(Integer ordId);
	
	//수주내역 수주내역순번 MAX
	Integer maxOrdSeq(Integer ordId);
}
