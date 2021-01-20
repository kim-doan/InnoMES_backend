package com.innomes.main.sales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.exception.CSalesPlanSaveException;
import com.innomes.main.response.model.CommonResult;
import com.innomes.main.response.model.ListResult;
import com.innomes.main.response.model.PageListResult;
import com.innomes.main.response.service.ResponseService;
import com.innomes.main.sales.dto.SalesPlanMainDTO;
import com.innomes.main.sales.dto.SalesPlanDTO;
import com.innomes.main.sales.param.SalesPlanParam;
import com.innomes.main.sales.service.SalesPlanService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(value = "SalesPlanController")
public class SalesPlanController {

	@Autowired
	private ResponseService responseService;
	
	@Autowired
	private SalesPlanService salesPlanService;
	
	@CrossOrigin
	@ApiOperation(value = "판매계획 조회", notes = "판매계획정보 전체를 반환합니다. (검색조건 필터링 가능)")
	@PostMapping("/sales/planList")
	public PageListResult<SalesPlanMainDTO> getSalesPlanList(@RequestBody(required = false) SalesPlanParam salesPlanParam, final Pageable pageable) {
		if(salesPlanParam == null) //파라메터가 없을경우
			salesPlanParam = new SalesPlanParam(); // 전체 조회
		
		return responseService.getPageListResult(SalesPlanMainDTO.class, salesPlanService.getSalesPlanList(salesPlanParam, pageable));
	}
	
	@CrossOrigin
	@ApiOperation(value = "판매계획내역 조회", notes = "판매계획정보의 세부 변경 로그를 반환합니다.")
	@PostMapping("/sales/planLogList")
	public ListResult<SalesPlanDTO> getSalesPlanLogList(@RequestBody(required = false) SalesPlanParam salesPlanParam, final Pageable pageable) {
		if(salesPlanParam == null) //파라메터가 없을경우
			salesPlanParam = new SalesPlanParam(); // 전체 조회
		
		return responseService.getListResult(SalesPlanDTO.class, salesPlanService.getSalesPlanLogList(salesPlanParam));
	}
	
	@CrossOrigin
	@ApiOperation(value = "판매계획내역 저장", notes = "판매계획정보를 저장합니다")
	@PostMapping("/sales/planList/save")
	public CommonResult setSalesPlanLogList(@RequestBody(required = true) SalesPlanParam[] salesPlanParam) {
		boolean result = salesPlanService.saveSalesPlan(salesPlanParam);
		
		if(result == true) {
			return responseService.getSuccessResult();
		} else {
			throw new CSalesPlanSaveException();
		}
	}
}
