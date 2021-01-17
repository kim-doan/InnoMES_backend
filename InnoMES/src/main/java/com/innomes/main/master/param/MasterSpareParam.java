package com.innomes.main.master.param;

import java.util.Date;

import com.innomes.main.master.param.MasterProductParam.MasterProductParamBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MasterSpareParam {
	/*	MST110	*/
	private String itemId;
	
	private String itemCode;
	
	private String itemName;
	
	private String itemType;
	
	private Double lotSize;
	
	private String lotUnit;
	
	private Double safetyQnt;
	
	private String safetyUnit;
	
	private String locCode;
	
	private Integer invType;
	
	private String description;
	
	private String createUser;
	
	private Date createTime;

	private String updateUser;
	
	private Date updateTime;
	
	private int used;
	
	/*	MST113	*/
	private String partId;
	
	private String partType;
	
	private String partCtg;
	
	private String partGroup;
	
	private double partShiftCycle;
	
	private String cycleUnit;
	
	private int lotYN;

	@Override
	public String toString() {
		return "MasterSpareParam [itemId=" + itemId + ", itemCode=" + itemCode + ", itemName=" + itemName
				+ ", itemType=" + itemType + ", lotSize=" + lotSize + ", lotUnit=" + lotUnit + ", safetyQnt="
				+ safetyQnt + ", safetyUnit=" + safetyUnit + ", locCode=" + locCode + ", invType=" + invType
				+ ", description=" + description + ", createUser=" + createUser + ", createTime=" + createTime
				+ ", updateUser=" + updateUser + ", updateTime=" + updateTime + ", used=" + used + ", partId=" + partId
				+ ", partType=" + partType + ", partCtg=" + partCtg + ", partGroup=" + partGroup + ", partShiftCycle="
				+ partShiftCycle + ", cycleUnit=" + cycleUnit + ", lotYN=" + lotYN + "]";
	}
}
