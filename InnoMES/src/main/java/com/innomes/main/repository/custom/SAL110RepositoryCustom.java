package com.innomes.main.repository.custom;

import java.util.List;

import com.innomes.main.sales.model.SAL110;
import com.innomes.main.sales.param.SalesOrderParam;

public interface SAL110RepositoryCustom {
	//수주내역 보기
	List<SAL110> getSalesOrderLog(SalesOrderParam salesOrderParam);
}
