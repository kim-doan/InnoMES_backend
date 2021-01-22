package com.innomes.main.sales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.exception.CSalesOrderSaveException;
import com.innomes.main.response.model.CommonResult;
import com.innomes.main.response.model.ListResult;
import com.innomes.main.response.model.PageListResult;
import com.innomes.main.response.service.ResponseService;
import com.innomes.main.sales.dto.SalesOrderDTO;
import com.innomes.main.sales.dto.SalesOrderMainDTO;
import com.innomes.main.sales.param.SalesOrderParam;
import com.innomes.main.sales.service.SalesOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(value = "SalesOrderController")
public class SalesOrderController {
	@Autowired
	private ResponseService responseService;
	
	@Autowired
	private SalesOrderService salesOrderService;
	
	@CrossOrigin
	@ApiOperation(value = "수주관리 조회", notes = "수주정보 전체를 반환합니다. (검색조건 필터링 가능)")
	@PostMapping("/sales/orderList")
	public PageListResult<SalesOrderMainDTO> getSalesOrderList(@RequestBody(required = false) SalesOrderParam salesOrderParam, final Pageable pageable) {
		if(salesOrderParam == null) //파라메터가 없을경우
			salesOrderParam = new SalesOrderParam(); // 전체 조회
		
		return responseService.getPageListResult(SalesOrderMainDTO.class, salesOrderService.getSalesOrderList(salesOrderParam, pageable));
	}
	
	@CrossOrigin
	@ApiOperation(value = "수주내역 조회", notes = "수주정보의 세부 변경 로그를 반환합니다.")
	@PostMapping("/sales/orderLogList")
	public ListResult<SalesOrderDTO> getSalesOrderLogList(@RequestBody(required = false) SalesOrderParam salesOrderParam, final Pageable pageable) {
		if(salesOrderParam == null) //파라메터가 없을경우
			salesOrderParam = new SalesOrderParam(); // 전체 조회
		
		return responseService.getListResult(SalesOrderDTO.class, salesOrderService.getSalesOrderLogList(salesOrderParam));
	}
	
	@CrossOrigin
	@ApiOperation(value = "수주내역 저장", notes = "수주정보를 저장합니다")
	@PostMapping("/sales/orderList/save")
	public CommonResult setSalesOrderLogList(@RequestBody(required = true) SalesOrderParam[] salesOrderParam) {
		boolean result = salesOrderService.saveSalesOrder(salesOrderParam);
		
		if(result == true) {
			return responseService.getSuccessResult();
		} else {
			throw new CSalesOrderSaveException();
		}
	}
}
