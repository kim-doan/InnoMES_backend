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
public class MasterSpareDTO {
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
	
	/*	MST113	*/
	private String partId;
	
	private String partType;
	
	private String partCtg;
	
	private String partGroup;
	
	private double partShiftCycle;
	
	private String cycleUnit;
	
	private int lotYN;
}
