package com.innomes.main.master.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MasterPriceItemParam {
	
	private String itemId;
	
	private String itemCode;
	
	private String itemName;
	
	private String compId;
	
	private String priceType;
}
