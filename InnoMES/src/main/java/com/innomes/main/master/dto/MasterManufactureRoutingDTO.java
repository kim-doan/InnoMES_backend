package com.innomes.main.master.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MasterManufactureRoutingDTO {
	private String prdtId;
	
	private Integer routingRev;
	
	private String procCode;
	
	private Integer routingSeq;
	
	private Integer procSeq;
	
	private Double inQnt;
	
	private Double outQnt;
	
	private String qntUnit;
	
	private Integer leadTime;
	
	private Integer settingTime;
	
	private Double unitWeight;
	
	private String locCode;
}
