package com.innomes.main.master.param;

import lombok.Builder;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MasterMaterialParam {
	/*	MST110	*/
	private String itemId;

	private String itemCode;

	private String itemName;

	private String itemType;

	private double lotSize;

	private String lotUnit;

	private double safetyQnt;

	private String safetyUnit;

	private String locCode;

	private Integer invType;

	private String description;

	private String createUser;

	private Date createTime;

	private String updateUser;

	private Date updateTime;
	
	private int used;
	
	/*	MST112	*/
	private String matId;
	
	private String matType;
	
	private String matCtg;
	
	private String matGroup;
	
	private String attMatType;
	
	private String attStdType;
	
	private String attDiaType;
	
	private int incInspYN;
	
	private double incVolStd;
	
	private String incVolUnit;
	
	private Integer lotYN;
	
	private String matProc;

	@Override
	public String toString() {
		return "MasterMaterialParam [itemId=" + itemId + ", itemCode=" + itemCode + ", itemName=" + itemName
				+ ", itemType=" + itemType + ", lotSize=" + lotSize + ", lotUnit=" + lotUnit + ", safetyQnt="
				+ safetyQnt + ", safetyUnit=" + safetyUnit + ", locCode=" + locCode + ", invType=" + invType
				+ ", description=" + description + ", createUser=" + createUser + ", createTime=" + createTime
				+ ", updateUser=" + updateUser + ", updateTime=" + updateTime + ", used=" + used + ", matId=" + matId
				+ ", matType=" + matType + ", matCtg=" + matCtg + ", matGroup=" + matGroup + ", attMatType="
				+ attMatType + ", attStdType=" + attStdType + ", attDiaType=" + attDiaType + ", incInspYN=" + incInspYN
				+ ", incVolStd=" + incVolStd + ", incVolUnit=" + incVolUnit + ", lotYN=" + lotYN + ", matProc="
				+ matProc + "]";
	}
}
