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
public class PurchaseRequestDetailDTO {

	private String reqNo;

	private int reqSeq;

	private String reqCode;
	
	private String itemCode;
	
	private String itemName;

	private double reqQnt;

	private String reqUnit;

	private String reqComp;
	
	private String compName;

	private Date reqDue;

	private String reqLoc;
	
	private String locName;

	private int orderYN;
	
	//자재정보
	private String description;
	
	private String attMatType;
	
	private String attDiaType;
	
	private String attStdType;
	
	private String matProc;
	
	//공구정보
	private String toolProc;
	
	private String prdtionProc;
	
	private String repItemId;
	
	private String repItemCode;
	
	private String repItemName;
}
