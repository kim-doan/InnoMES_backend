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
public class MasterToolParam implements Pageable{

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
	
	private int lotYN;
	
	private String repItemId;

	@Override
	public String toString() {
		return "MasterToolParam [itemCode=" + itemCode + ", itemName=" + itemName + ", itemType=" + itemType + ", invType=" + invType + ", toolType=" + toolType + ", toolCtg=" + toolCtg
				+ ", toolGroup=" + toolGroup + "]";
	}

	@Override
	public int getPageNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPageSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getOffset() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Sort getSort() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pageable next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pageable previousOrFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pageable first() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
