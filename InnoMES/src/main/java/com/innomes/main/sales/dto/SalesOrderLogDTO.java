package com.innomes.main.sales.dto;

import java.util.Date;

import lombok.RequiredArgsConstructor;

import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class SalesOrderLogDTO {
	private Integer ordId;
	
	private Integer ordSeq;
	
	private Double ordQnt;
	
	private String ordRegType;
	
	private String ordRegUser;

	private Date ordRegTime;

	private String description;
	
	private int used;
}
