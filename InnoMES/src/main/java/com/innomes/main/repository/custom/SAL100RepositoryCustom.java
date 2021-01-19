package com.innomes.main.repository.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.innomes.main.sales.model.SAL100;
import com.innomes.main.sales.model.SAL101;
import com.innomes.main.sales.param.SalesPlanParam;

public interface SAL100RepositoryCustom {
	//판매계획 내역 보기
	List<SAL100> getSalesPlanLog(SalesPlanParam salesPlanParam);
}
