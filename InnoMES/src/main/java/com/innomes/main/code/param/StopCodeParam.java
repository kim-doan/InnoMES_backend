package com.innomes.main.code.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class StopCodeParam {
	@ApiModelProperty(value = "비가동유형코드(PK)" + "\n DownTimeCode" + "\n COD300.STOP_CODE")
	private String stopCode;

	@ApiModelProperty(value = "유저코드" + "\n User Code" + "\n COD300.USER_CODE")
	private String displayCode;

	@ApiModelProperty(value = "비가동유형명" + "\n DownTimeName" + "\n COD300.STOP_NAME")
	private String stopName;

	@ApiModelProperty(value = "비가동구분" + "\n Downtime Type" + "\n COD300.STOP_TYPE")
	private String stopType;

	@ApiModelProperty(value = "비가동분류" + "\n DownTimeClassification" + "\n COD300.STOP_CLASS")
	private String stopClass;

	@ApiModelProperty(value = "공정유형" + "\n Process type" + "\n COD300.PROC_TYPE")
	private String procType;

	@ApiModelProperty(value = "종합효율적용여부(1: 비가동 포함, 0: 비가동 미포함)" + "\n OEE Reference?(1 : OEE including downtime / 0 : OEE not including downtime)" + "\n COD300.OEE_YN")
	private Integer oeeYN;

	@ApiModelProperty(value = "비고" + "\n Description" + "\n COD300.DESCRIPTION")
	private String description;

	@ApiModelProperty(value = "생성자" + "\n Author" + "\n COD300.CREATE_USER")
	private String createUser;

	@ApiModelProperty(value = "수정자" + "\n Modified by" + "\n COD300.UPDATE_USER")
	private String updateUser;

	@ApiModelProperty(value = "사용여부(1: 가용 / 0: 비가용)" + "\n Use/Unuse (1: Use / 0: Unuse)" + "\n COD300.USED")
	private int used;
}
