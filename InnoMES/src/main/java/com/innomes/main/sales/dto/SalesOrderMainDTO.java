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
public class SalesOrderMainDTO {
	//SAL110
	private Integer ordId;

	private String compId;

	private String itemId;

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
