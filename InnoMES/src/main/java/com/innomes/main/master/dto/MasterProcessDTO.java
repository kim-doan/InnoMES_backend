package com.innomes.main.master.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MasterProcessDTO {

	private String procCode;

	private String procName;

	private String procType;

	private int inOutType;

	private int prdtionYN;

	private int supplyYN;

	private int workOrderYN;

	private int defaultYN;

	private String procTypeName;

	private Integer inspFinishedYN;

	private Integer asYN;

	private String description;
}
