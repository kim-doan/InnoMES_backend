package com.innomes.main.code.param;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CodeInfoParam {
	@ApiModelProperty(value = "코드(PK)" + "\n Code" + "\n COD100.CODE")
	private String code;

	@ApiModelProperty(value = "코드 산출 유형(0:Category, 1: ALL)" + "\n Code type")
	private Integer displayType; // 코드 뽑는 유형 (0: 카테고리별, 그 외 전체)

	@ApiModelProperty(value = "코드유형" + "\n Code Output Type" + "\n COD100.CODE_TYPE")
	private Integer codeType;

	@ApiModelProperty(value = "상위코드" + "\n Parent code" + "\n COD100.P_CODE")
	private String pCode;

	@ApiModelProperty(value = "코드명(한국어)" + "\n Code name(Korean) " + "\n COD100.CODE_KR")
	private String codeKR;

	@ApiModelProperty(value = "코드명(영어)" + "\n Code name(English) " + "\n COD100.CODE_EN")
	private String codeEN;

	@ApiModelProperty(value = "코드명(중국어)" + "\n Code name(Chinese) " + "\n COD100.CODE_CH")
	private String codeCH;

	@ApiModelProperty(value = "코드명(일본어)" + "\n Code name(Japanese) " + "\n COD100.CODE_JP")
	private String codeJP;

	@ApiModelProperty(value = "최대수준" + "\n Max level " + "\n COD100.MAX_LEVEL")
	private Integer maxLevel;

	@ApiModelProperty(value = "고정수준" + "\n Fixed levels" + "\n COD100.FIX_LEVEL")
	private Integer fixLevel;

	@ApiModelProperty(value = "코드수준" + "\n Code level" + "\n COD100.CODE_LEVEL")
	private Integer codeLevel;

	@ApiModelProperty(value = "사용자코드 사용여부(0: 사용 / 1: 비사용)" + "\n User code activation" + "\n COD100.DISPLAY_CODE_YN")
	private Integer displayCodeYN;

	@ApiModelProperty(value = "사용자코드" + "\n User code" + "\n COD100.DISPLAY_CODE_YN")
	private String displayCode;

	@ApiModelProperty(value = "사용자코드 카테고리" + "\n User code division" + "\n COD100.CODE_CTG")
	private String codeCtg;

	@ApiModelProperty(value = "기본값 여부" + "\n Default?" + "\n COD100.DEFAULT_YN")
	private Integer defaultYN;

	@ApiModelProperty(value = "비고" + "\n remarks" + "\n COD100.DESCRIPTION")
	private String description;

	@ApiModelProperty(value = "생성자" + "\n Author" + "\n COD100.CREATE_USER")
	private String createUser;

	@ApiModelProperty(value = "생성일시" + "\n Created time" + "\n COD100.CREATE_TIME")
	private Date createTime;

	@ApiModelProperty(value = "수정자" + "\n Modified by" + "\n COD100.UPDATE_USER")
	private String updateUser;

	@ApiModelProperty(value = "수정일시" + "\n Modified time" + "\n COD100.UPDATE_TIME")
	private Date updateTime;

	@ApiModelProperty(value = "사용여부(1: 가용 / 0: 비가용)" + "\n Use/Unuse (1: Use / 0: Unuse)" + "\n COD100.USED")
	private Integer used;
}
