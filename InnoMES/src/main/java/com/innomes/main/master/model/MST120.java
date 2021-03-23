package com.innomes.main.master.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "MST120")
@Builder 
public class MST120{
	@Id
	@ApiModelProperty(required = true, value = "공정코드(PK)"
			+ "\n ProcessCode"
			+ "\n MST120.PROC_CODE")
	@Column(name = "PROC_CODE")
	private String procCode;
	
	@ApiModelProperty(required = true, value = "공정명"
			+ "\n Process Name"
			+ "\n MST120.PROC_NAME")	
	@Column(name = "PROC_NAME")
	private String procName;
	
	@ApiModelProperty(value = "공정유형"
			+ "\n Process type"
			+ "\n MST120.PROC_TYPE")
	@Column(name = "PROC_TYPE")
	private String procType;
	
	@ApiModelProperty(value = "내외작구분 (TPS019001: 사내 / TPS019002: 외주/ TPS019003: 내외작)"
			+ "\n Insourcing/Outsourcing(0 : Insourcing, shipping(IN, SP) / 1 : Outsourcing / 2 : Both)"
			+ "\n MST120.IN_OUT_TYPE")
	@Column(name = "IN_OUT_TYPE")
	private String inOutType;
	
	@ApiModelProperty(value = "생산공정유무 (0: 생산공정 / 1: 비생산공정)"
			+ "\n Production Process?(0 : Prod.Process / 1 : Non-prod. process(IN, SP))"
			+ "\n MST120.PRDTION_YN")
	@Column(name = "PRDTION_YN")
	private Integer prdtionYN;
	
	@ApiModelProperty(value = "수불관리유무(0: 수불관리 / 1: 비수불관리)"
			+ "\n IN-OUT management(0 : Manage / 1 : Not manage)"
			+ "\n MST120.SUPLLY_YN")
	@Column(name = "SUPLLY_YN")
	private Integer supplyYN;
	
	@ApiModelProperty(value = "지시유무 (0: 지시공정 / 1: 비지시공정)"
			+ "\n Ordered?(0 : Process with order / 1 : Process without order)"
			+ "\n MST120.WORK_ORDER_YN")
	@Column(name = "WORK_ORDER_YN")
	private Integer workOrderYN;
	
	@ApiModelProperty(value = "기본공정여부 (0: 필수 / 1: 사용자 지정)"
			+ "\n Default Process(0 : Default(IN, SP) / 1 : User assignment)"
			+ "\n MST120.DEFAULT_YN")
	@Column(name = "DEFAULT_YN")
	private Integer defaultYN;
	
	@ApiModelProperty(required = true, value = "최종검사 여부 (0: Y / 1: N)"
			+ "\n FinalInspection?"
			+ "\n MST120.PROC_NAME")
	@Column(name = "INSP_FINISHED_YN")
	private Integer inspFinishedYN;
	
	@ApiModelProperty(required = true, value = "조립공정 여부 (0: Y / 1: N)"
			+ "\n Assembly Process?"
			+ "\n MST120.AS_YN")
	@Column(name = "AS_YN")
	private Integer asYN;
	
	@ApiModelProperty(value = "비고"
			+ "\n Remarks"
			+ "\n MST120.DESCRIPTION")
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name="USED", insertable = true, updatable = true)
	private Integer used;
	
	@ApiModelProperty(required = true, value = "생성자"
			+ "\n Author"
			+ "\n MST120.CREATE_USER")
	@Column(name="CREATE_USER", insertable = true, updatable = false)
	private String createUser;
	
	@ApiModelProperty(required = true, value = "생성일시"
			+ "\n Created time"
			+ "\n MST120.CREATE_TIME")
	
	@Column(name="CREATE_TIME", insertable = true, updatable = false)
	private Date createTime;
	
	@ApiModelProperty(value = "수정자" 
			+ "\n Modified by"
			+ "\n MST120.UPDATE_USER")
	@Column(name="UPDATE_USER", insertable = false, updatable = true)
	private String updateUser;
	
	@ApiModelProperty(value = "수정일시"
			+ "\n Modified time"
			+ "\n MST120.UPDATE_TIME")
	@Column(name="UPDATE_TIME", insertable = false, updatable = true)
	private Date updateTime;
	
}
