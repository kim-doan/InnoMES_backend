package com.innomes.main.master.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MasterMaterialDTO {
	/*	MST110	*/
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
	
	private String description;
	
	private String createUser;
	
	private Date createTime;

	private String updateUser;
	
	private Date updateTime;
	
	private int used;
	
	/*	MST112	*/
	private String matId;
	
	private String matType;
	
	private String matCtg;
	
	private String matGroup;
	
	private String attMatType;
	
	private String attStdType;
	
	private String attDiaType;
	
	private int incInspYN;
	
	private double incVolStd;
	
	private String incVolUnit;
	
	private Integer lotYN;
	
	private String matProc;
}
