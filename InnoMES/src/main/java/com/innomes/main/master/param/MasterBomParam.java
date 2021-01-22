package com.innomes.main.master.param;

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
public class MasterBomParam {
	private String prdtId;

	private String procCode;

	private Integer bomSeq;

	private Integer swapSeq;
	
	private String itemId;
	
	private Double inQnt;
	
	private String inUnit;
	
	private String description;

	private String createUser;

	private Date createTime;

	private String updateUser;

	private Date updateTime;

	private int used;
}
