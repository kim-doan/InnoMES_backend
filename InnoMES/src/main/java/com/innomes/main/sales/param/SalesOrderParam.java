package com.innomes.main.sales.param;

import java.util.Date;

import com.innomes.main.sales.param.SalesPlanParam.SalesPlanParamBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class SalesOrderParam {
	//SAL110
	private Integer ordId;

	private String compId;
	
	private String compName;

	private String itemId;
	
	private String itemCode;
	
	private String itemName;
	
	private Date dueDate;

	private  String ordType;

	private String ordRegUser;

	private Date ordRegTime;

	private String description;

	private String createUser;

	private Date createTime;

	private String updateUser;
	
	private Date updateTime;
	
	private int used;
	
	//SAL111
	private Integer ordSeq;
	
	private Double ordQnt;
	
	private String ordRegType;
}
