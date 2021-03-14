package com.innomes.main.purchase.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.innomes.main.purchase.dto.PurchaseRequestDTO;
import com.innomes.main.purchase.dto.PurchaseRequestDetailDTO;
import com.innomes.main.purchase.model.PUR100;
import com.innomes.main.purchase.model.PUR101;
import com.innomes.main.purchase.param.PurchaseRequestParam;
import com.innomes.main.repository.PUR100Repository;
import com.innomes.main.repository.PUR101Repository;
import com.innomes.main.util.service.UtilService;

@Service
@Transactional
public class PurchaseRequestService {
	
	@Autowired
	UtilService util;
	
	@Autowired
	PUR100Repository pur100Repository;
	
	@Autowired
	PUR101Repository pur101Repository;
	
	//제품 구매 요청 조회
	public Page<PurchaseRequestDTO> getProductPurchaseRequest(PurchaseRequestParam purchaseRequestParam, Pageable pageable) {
		return getPurchaseRequest(purchaseRequestParam, pageable, "PCS001001");
	}
	//자제 구매 요청 조회
	public Page<PurchaseRequestDTO> getMaterialPurchaseRequest(PurchaseRequestParam purchaseRequestParam, Pageable pageable) {
		return getPurchaseRequest(purchaseRequestParam, pageable, "PCS001002");
	}
	//공구 구매 요청 조회
	public Page<PurchaseRequestDTO> getToolPurchaseRequest(PurchaseRequestParam purchaseRequestParam, Pageable pageable) {
		return getPurchaseRequest(purchaseRequestParam, pageable, "PCS001003");
	}
	//예비품 구매 요청 조회
	public Page<PurchaseRequestDTO> getSparePurchaseRequest(PurchaseRequestParam purchaseRequestParam, Pageable pageable) {
		return getPurchaseRequest(purchaseRequestParam, pageable, "PCS001004");
	}
	
	public boolean setPurchaseRequest(List<PurchaseRequestParam> purchaseRequestParams) {
		try {
			List<PUR100> pur100List = util.convertModelAndDto(purchaseRequestParams, PUR100.class);
			for(PUR100 pur100 : pur100List) {
				pur100.setCreateTime(new Date());
				pur100.setUpdateTime(new Date());
			}
		} catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return true;
	}
	
	private Page<PurchaseRequestDTO> getPurchaseRequest(PurchaseRequestParam purchaseRequestParam, Pageable pageable, String priceType) {
		purchaseRequestParam.setReqType(priceType);
		Page<PUR100> output = pur100Repository.findAllLike(purchaseRequestParam, pageable);
		List<PurchaseRequestDTO> dtoList = util.convertModelAndDto(output.getContent(), PurchaseRequestDTO.class);
		return new PageImpl<>(dtoList, pageable, output.getTotalElements());
	}
}
