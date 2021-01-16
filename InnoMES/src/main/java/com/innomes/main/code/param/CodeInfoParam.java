package com.innomes.main.code.param;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public class CodeInfoParam {
	private String code;
	
	private Integer displayType; // 코드 뽑는 유형 (0: 카테고리별, 그 외 전체)
	
	private Integer codeType;
	
	private String pCode;
	
	private String codeKR;
	
	private String codeEN;
	
	private String codeCH;
	
	private String codeJP;
	
	private Integer maxLevel;
	
	private Integer fixLevel;
	
	private Integer codeLevel;
	
	private Integer displayCodeYN;
	
	private String displayCode;
	
	private String codeCtg;
	
	private Integer defaultYN;
	
	private String description;
	
	private String createUser;
	
	private Date createTime;
	
	private String updateUser;
	
	private Date updateTime;
	
	private Integer used;
}
