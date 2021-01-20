package com.innomes.main.sales.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.innomes.main.sales.dto.SalesOrderLogDTO.SalesOrderLogDTOBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class SalesOrderDTO {
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
	
	private List<SalesOrderLogDTO> salesOrderLog = new ArrayList<SalesOrderLogDTO>();
}
