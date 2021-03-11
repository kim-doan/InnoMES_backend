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
		purchaseRequestParam.setReqType("PCS001001"); //제품구매
		return getPurchaseRequest(purchaseRequestParam, pageable);
	}
	//자제 구매 요청 조회
	public Page<PurchaseRequestDTO> getMaterialPurchaseRequest(PurchaseRequestParam purchaseRequestParam, Pageable pageable) {
		purchaseRequestParam.setReqType("PCS001002"); //자재구매
		return getPurchaseRequest(purchaseRequestParam, pageable);
	}
	//공구 구매 요청 조회
	public Page<PurchaseRequestDTO> getToolPurchaseRequest(PurchaseRequestParam purchaseRequestParam, Pageable pageable) {
		purchaseRequestParam.setReqType("PCS001003"); //공구구매
		return getPurchaseRequest(purchaseRequestParam, pageable);
	}
	//예비품 구매 요청 조회
	public Page<PurchaseRequestDTO> getSparePurchaseRequest(PurchaseRequestParam purchaseRequestParam, Pageable pageable) {
		purchaseRequestParam.setReqType("PCS001004"); //예비품구매
		return getPurchaseRequest(purchaseRequestParam, pageable);
	}
	
	//구매 요청 상세 내용 조회
	public Page<PurchaseRequestDetailDTO> getPurchaseRequestDetail(PurchaseRequestParam purchaseRequestParam, Pageable pageable) {
		Page<PUR101> output = pur101Repository.findById(purchaseRequestParam, pageable);
		List<PurchaseRequestDetailDTO> dtoList = util.convertModelAndDto(output.getContent(), PurchaseRequestDetailDTO.class);
		return new PageImpl<>(dtoList, pageable, output.getTotalElements());
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
	
	private Page<PurchaseRequestDTO> getPurchaseRequest(PurchaseRequestParam purchaseRequestParam, Pageable pageable) {
		Page<PUR100> output = pur100Repository.findAllLike(purchaseRequestParam, pageable);
		List<PurchaseRequestDTO> dtoList = util.convertModelAndDto(output.getContent(), PurchaseRequestDTO.class);
		return new PageImpl<>(dtoList, pageable, output.getTotalElements());
	}
}
