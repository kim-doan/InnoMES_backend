package com.innomes.main.code.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class FailCodeParam {
	@ApiModelProperty(value = "고장유형코드(PK)" + "\n Failure type code" + "\n COD400.FAIL_CODE")
	private String failCode;

	@ApiModelProperty(value = "사용자코드" + "\n User Code" + "\n COD400.DISPLAY_CODE")
	private String displayCode; // 사용자코드

	@ApiModelProperty(value = "고장유형분류_생산라인" + "\n Failure classification" + "\n COD400.FAIL_CLASS")
	private String failClass; // GRP006 하위코드 lv3

	@ApiModelProperty(value = "고장유형구분" + "\n Failure type" + "\n COD400.FAIL_TYPE")
	private String failType; // MFT 하위코드 lv2

	@ApiModelProperty(value = "고장유형명" + "\n Failure name" + "\n COD400.FAIL_NAME")
	private String failName;

	@ApiModelProperty(value = "비고" + "\n Remarks" + "\n COD400.DESCRIPTION")
	private String description;

	@ApiModelProperty(value = "생성자" + "\n Author" + "\n COD400.CREATE_USER")
	private String createUser;

	@ApiModelProperty(value = "수정자" + "\n Modified by" + "\n COD400.UPDATE_USER")
	private String updateUser;

	@ApiModelProperty(value = "사용여부(1: 가용 / 0:비가용)" + "\n Use/Unuse( 1 : Use / 0 : Unuse)" + "\n COD400.USED")
	private int used;
}
