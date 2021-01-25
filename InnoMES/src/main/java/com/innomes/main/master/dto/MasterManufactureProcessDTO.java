package com.innomes.main.master.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MasterManufactureProcessDTO {
	//MST200
	private String prdtId;
	
	private Integer routingRev;
	
	private String prdtStatus;
	
	private String revUser;
	
	private Date revDate;
	
	private String revCause;
	
	private String description;
	
	private String createUser;
	
	private Date createTime;
	
	private String updateUser;
	
	private Date updateTime;
	
	private int used;
	
	// MST110, 111
	private String itemCode;
	
	private String itemName;
	
	private String prdtType;
	
	private String prdtCtg;
	
	private String prdtGroup;	
	
	private String attMatType;
	
	private String attStdType;
	
	private String attDiaType;
	
	private String heatSpec;
	
	private String surfaceSpec;
	
	private String coatingSpec;
	
	//MST210
	List<MasterManufactureRoutingDTO> routeList = new ArrayList<MasterManufactureRoutingDTO>();
}
