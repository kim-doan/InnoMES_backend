package com.innomes.main.purchase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.exception.CCompanySaveException;
import com.innomes.main.master.param.MasterCompanyParam;
import com.innomes.main.purchase.dto.PurchaseRequestDTO;
import com.innomes.main.purchase.dto.PurchaseRequestDetailDTO;
import com.innomes.main.purchase.param.PurchaseRequestParam;
import com.innomes.main.purchase.service.PurchaseRequestService;
import com.innomes.main.response.model.CommonResult;
import com.innomes.main.response.model.PageListResult;
import com.innomes.main.response.service.ResponseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(value = "PurchaseRequestController")
public class PurchaseRequestController {
	@Autowired
	private ResponseService responseService;
	
	@Autowired
	private PurchaseRequestService purchaseRequestService;
	
	@CrossOrigin
	@ApiOperation(value = "자재구매요청 조회", notes = "구매요청 정보를 반환합니다.")
	@PostMapping("/purchaseRequest/material")
	public PageListResult<PurchaseRequestDTO> getMaterialPurchaseRequestList(@RequestBody(required = false) PurchaseRequestParam purchaseRequestParam, final Pageable pageable){
		if(purchaseRequestParam == null)
			purchaseRequestParam = new PurchaseRequestParam();
		return responseService.getPageListResult(PurchaseRequestDTO.class, purchaseRequestService.getMaterialPurchaseRequest(purchaseRequestParam, pageable));
	}
	
	@CrossOrigin
	@ApiOperation(value = "공구구매요청 조회", notes = "공구요청 정보를 반환합니다.")
	@PostMapping("/purchaseRequest/tool")
	public PageListResult<PurchaseRequestDTO> getToolPurchaseRequestList(@RequestBody(required = false) PurchaseRequestParam purchaseRequestParam, final Pageable pageable){
		if(purchaseRequestParam == null)
			purchaseRequestParam = new PurchaseRequestParam();
		return responseService.getPageListResult(PurchaseRequestDTO.class, purchaseRequestService.getToolPurchaseRequest(purchaseRequestParam, pageable));
	}
	
	@CrossOrigin
	@ApiOperation(value = "예비품구매요청 조회", notes = "예비품요청 정보를 반환합니다.")
	@PostMapping("/purchaseRequest/spare")
	public PageListResult<PurchaseRequestDTO> getSparePurchaseRequestList(@RequestBody(required = false) PurchaseRequestParam purchaseRequestParam, final Pageable pageable){
		if(purchaseRequestParam == null)
			purchaseRequestParam = new PurchaseRequestParam();
		return responseService.getPageListResult(PurchaseRequestDTO.class, purchaseRequestService.getSparePurchaseRequest(purchaseRequestParam, pageable));
	}
	
	@CrossOrigin
	@ApiOperation(value = "구매요청 정보 저장", notes = "구매요청 정보를 저장합니다.")
	@PostMapping("/purchaseRequest/save")
	public CommonResult setPurchaseRequest(@RequestBody(required = true) List<PurchaseRequestParam> purchaseRequestParams) {
		boolean result = purchaseRequestService.setPurchaseRequest(purchaseRequestParams);
		
		if(result) {
			return responseService.getSuccessResult();
		}else {
			throw new CCompanySaveException();
		}
	}
}
