package com.innomes.main.master.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@IdClass(MST220PK.class)
@Table(name = "MST220")
@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MST220 {
	@Id
	@Column(name = "PRDT_ID", insertable = false, updatable = false)
	private String prdtId;
	
	@Id
	@Column(name = "PROC_CODE", insertable = false, updatable = false)
	private String procCode;
	
	@Id
	@Column(name = "BOM_SEQ", insertable = false, updatable = false)
	private Integer bomSeq;
	
	@Id
	@Column(name = "SWAP_SEQ", insertable = false, updatable = false)
	private Integer swapSeq;
	
	@Column(name = "ITEM_ID")
	private String itemId;
	
	@Column(name = "IN_QNT")
	private Double inQnt;
	
	@Column(name = "IN_UNIT")
	private String inUnit;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "CREATE_USER", insertable = true, updatable = false)
	private String createUser;
	
	@Column(name = "CREATE_TIME", insertable = true, updatable = false)
	private Date createTime;
	
	@Column(name = "UPDATE_USER", insertable = false, updatable = true)
	private String updateUser;
	
	@Column(name = "UPDATE_TIME", insertable = false, updatable = true)
	private Date updateTime;
	
	@Column(name = "USED")
	private int used;
}
