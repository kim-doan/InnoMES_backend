package com.innomes.main.code.param;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@RequiredArgsConstructor
@SuperBuilder
public class BadCodeParam {
	@ApiModelProperty(value = "불량유형코드(PK)" + "\n Defect Type Code " + "\n COD200.BAD_CODE")
	private String badCode;

	@ApiModelProperty(value = "사용자코드" + "\n User Code" + "\n COD200.DISPLAY_CODE")
	private String displayCode;

	@ApiModelProperty(value = "불량유형명" + "\n Defect Type Name" + "\n COD200.BAD_NAME")
	private String badName;

	@ApiModelProperty(value = "불량구분" + "\n Defect Type" + "\n COD200.BAD_TYPE")
	private String badType;

	@ApiModelProperty(value = "불량분류" + "\n Defect Classification" + "\n COD200.BAD_CLASS")
	private String badClass;

	@ApiModelProperty(value = "공정유형" + "\n Process type" + "\n COD200.PROC_TYPE")
	private String procType;

	@ApiModelProperty(value = "비고" + "\n Remarks" + "\n COD200.DESCRIPTION")
	private String description;

	@ApiModelProperty(value = "생성자" + "\n Author" + "\n COD200.CREATE_USER")
	private String createUser;

	@ApiModelProperty(value = "생성일시" + "\n Created time" + "\n COD200.CREATE_TIME")
	private Date createTime;

	@ApiModelProperty(value = "수정자" + "\n Modified by" + "\n COD200.UPDATE_USER")
	private String updateUser;

	@ApiModelProperty(value = "수정일시" + "\n Modified time" + "\n COD200.UPDATE_TIME")
	private Date updateTime;

	@ApiModelProperty(value = "사용여부(1: 가용 / 0: 비가용)" + "\n Use/Unuse(1 : Use / 0 : Unuse)" + "\n COD200.USED")
	private int used;
}
