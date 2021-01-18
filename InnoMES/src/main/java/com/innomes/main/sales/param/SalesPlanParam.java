package com.innomes.main.sales.param;

import java.util.Date;

import javax.persistence.Column;

import com.innomes.main.sales.model.SAL101;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class SalesPlanParam {
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
	private SAL101 sal101;
	
}
