package com.innomes.main.sales.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@IdClass(SAL111PK.class)
@Table(name = "SAL111")
@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class SAL111 implements Persistable<SAL111PK> {
	@Id
	@Column(name = "ORD_ID", insertable = false, updatable = false)
	private Integer ordId;
	
	@Id
	@Column(name = "ORD_SEQ", insertable = false, updatable = false)
	private Integer ordSeq;
	
	@Column(name = "ORD_QNT")
	private Double ordQnt;
	
	@Column(name = "ORD_REG_TYPE")
	private String ordRegType;
	
	@Column(name = "ORD_REG_USER")
	private String ordRegUser;
	
	@Column(name = "ORD_REG_TIME")
	private Date ordRegTime;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "USED", insertable = false, updatable = true)
	private int used;

	@Transient
	private boolean isNew = false;
	
	@Override
	public SAL111PK getId() {
		// TODO Auto-generated method stub
		return SAL111PK.builder()
				.ordId(ordId)
				.ordSeq(ordSeq)
				.build();
	}

	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		return isNew;
	}
}
