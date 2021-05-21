package com.innomes.main.purchase.param;

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
public class PurchaseOrderParam {
	//PUR110
	private String poNo;
	
	private String compId;
	
	private String orderUser;
	
	private Date orderDate;
	
	//PUR111
	private int poSeq;
	
	private String orderItem;
	
	private double orderQnt;
	
	private String orderUnit;
	
	private String description;
	
	private Date dueDate;
	
	private String incomeLoc;
	
	private String reqNo;
	
	private int reqSeq;
	
	private double priceStd;
	
	private double priceLog;
	
	private String moneyUnit;
	
	private double taxRate;
	
	private double amount;
	
	private int incomeYN;

	//공통	
	private String createUser;
	
	private Date createTime;
	
	private String updateUser;
	
	private Date updateTime;
	
	private int used;
	
	//search
	private Date orderStartDate;
	
	private Date orderEndDate;
	
	private Date dueStartDate;
	
	private Date dueEndDate;
}
