package com.innomes.main.master.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "MST110")
@Getter @Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MST110 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ITEM_ID")
	private Integer itemId;
	
	@Column(name = "ITEM_CODE")
	private String itemCode;

	@Column(name = "ITEM_NAME")
	private String itemName;

	@Column(name = "ITEM_TYPE")
	private String itemType;

	@Column(name = "LOT_SIZE")
	private double lotSize;

	@Column(name = "LOT_UNIT")
	private String lotUnit;

	@Column(name = "SAFETY_QNT")
	private double safetyQnt;

	@Column(name = "SAFETY_UNIT")
	private String safetyUnit;

	@Column(name = "LOC_CODE")
	private String locCode;
	
	@Column(name = "INV_TYPE")
	private int invType;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name="CREATE_USER", insertable = true, updatable = false)
	private String createUser;
	
	@Column(name="CREATE_TIME", insertable = true, updatable = false)
	private Date createTime;
	
	@Column(name="UPDATE_USER", insertable = false, updatable = true)
	private String updateUser;
	
	@Column(name="UPDATE_TIME", insertable = false, updatable = true)
	private Date updateTime;
	
	@Column(name="USED", insertable = true, updatable = true)
	private int used;
	
//    @OneToOne(mappedBy = "mst110", cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private MST111 mst111;
}
