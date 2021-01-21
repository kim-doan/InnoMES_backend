package com.innomes.main.master.param;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MasterProcessParam {
	@ApiModelProperty(required = true, value = "공정코드(PK)"
			+ "\n ProcessCode"
			+ "\n MST120.PROC_CODE")
	private String procCode;
	
	@ApiModelProperty(required = true, value = "공정명"
			+ "\n Process Name"
			+ "\n MST120.PROC_NAME")	
	private String procName;

	@ApiModelProperty(value = "공정유형"
			+ "\n Process type"
			+ "\n MST120.PROC_TYPE")
	private String procType;
	
	@ApiModelProperty(value = "내외작구분 (0: 사내 / 1: 외작 / 2: 내외작)"
			+ "\n Insourcing/Outsourcing(0 : Insourcing, shipping(IN, SP) / 1 : Outsourcing / 2 : Both)"
			+ "\n MST120.IN_OUT_TYPE")
	private int inOutType;
	
	@ApiModelProperty(value = "생산공정유무 (0: 생산공정 / 1: 비생산공정)"
			+ "\n Production Process?(0 : Prod.Process / 1 : Non-prod. process(IN, SP))"
			+ "\n MST120.PRDTION_YN")
	private int prdtionYN;
	
	@ApiModelProperty(value = "수불관리유무(0: 수불관리 / 1: 비수불관리)"
			+ "\n IN-OUT management(0 : Manage / 1 : Not manage)"
			+ "\n MST120.SUPLLY_YN")
	private int supplyYN;
	
	@ApiModelProperty(value = "지시유무 (0: 지시공정 / 1: 비지시공정)"
			+ "\n Ordered?(0 : Process with order / 1 : Process without order)"
			+ "\n MST120.WORK_ORDER_YN")
	private int workOrderYN;
	
	@ApiModelProperty(value = "기본공정여부 (0: 필수 / 1: 사용자 지정)"
			+ "\n Default Process(0 : Default(IN, SP) / 1 : User assignment)"
			+ "\n MST120.DEFAULT_YN")
	private Integer defaultYN;
	
	@ApiModelProperty(required = true, value = "최종검사 여부 (0: Y / 1: N)"
			+ "\n FinalInspection?"
			+ "\n MST120.PROC_NAME")
	private Integer inspFinishedYN;
	
	@ApiModelProperty(required = true, value = "조립공정 여부 (0: Y / 1: N)"
			+ "\n Assembly Process?"
			+ "\n MST120.AS_YN")
	private Integer asYN;
	
	@ApiModelProperty(value = "비고"
			+ "\n Remarks"
			+ "\n MST120.DESCRIPTION")
	private String description;
	
	@ApiModelProperty(required = true, value = "생성자"
			+ "\n Author"
			+ "\n MST120.CREATE_USER")
	private String createUser;

	@ApiModelProperty(required = true, value = "생성일시"
			+ "\n Created time"
			+ "\n MST120.CREATE_TIME")
	private Date createTime;
	
	@ApiModelProperty(value = "수정자"
			+ "\n Modified by"
			+ "\n MST120.UPDATE_USER")
	private String updateUser;
	
	@ApiModelProperty(value = "수정일시"
			+ "\n Modified time"
			+ "\n MST120.UPDATE_TIME")
	private Date updateTime;
	
	@ApiModelProperty(required = true, value = "사용여부 (0: 가용 / 1: 비가용)"
			+ "\n Use/Unuse(0 : Use / 1 : Unuse)"
			+ "\n MST120.USED")
	private int used = 0;
}
