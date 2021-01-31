package com.innomes.main.master.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "MST150")
@Builder
public class MST150 {
	@Id @Column(name="COMP_ID")
	private String compId;
	@Column(name="COMP_NAME")
	private String compName;
	@Column(name="SALE_YN")
	private Integer saleYN;
	@Column(name="PURCHASE_YN")
	private Integer purchaseYN;
	@Column(name="OUTSOURCING_YN")
	private Integer outsourcingYN;
	@Column(name="DOMESTIC_YN")
	private Integer domesticYN;
	@Column(name="DESTRIBUTE_TYPE")
	private String destributeType;
	@Column(name="BUSINESS")
	private String business;
	@Column(name="INDUSTRY")
	private String industry;
	@Column(name="REPRESENTATIVE")
	private String representative;
	@Column(name="COMP_PHONE")
	private String compPhone;
	@Column(name="COMP_FAX")
	private String compFax;
	@Column(name="COMP_EMAIL")
	private String compEmail;
	@Column(name="POST_NO")
	private String postNo;
	@Column(name="ADDRESS")
	private String address;
	@Column(name="HOMEPAGE")
	private String homepage;
	@Column(name="MAIN_CHARGE")
	private String mainCharge;
	@Column(name="CHARGE_PHONE")
	private String chargePhone;
	@Column(name="CHARGE_CP")
	private String chargeCp;
	@Column(name="CHARGE_FAX")
	private String chargeFax;
	@Column(name="CHARGE_EMAIL")
	private String chargeEmail;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="CREATE_USER")
	private String createUser;
	@Column(name="CREATE_TIME")
	private Date createTime;
	@Column(name="UPDATE_USER")
	private String updateUser;
	@Column(name="UPDATE_TIME")
	private Date updateTime;
	@Column(name="USED")
	private int used;
	
}
