package com.innomes.main.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.innomes.main.purchase.model.PUR100;
import com.innomes.main.purchase.param.PurchaseRequestParam;

public interface PUR100RepositoryCustom {
	Page<PUR100> findAllLike(PurchaseRequestParam purchaseRequestParam, Pageable pageable);
}
