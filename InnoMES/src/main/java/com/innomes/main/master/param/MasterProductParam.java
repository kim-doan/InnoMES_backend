package com.innomes.main.master.param;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MasterProductParam {
	/* MST110 */
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
	
	/* MST111 */
	private String prdtId;

	private String prdtType;

	private String prdtCtg;

	private String prdtGroup;

	private String attMatType;

	private String attStdType;

	private String attDiaType;

	private String heatSpec;

	private String surfaceSpec;

	private String coatingSpec;

	private double batchSize;

	private String batchUnit;
	
	private String matProc;
	
	//MST200
	private String prdtStatus;
}
