package com.innomes.main.code.model;

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

/*
 * TABLE ID : COD300
 * TABLE NAME : 비가동유형정보
 * AUTHOR : 서영의
 * 
 */
@Entity
@Table(name = "COD300")
@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class COD300 {
	@ApiModelProperty(required = true, value = "비가동유형코드(PK)"
			+ "\n Stop Code"
			+ "\n COD300.STOP_CODE")
	@Id
	@Column(name="STOP_CODE")
	private String stopCode;
	
	@ApiModelProperty(required = true, value = "사용자코드"
			+ "\n Display Code"
			+ "\n COD300.DISPLAY_CODE")
	@Column(name="DISPLAY_CODE")
	private String displayCode;
	
	@ApiModelProperty(required = true, value = "비가동유형명"
			+ "\n Stop Name"
			+ "\n COD300.STOP_NAME")
	@Column(name="STOP_NAME")
	private String stopName;
	
	@ApiModelProperty(required = true, value = "비가동구분"
			+ "\n Stop Type"
			+ "\n COD300.STOP_TYPE")
	@Column(name="STOP_TYPE")
	private String stopType;
	
	@ApiModelProperty(required = true, value = "비가동분류"
			+ "\n Stop class"
			+ "\n COD300.STOP_CLASS")
	@Column(name="STOP_CLASS")
	private String stopClass;
	
	@ApiModelProperty(required = true, value = "공정유형 (공정 대분류 개념. 불량구분이 공정구분이 아닐 경우 NULL)"
			+ "\n Proc Type"
			+ "\n COD300.PROC_TYPE")
	@Column(name="PROC_TYPE")
	private String procType;
	
	@ApiModelProperty(required = true, value = "종합효율적용여부 (0 : 종합효율산출 시 비가동 포함 / 1 : 종합효율산출 시 비가동 미포함)"
			+ "\n Oee Yn"
			+ "\n COD300.OEE_YN")
	@Column(name="OEE_YN")
	private int oeeYN;
	
	@ApiModelProperty(required = true, value = "비고"
			+ "\n Description"
			+ "\n COD300.DESCRIPTION")
	@Column(name="DESCRIPTION")
	private String description;
	
	@ApiModelProperty(required = true, value = "생성자"
			+ "\n Author"
			+ "\n COD300.CREATE_USER")
	@Column(name="CREATE_USER", insertable = true, updatable = false)
	private String createUser;
	
	@ApiModelProperty(required = true, value = "생성일시"
			+ "\n Created time"
			+ "\n COD300.CREATE_TIME")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="CREATE_TIME", insertable = true, updatable = false)
	private Date createTime;
	
	@ApiModelProperty(value = "수정자" 
			+ "\n Modified by"
			+ "\n COD300.UPDATE_USER")
	@Column(name="UPDATE_USER", insertable = false, updatable = true)
	private String updateUser;
	
	@ApiModelProperty(value = "수정일시"
			+ "\n Modified time"
			+ "\n COD300.UPDATE_TIME")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="UPDATE_TIME", insertable = false, updatable = true)
	private Date updateTime;
	
	@ApiModelProperty(value = "사용여부 (0: 가용 / 1: 비가용)"
			+ "\n Use/Unuse (0: Use / 1: Unuse)"
			+ "\n COD300.USED")
	@Column(name="USED", insertable = false, updatable = true)
	private Integer used;

}
