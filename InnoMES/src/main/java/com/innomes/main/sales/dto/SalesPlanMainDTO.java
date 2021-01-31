package com.innomes.main.sales.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class SalesPlanMainDTO {
	//SAL100
	private Integer salPlanNo;
	
	private Integer planYear;
	
	private Integer planMonth;
	
	private String compId;
	
	private String itemId;
	
	private String description;
	
	private String createUser;
	
	private Date createTime;
	
	private String updateUser;
	
	private Date updateTime;
	
	private int used;
	
	//SAL101
	private Integer salPlanSeq;
	
	private String regType;
	
	private String regUser;
	
	private Date regTime;
	
	private String regCause;

	private Double planQnt; 
}
