package com.innomes.main.master.dto;

import java.util.ArrayList;
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
public class MasterManufactureRoutingDTO {
	private String prdtId;
	
	private Integer routingRev;
	
	private String procCode;
	
	private String procName;
	
	private boolean passYN;
	
	private String inOutType;
	
	private Integer routingSeq;
	
	private Integer procSeq;
	
	private Double inQnt;
	
	private Double outQnt;
	
	private String qntUnit;
	
	private Integer leadTime;
	
	private Integer settingTime;
	
	private Double unitWeight;
	
	private String locCode;
	
	private List<MasterBomDTO> bomList = new ArrayList<MasterBomDTO>();
}
