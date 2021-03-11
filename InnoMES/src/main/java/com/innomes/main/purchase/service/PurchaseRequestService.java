package com.innomes.main.purchase.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.innomes.main.purchase.dto.PurchaseRequestDTO;
import com.innomes.main.purchase.param.PurchaseRequestParam;
import com.innomes.main.util.service.UtilService;

@Service
@Transactional
public class PurchaseRequestService {
	
	@Autowired
	UtilService util;
	
	//구매요청 조회
	public Page<PurchaseRequestDTO> getPurchaseRequest(PurchaseRequestParam purchaseRequestParam, Pageable pageable) {
		return null;
	}
}
