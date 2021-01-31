package com.innomes.main.master.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MasterPriceDTO {
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
}
