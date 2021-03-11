package com.innomes.main.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.innomes.main.purchase.model.PUR101;
import com.innomes.main.purchase.param.PurchaseRequestParam;

public interface PUR101RepositoryCustom {
	Page<PUR101> findById(PurchaseRequestParam purchaseRequestParam, Pageable pageable);
}
