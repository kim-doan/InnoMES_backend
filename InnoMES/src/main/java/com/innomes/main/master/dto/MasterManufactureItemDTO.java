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
public class MasterManufactureItemDTO {
	private String prdtId;

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
	
	
	//MST200
	private int routingRev;
	
	private String prdtStatus;
	
	private String revCause;
	
	private String description;
	
	
	private List<MasterManufactureRoutingDTO> routeList = new ArrayList<MasterManufactureRoutingDTO>(); 
}
