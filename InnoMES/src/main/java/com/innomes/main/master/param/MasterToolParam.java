package com.innomes.main.master.param;

import java.util.Date;

import javax.persistence.Column;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.innomes.main.master.model.MST110;
import com.innomes.main.master.model.MST113;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MasterToolParam{

	// MST110 PARAM
	private String itemId;
	
	private String itemCode;
	
	private String itemName;
	
	private String itemType;
	
	private int invType;
	
	private double lotSize;

	private String lotUnit;

	private double safetyQnt;

	private String safetyUnit;

	private String locCode;

	private String description;
	
	private int used;
	
	private String createUser;
	
	private Date createTime;
	
	private String updateUser;
	
	private Date updateTime;
	
	// MST113 PARAM
	private String toolType; // 공구유형
	
	private String toolCtg; // 공구카테고리
	
	private String toolGroup; // 공구 그룹
	
	private String toolId;
	
	private String toolProc;
	
	private String prdtionProc;
	
	private double toolLifeCnt;
	
	private double toolWarningRate;
	
	private double toolChkCycle;
	
	private double toolRecycleCnt;
	
	private Integer incInspYN;
	
	private String procType;
	
	private int lotYN;
	
	private String repItemId;
	
}
