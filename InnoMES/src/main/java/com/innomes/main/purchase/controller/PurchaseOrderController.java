package com.innomes.main.purchase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.purchase.dto.PurchaseOrderDTO;
import com.innomes.main.purchase.param.PurchaseOrderParam;
import com.innomes.main.purchase.service.PurchaseOrderService;
import com.innomes.main.response.model.PageListResult;
import com.innomes.main.response.service.ResponseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(value = "PurchaseOrderController")
public class PurchaseOrderController {
	@Autowired
	private ResponseService responseService;
	
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	@CrossOrigin
	@ApiOperation(value = "자재발주 조회", notes = "구매발주 정보를 반환합니다.")
	@PostMapping("/purchaseOrder/material")
	public PageListResult<PurchaseOrderDTO> getMaterialPurchaseRequestList(@RequestBody(required = false) PurchaseOrderParam purchaseOrderParam, final Pageable pageable){
		if(purchaseOrderParam == null)
			purchaseOrderParam = new PurchaseOrderParam();
		return responseService.getPageListResult(PurchaseOrderDTO.class, purchaseOrderService.getMaterialPurchaseOrder(purchaseOrderParam, pageable));
	}
}
