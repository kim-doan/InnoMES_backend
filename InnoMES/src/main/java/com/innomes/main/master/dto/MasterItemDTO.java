package com.innomes.main.master.dto;

import java.util.Date;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MasterItemDTO {
	/*MST110 */
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
}
