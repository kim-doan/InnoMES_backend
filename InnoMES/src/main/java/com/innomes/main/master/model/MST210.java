package com.innomes.main.master.model;

import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.core.sym.Name;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@IdClass(MST210PK.class)
@Table(name = "MST210")
@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MST210 implements Persistable<MST210PK> {
	@Id
	@Column(name = "PRDT_ID", insertable = false, updatable = false)
	private String prdtId;
	
	@Id
	@Column(name = "ROUTING_REV", insertable = false, updatable = false)
	private Integer routingRev;
	
	@Id
	@Column(name = "PROC_CODE", insertable = false, updatable = false)
	private String procCode;
	
	@Column(name = "ROUTING_SEQ")
	private Integer routingSeq;
	
	@Column(name = "PROC_SEQ")
	private Integer procSeq;
	
	@Column(name = "IN_QNT")
	private Double inQnt;
	
	@Column(name = "OUT_QNT")
	private Double outQnt;
	
	@Column(name = "QNT_UNIT")
	private String qntUnit;
	
	@Column(name = "LEAD_TIME")
	private Integer leadTime;
	
	@Column(name = "SETTING_TIME")
	private Integer settingTime;
	
	@Column(name = "UNIT_WEIGHT")
	private Double unitWeight;
	
	@Column(name = "LOC_CODE")
	private String locCode;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
	@JoinColumns(value = {
			@JoinColumn(name = "PRDT_ID", referencedColumnName = "PRDT_ID", insertable = false, updatable = false),
			@JoinColumn(name = "ROUTING_REV", referencedColumnName = "ROUTING_REV", insertable = false, updatable = false),
	})
	private MST200 mst200;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
	@JoinColumns(value = {
			@JoinColumn(name = "PRDT_ID", referencedColumnName = "PRDT_ID", insertable = false, updatable = false)
	})
	private MST111 mst111;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "PROC_CODE", insertable = false, updatable = false)
	private MST120 mst120;
	
	@Transient
	private boolean isNew = false;

	@Override
	public MST210PK getId() {
		// TODO Auto-generated method stub
		return MST210PK.builder()
				.prdtId(prdtId)
				.routingRev(routingRev)
				.procCode(procCode)
				.build();
	}
	
	@Override
	public boolean isNew() {
		return isNew;
	}
}
