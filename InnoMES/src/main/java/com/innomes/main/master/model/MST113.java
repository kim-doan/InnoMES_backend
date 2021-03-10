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
@Table(name = "MST113")
@Builder
public class MST113 {
	@Id
	@Column(name = "TOOL_ID")
	private String toolId;
	
	@Column(name = "TOOL_TYPE")
	private String toolType;
	
	@Column(name = "TOOL_CTG")
	private String toolCtg;
	
	@Column(name = "TOOL_GROUP")
	private String toolGroup;
	
	@Column(name = "TOOL_PROC")
	private String toolProc;
	
	@Column(name = "PRDTION_PROC")
	private String prdtionProc;
	
	@Column(name = "REP_ITEM_ID")
	private String repItemId;
	
	@Column(name = "PROC_TYPE")
	private String procType;
	
	@Column(name = "TOOL_LIFE_CNT")
	private double toolLifeCnt;
	
	@Column(name = "TOOL_WARNING_RATE")
	private double toolWarningRate;
	
	@Column(name = "TOOL_CHK_CYCLE")
	private double toolChkCycle;
	
	@Column(name = "TOOL_RECYCLE_CNT")
	private double toolRecycleCnt;
	
	@Column(name = "INC_INSP_YN")
	private Integer incInspYN;
	
	@Column(name = "LOT_YN")
	private int lotYN;
	
	@OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "TOOL_ID")
    private MST110 mst110;
}
