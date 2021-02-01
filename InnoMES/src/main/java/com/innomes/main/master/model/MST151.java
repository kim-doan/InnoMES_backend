package com.innomes.main.master.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@IdClass(MST151PK.class)
@RequiredArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "MST151")
@Builder
public class MST151 {
	@Id
	@Column(name = "ITEM_ID", insertable = false, updatable = false)
	private String itemId;
	
	@Id 
	@Column(name = "COMP_ID", insertable = false, updatable = false)
	private String compId;
	
	@Id 
	@Column(name = "PRICE_REV", insertable = false, updatable = false)
	private int priceRev;
	
	@Id 
	@Column(name = "PRICE_TYPE", insertable = false, updatable = false)
	private String priceType;
	
	@Column(name = "PRICE_REV_CAUSE")
	private String priceRevCause;
	
	@Column(name = "PRICE_REV_USER")
	private String priceRevUser;
	
	@Column(name = "COMP_ITEM_ID")
	private String compItemId;
	
	@Column(name = "PRICE_STD")
	private double pricdStd;
	
	@Column(name = "PRICE_UNIT")
	private String priceUnit;
	
	@Column(name = "DELIVERY_TYPE")
	private String deliveryType;
	
	@Column(name = "DELIVERY_DAY")
	private Integer deliveryDay;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "CREATE_USER")
	private String createUser;
	
	@Column(name = "CREATE_TIME")
	private Date createTime;
	
	@Column(name = "UPDATE_USER")
	private String updateUser;
	
	@Column(name = "UPDATE_TIME")
	private Date updateTime;
	
	@Column(name = "USED")
	private int used;
	
}
