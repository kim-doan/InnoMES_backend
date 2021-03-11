package com.innomes.main.purchase.model;

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
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@IdClass(PUR101PK.class)
@RequiredArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "PUR101")
@Builder
public class PUR101 implements Persistable<PUR101PK> {

	@Id
	@Column(name = "REQ_NO")
	private String reqNo;

	@Id
	@Column(name = "REQ_SEQ")
	private int reqSeq;

	@Column(name = "REQ_CODE")
	private String reqCode;

	@Column(name = "REQ_QNT")
	private double reqQnt;

	@Column(name = "REQ_UNIT")
	private String reqUnit;

	@Column(name = "REQ_COMP")
	private String reqComp;

	@Column(name = "REQ_DUE")
	private Date reqDue;

	@Column(name = "REQ_LOC")
	private String reqLoc;

	@Column(name = "ORDER_YN")
	private int orderYN;

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
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
	@JoinColumns(value = {
			@JoinColumn(name = "REQ_NO", referencedColumnName = "REQ_NO", insertable = false, updatable = false)
	})
	private PUR100 pur100;
	
	@Transient
	private boolean isNew = false;

	@Override
	public PUR101PK getId() {
		return PUR101PK.builder()
				.reqNo(reqNo)
				.reqSeq(reqSeq)
				.build();
	}

	@Override
	public boolean isNew() {
		return isNew;
	}
}
