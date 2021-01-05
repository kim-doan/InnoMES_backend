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
public class MasterProductDTO {
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
	
	private String description;
	
	private String createUser;
	
	private Date createTime;

	private String updateUser;
	
	private Date updateTime;
	
	private int used;
	
	// MST111
	private String prdtId;
	
	private String prdtType;
	
	private String prdtCtg;
	
	private String prdtGroup;
	
	private String attMatType;
	
	private String attStdType;
	
	private String attDiaType;
	
	private String heatSpec;
	
	private String surfaceSpec;
	
	private String coatingSpec;
	
	private Double batchSize;
	
	private String batchUnit;
	
	private String matProc;
}
