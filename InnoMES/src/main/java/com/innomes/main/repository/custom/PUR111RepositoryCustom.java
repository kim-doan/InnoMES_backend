package com.innomes.main.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.innomes.main.purchase.model.PUR111;
import com.innomes.main.purchase.param.PurchaseOrderParam;

public interface PUR111RepositoryCustom {
	Page<PUR111> findAllLike(PurchaseOrderParam purchaseOrderParam, Pageable pageable, String itemType);
}
