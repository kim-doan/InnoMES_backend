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
public class MasterCompanyParam {
	private String compId;
	
	private String compName;
	
	private String companyNo;
	
	private Integer saleYN;
	
	private Integer purchaseYN;
	
	private Integer outsourcingYN;
	
	private Integer domesticYN;
	
	private String destributeType;
	
	private String business;
	
	private String industry;
	
	private String representative;
	
	private String compPhone;
	
	private String compFax;
	
	private String compEmail;
	
	private String postNo;
	
	private String address;
	
	private String homepage;
	
	private String mainCharge;
	
	private String chargePhone;
	
	private String chargeCp;
	
	private String chargeFax;
	
	private String chargeEmail;
	
	private String description;
	
	private String createUser;
	
	private Date createTime;
	
	private String updateUser;
	
	private Date updateTime;
	
	private Integer used;
}
