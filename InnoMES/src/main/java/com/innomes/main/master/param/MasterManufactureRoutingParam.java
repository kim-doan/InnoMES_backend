package com.innomes.main.master.param;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.RequiredArgsConstructor;

import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MasterManufactureRoutingParam {
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
	
	List<MasterBomParam> bomList = new ArrayList<MasterBomParam>();
}
