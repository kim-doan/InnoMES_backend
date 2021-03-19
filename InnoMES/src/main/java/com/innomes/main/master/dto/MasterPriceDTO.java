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
	
	private String itemCode;
	
	private String itemName;

	private String compId;
	
	private String compName;

	private int priceRev;

	private String priceType;

	private String priceRevCause;

	private String priceRevUser;

	private String compItemId;

	private double priceStd;

	private String priceUnit;

	private String deliveryType;

	private Integer deliveryDay;

	private String description;

	private String createUser;

	private Date createTime;

	private String updateUser;

	private Date updateTime;

	private int used;
}
