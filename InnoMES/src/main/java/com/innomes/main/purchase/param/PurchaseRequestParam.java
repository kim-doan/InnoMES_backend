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
public class PurchaseRequestParam {

	//PUR100
	private String reqNo;

	private String reqType;

	private String reqUser;

	private String reqDepartment;

	private Date reqDate;

	private String apprUser;

	private Date apprDate;

	private String description;

	private String createUser;

	private Date createTime;

	private String updateUser;

	private Date updateTime;

	private int used;
	

	//PUR101

	private int reqSeq;

	private String reqCode;

	private double reqQnt;

	private String reqUnit;

	private String reqComp;

	private Date reqDue;

	private String reqLoc;

	private int orderYn;

}
