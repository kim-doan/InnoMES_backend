package com.innomes.main.master.dto;

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
public class MasterPriceItemDTO {
	
	private String itemId;
	
	private String itemCode;
	
	private String itemName;
	
	private String compId;
	
	private String compName;
	
	private String priceRevCause;
	
	private String priceRevUser;
	
	private String compItemId;
	
	private double priceStd;
	
	private String priceUnit;
	
	private String deliveryType;
	
	private int deliveryDay;
		
	private int priceRev;
	
	private String description;
	
	private String createUser;

	private Date createTime;
	
	private String updateUser;
	
	private Date updateTime;
}
