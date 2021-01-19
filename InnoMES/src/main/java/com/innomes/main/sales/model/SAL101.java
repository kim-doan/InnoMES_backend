package com.innomes.main.sales.model;

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
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@IdClass(SAL101PK.class)
@Table(name = "SAL101")
@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class SAL101 implements Persistable<SAL101PK>{
	@Id
	@Column(name = "SAL_PLAN_NO", insertable = false, updatable = false)
	private Integer salPlanNo;
	
	@Id
	@Column(name = "SAL_PLAN_SEQ", insertable = false, updatable = false)
	private Integer salPlanSeq;
	
	@Column(name = "REG_TYPE")
	private String regType;
	
	@Column(name = "REG_USER")
	private String regUser;
	
	@Column(name = "REG_TIME")
	private Date regTime;
	
	@Column(name = "REG_CAUSE")
	private String regCause;

	@Column(name = "PLAN_QNT")
	private Double planQnt; 
	
//	@ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumns(value = {
			@JoinColumn(name = "SAL_PLAN_NO", referencedColumnName = "SAL_PLAN_NO", insertable = false, updatable = false),
	})
	private SAL100 sal100;

	@Transient
	private boolean isNew = false;
	
	@Override
	public SAL101PK getId() {
		return SAL101PK.builder()
				.salPlanNo(salPlanNo)
				.salPlanSeq(salPlanSeq)
				.build();
	}

	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		return isNew;
	}
}
