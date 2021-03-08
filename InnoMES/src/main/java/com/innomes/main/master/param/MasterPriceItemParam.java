package com.innomes.main.master.param;

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
public class MasterPriceItemParam {
	
	private String itemId;
	
	private String itemCode;
	
	private String itemName;
	
	private String compId;
	
	private Integer priceRev;
	
	private String priceType;
	
	private String priceRevCause;
	
	private String priceRevUser;
	
	private String compItemId;
	
	private Double priceStd;
	
	private String priceUnit;
	
	private String deliveryType;
	
	private Integer deliveryDay;
	
	private String description;
	
	private String createUser;
	
	private Date createTime;
	
	private String updateUser;
	
	private Date updateTime;
	
	private Integer used;
}
