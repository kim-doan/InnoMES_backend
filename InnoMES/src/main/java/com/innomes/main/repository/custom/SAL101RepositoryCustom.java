package com.innomes.main.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.innomes.main.sales.model.SAL100;
import com.innomes.main.sales.model.SAL101;
import com.innomes.main.sales.param.SalesPlanParam;

public interface SAL101RepositoryCustom {
	//판매계획 조회
	Page<SAL101> getSalesPlan(SalesPlanParam salesPlanParam, Pageable pageable);
		
	// 판매계획내역 판매계획량 SUM
	Double sumPlanQnt(Integer salPlanNo);
}
