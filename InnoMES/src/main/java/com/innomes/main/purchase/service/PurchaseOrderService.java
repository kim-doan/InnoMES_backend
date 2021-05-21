package com.innomes.main.purchase.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.innomes.main.pool.service.MasterPoolService;
import com.innomes.main.purchase.dto.PurchaseOrderDTO;
import com.innomes.main.purchase.model.PUR111;
import com.innomes.main.purchase.param.PurchaseOrderParam;
import com.innomes.main.repository.PUR111Repository;

@Service
@Transactional
public class PurchaseOrderService {
	
	@Autowired
	PUR111Repository pur111Repository;
	
	@Autowired
	MasterPoolService masterPoolService;
	
	public Page<PurchaseOrderDTO> getMaterialPurchaseOrder(PurchaseOrderParam purchaseOrderParam, Pageable pageable) {
		return getPurchaseOrder(purchaseOrderParam, pageable, "ITM002");
	}
	
	private Page<PurchaseOrderDTO> getPurchaseOrder(PurchaseOrderParam purchaseOrderParam, Pageable pageable, String itemType) {
		Page<PUR111> output = pur111Repository.findAllLike(purchaseOrderParam, pageable, itemType);
		List<PurchaseOrderDTO> dtoList = new ArrayList<PurchaseOrderDTO>();
		for(PUR111 pur111 : output.getContent()) {
			PurchaseOrderDTO dto = PurchaseOrderDTO.builder()
					.poNo(pur111.getPoNo())
					.compId(pur111.getPur110().getCompId())
					.compName(masterPoolService.getMST150Map().get(pur111.getPur110().getCompId()).getCompName())
					.orderUser(pur111.getPur110().getOrderUser())
					.orderDate(pur111.getPur110().getOrderDate())
					.poSeq(pur111.getPoSeq())
					.orderItem(pur111.getOrderItem())
					.itemCode(masterPoolService.getMST110(pur111.getOrderItem()).getItemCode())
					.itemName(masterPoolService.getMST110(pur111.getOrderItem()).getItemName())
					.orderQnt(pur111.getOrderQnt())
					.orderUnit(pur111.getOrderUnit())
					.description(pur111.getDescription())
					.dueDate(pur111.getDueDate())
					.incomeLoc(pur111.getIncomeLoc())
					.reqNo(pur111.getReqNo())
					.reqSeq(pur111.getReqSeq())
					.priceStd(pur111.getPriceStd())
					.priceLog(pur111.getPriceLog())
					.moneyUnit(pur111.getMoneyUnit())
					.taxRate(pur111.getTaxRate())
					.amount(pur111.getAmount())
					.incomeYN(pur111.getIncomeYn())
					.build();
			
			dtoList.add(dto);
		}
		return new PageImpl<PurchaseOrderDTO>(dtoList, pageable, output.getTotalElements());
	}
}
