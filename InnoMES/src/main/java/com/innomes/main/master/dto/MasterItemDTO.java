package com.innomes.main.master.dto;

import java.util.Date;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MasterItemDTO {
	/*MST110 */
	private Integer itemId;
	
	private String itemCode;
	
	private String itemName;
	
	private String itemType;
	
	private double lotSize;
	
	private String lotUnit;
	
	private double safetyQnt;
	
	private String safetyUnit;
	
	private String locCode;
	
	private Integer invType;
	
	// MST111
	private Integer prdtId;
	
	private String prdtType;
	
	private String prdtCtg;
	
	private String prdtGroup;
	
	private String heatSpec;
	
	private String surfaceSpec;
	
	private String coatingSpec;
	
	private double batchSize;
	
	private String batchUnit;
	
	/* MST112*/
	private String matId;
	
	private String matType;
	
	private String matCtg;
	
	private String matGroup;
	
	private int incInspYN;
	
	private double incVolStd;
	
	private String incVolUnit;
	
	private int lotYN;
	
	/* MST111, MST112 */
	private String matProc;

	/* MST113 */
	private String toolId;
	
	private String toolType;
	
	private String toolCtg;
	
	private String toolGroup;
	
	private String toolProc;
	
	private double toolLifeCnt;
	
	private double toolWarningRate;
	
	private double toolChkCycle;
	
	private double toolRecycleCnt;
	
	private String repItemId;
	
	private String prdtionProc;
	
	/* MST114 */
	private String partId;

	private String partType;
	
	private String partCtg;
	
	private String partGroup;
	
	private double partShiftCycle;
	
	private String cycleUnit;
	
	/* 공용 */
	private String attMatType;
	
	private String attStdType;
	
	private String attDiaType;
	
	private String description;
	
	private String createUser;
	
	private Date createTime;

	private String updateUser;
	
	private Date updateTime;
	
	private int used;
}
