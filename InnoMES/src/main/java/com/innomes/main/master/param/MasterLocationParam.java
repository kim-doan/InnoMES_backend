package com.innomes.main.master.param;

import com.innomes.main.master.model.MST160;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MasterLocationParam {
	
	/* search */
	@ApiModelProperty(value = "적재위치코드(PK)"
			+ "\n Loading location code"
			+ "\n MST160.LOC_CODE")
	private String locCode;
	
	@ApiModelProperty(value = "위치명"
			+ "\n Locatioin name"
			+ "\n MST160.LOC_NAME")
	private String locName;
	
	@ApiModelProperty(value = "위치유형"
			+ "\n Location type"
			+ "\n MST160.LOC_TYPE")
	private String locType;
	
	@ApiModelProperty(value = "공장코드"
			+ "\n Factory code"
			+ "\n MST160.FTR_CODE")
	private String ftrCode;
	
	@ApiModelProperty(value = "공정코드(FK - MST120.PROC_CODE)"
			+ "\n Process code"
			+ "\n MST160.PROC_CODE")
	private String procCode;
	
	@ApiModelProperty(value = "사용여부(0: 가용 / 1: 비가용)"
			+ "\n Use/Unuse"
			+ "\n MST160.USED")
	private Integer used;
	
	/* save */
	private MST160 mst160;
}
