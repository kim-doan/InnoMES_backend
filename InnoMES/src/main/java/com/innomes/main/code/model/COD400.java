package com.innomes.main.code.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/*
 * TABLE ID : COD400
 * TABLE NAME : 고장유형정보
 * AUTHOR : 서영의
 * 
 */
@Entity
@Table(name = "COD400")
@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class COD400 {
	
	@ApiModelProperty(value = "고장유형코드(PK)"
			+ "\n Failure type code"
			+ "\n COD400.FAIL_CODE")
	@Id
	@Column(name="FAIL_CODE")
	private String failCode; // 고장유형코드
	
	@ApiModelProperty(value = "사용자코드"
			+ "\n User Code"
			+ "\n COD400.DISPLAY_CODE")
	@Column(name="DISPLAY_CODE")
	private String displayCode; // 사용자코드
	
	@ApiModelProperty(value = "고장유형분류"
			+ "\n Failure classification"
			+ "\n COD400.FAIL_CLASS")
	@Column(name="FAIL_CLASS")
	private String failClass; // GRP006 하위코드 lv3
	
	@ApiModelProperty(value = "고장유형구분"
			+ "\n Failure type"
			+ "\n COD400.FAIL_TYPE")
	@Column(name="FAIL_TYPE")
	private String failType; // MFT 하위코드 lv2
	
	@ApiModelProperty(value = "고장유형명"
			+ "\n Failure name"
			+ "\n COD400.FAIL_NAME")
	@Column(name="FAIL_NAME")
	private String failName;
	
	@ApiModelProperty(value = "비고"
			+ "\n Remarks"
			+ "\n COD400.DESCRIPTION")
	@Column(name="DESCRIPTION")
	private String description;
	
	@ApiModelProperty(value = "생성자"
			+ "\n Author"
			+ "\n COD400.CREATE_USER")
	@Column(name="CREATE_USER", insertable = true, updatable = false)
	private String createUser;
	
	@ApiModelProperty(value = "생성일시"
			+ "\n Created time"
			+ "\n COD300.CREATE_TIME")
	@Column(name="CREATE_TIME", insertable = true, updatable = false)
	private Date createTime;
	
	@ApiModelProperty(value = "수정자"
			+ "\n Modified by"
			+ "\n COD400.UPDATE_USER")
	@Column(name="UPDATE_USER", insertable = false, updatable = true)
	private String updateUser;
	
	@ApiModelProperty(value = "수정일시"
			+ "\n Modified time"
			+ "\n COD300.UPDATE_TIME")
	@Column(name="UPDATE_TIME", insertable = false, updatable = true)
	private Date updateTime;
	
	@ApiModelProperty(value = "사용여부(0: 가용 / 1:비가용)"
			+ "\n Use/Unuse( 0 : Use / 1 : Unuse)"
			+ "\n COD400.USED")
	@Column(name="USED")
	private Integer used;

}
