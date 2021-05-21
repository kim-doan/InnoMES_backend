package com.innomes.main.purchase.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseOrderDTO {
	//PUR110
	private String poNo;
	
	private String compId;
	
	private String compName;
	
	private String orderUser;
	
	private Date orderDate;
	
	//PUR111
	private int poSeq;
	
	private String orderItem;
	
	private String itemCode;
	
	private String itemName;
	
	private double orderQnt;
	
	private String orderUnit;
	
	private String description;
	
	private Date dueDate;
	
	private String incomeLoc;
	
	private String reqNo;
	
	private Integer reqSeq;
	
	private double priceStd;
	
	private double priceLog;
	
	private String moneyUnit;
	
	private double taxRate;
	
	private double amount;
	
	private int incomeYN;
}
