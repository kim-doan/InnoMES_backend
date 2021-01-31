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
public class MasterLocationDTO {

	private String locCode;
	
	private String locName;
	
	private String locType;
	
	private String ftrCode;
	
	private String procCode;
	
	private String pLocCode;
	
	private double locVol;
	
	private String volUnit;
	
	private String procName;
	
	private String procType;
	
	private int inOutType;
	
	private int prdtionYN;
	
	private int supplyYN;

	private String description;
	
	private String createUser;
	
	private Date createTime;
	
	private String updateUser;
	
	private Date updateTime;
	
	private int used;
}
