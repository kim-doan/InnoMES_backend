package com.innomes.main.master.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
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
@Table(name = "MST111")
@Builder
public class MST111 {
	@Id
	@Column(name="PRDT_ID")
	private Integer prdtId;
	
	@Column(name="PRDT_TYPE")
	private String prdtType;
	
	@Column(name="PRDT_CTG")
	private String prdtCtg;
	
	@Column(name="PRDT_GROUP")
	private String prdtGroup;
	
	@Column(name="ATT_MAT_TYPE")
	private String attMatType;
	
	@Column(name="ATT_STD_TYPE")
	private String attStdType;
	
	@Column(name="ATT_DIA_TYPE")
	private String attDiaType;
	
	@Column(name="HEAT_SPEC")
	private String heatSpec;
	
	@Column(name="SURFACE_SPEC")
	private String surfaceSpec;
	
	@Column(name="COATING_SPEC")
	private String coatingSpec;
	
	@Column(name="BATCH_SIZE")
	private double batchSize;
	
	@Column(name="BATCH_UNIT")
	private String batchUnit;
	
	@Column(name="MAT_PROC")
	private String matProc;
	
    @OneToOne
    @MapsId
    @JoinColumn(name = "PRDT_ID")
    private MST110 mst110;
}