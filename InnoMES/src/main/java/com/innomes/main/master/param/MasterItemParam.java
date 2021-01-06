package com.innomes.main.master.param;

import java.util.Date;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class MasterItemParam {
	/*MST110 */
	private String itemId;
	
	private String itemCode;
	
	private String itemName;
	
	private String itemType;
	
	private Double lotSize;
	
	private String lotUnit;
	
	private Double safetyQnt;
	
	private String safetyUnit;
	
	private String locCode;
	
	private Integer invType;
	
	// MST111
	private String prdtId;
	
	private String prdtType;
	
	private String prdtCtg;
	
	private String prdtGroup;
	
	private String heatSpec;
	
	private String surfaceSpec;
	
	private String coatingSpec;
	
	private Double batchSize;
	
	private String batchUnit;
	
	/* MST112*/
	private String matId;
	
	private String matType;
	
	private String matCtg;
	
	private String matGroup;
	
	private int incInspYN;
	
	private Double incVolStd;
	
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
	
	private Double toolLifeCnt;
	
	private Double toolWarningRate;
	
	private Double toolChkCycle;
	
	private Double toolRecycleCnt;
	
	private String repItemId;
	
	private String prdtionProc;
	
	/* MST114 */
	private String partId;

	private String partType;
	
	private String partCtg;
	
	private String partGroup;
	
	private Double partShiftCycle;
	
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
