package com.innomes.main.sales.model;

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
@IdClass(SAL112PK.class)
@Table(name = "SAL112")
@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class SAL112 {
	@Id
	@Column(name = "COMP_ID")
	private String compId;
	
	@Id
	@Column(name = "ITEM_ID")
	private String itemId;
	
	@Column(name = "CARRY_OVER_QNT")
	private Double carryOverQnt;
	
	@Column(name = "TOTAL_ORD_QNT")
	private Double totalOrdQnt;
	
	@Column(name = "SHIP_QNT")
	private Double shipQnt;
	
	@Column(name = "OVER_DUE_QNT")
	private Double overDueQnt;
	
	@Column(name = "CLOSE_USER")
	private String closeUser;
	
	@Column(name = "CLOSE_DATE")
	private Date closeDate;
}
