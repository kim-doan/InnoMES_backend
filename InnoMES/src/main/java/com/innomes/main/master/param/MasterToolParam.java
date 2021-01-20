package com.innomes.main.master.param;

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
public class MasterToolParam {
	
	/* save */
	private MST110 mst110;
	
	private MST113 mst113;

	/* search */
	// MST110 PARAM
	private String itemCode;
	
	private String itemName;
	
	private String itemType;
	
	private int invType;
	
	// MST113 PARAM
	private String toolType;
	
	private String toolCtg;
	
	private String toolGroup;

	@Override
	public String toString() {
		return "MasterToolParam [mst110=" + mst110 + ", mst113=" + mst113 + ", itemCode=" + itemCode + ", itemName=" + itemName + ", itemType=" + itemType + ", invType=" + invType + ", toolType=" + toolType + ", toolCtg=" + toolCtg
				+ ", toolGroup=" + toolGroup + "]";
	}
	
	
}
