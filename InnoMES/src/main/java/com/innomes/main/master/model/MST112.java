package com.innomes.main.master.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
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
@Table(name = "MST112")
@Builder
public class MST112 {
	@Id
	@Column(name="MAT_ID")
	private String matId;
	
	@Column(name="MAT_TYPE")
	private String matType;
	
	@Column(name="MAT_CTG")
	private String matCtg;
	
	@Column(name="MAT_GROUP")
	private String matGroup;
	
	@Column(name="ATT_MAT_TYPE")
	private String attMatType;
	
	@Column(name="ATT_STD_TYPE")
	private String attStdType;
	
	@Column(name="ATT_DIA_TYPE")
	private String attDiaType;
	
	@Column(name="INC_INSP_YN")
	private int incInspYN;
	
	@Column(name="INC_VOL_STD")
	private double incVolStd;
	
	@Column(name="INC_VOL_UNIT")
	private String incVolUnit;
	
	@Column(name="LOT_YN")
	private Integer lotYN;
	
	@Column(name="MAT_PROC")
	private String matProc;
	
	@OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "MAT_ID")
    private MST110 mst110;
}
