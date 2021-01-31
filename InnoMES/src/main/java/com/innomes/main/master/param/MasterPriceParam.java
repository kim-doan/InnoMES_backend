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
public class MasterPriceParam {
private String itemId;
	
	private String compId;
	
	private int priceRev;
	
	private String priceType;
	
	private String pricdRevCause;
	
	private String priceRevUser;
	
	private String compItemId;
	
	private double priceStd;
	
	private String priceUnit;
	
	private String deliveryType;
	
	private int deliveryDay;
	
	private String description;
	
	private Date createTime;
	
	private String createUser;
	
	private Date updateTime;
	
	private String updateUser;
	
	private int used;
}
