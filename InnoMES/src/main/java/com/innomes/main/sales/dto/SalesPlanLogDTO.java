package com.innomes.main.sales.dto;

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
public class SalesPlanLogDTO {
	private Integer salPlanNo;
	
	private Integer salPlanSeq;
	
	private String regType;
	
	private String regUser;
	
	private Date regTime;
	
	private String regCause;

	private Double planQnt; 
}
