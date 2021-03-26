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
public class MasterBomDTO {
	private String prdtId;
	
	private String procCode;
	
	private Integer bomSeq;
	
	private Integer swapSeq;
	
	private String itemId;
	
	private String itemCode;
	
	private String itemName;
	
	private Double inQnt;
	
	private String inUnit;
	
	private String description;
	
	private String createUser;
	
	private Date createTime;
	
	private String updateUser;
	
	private Date updateTime;
	
	private Integer used;
}
