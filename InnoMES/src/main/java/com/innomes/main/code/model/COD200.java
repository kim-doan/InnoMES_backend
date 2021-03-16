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
 * TABLE ID : COD200
 * TABLE NAME : 불량유형정보
 * AUTHOR : 서영의
 * 
 */
@Entity
@Table(name = "COD200")
@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class COD200 {
	
	@ApiModelProperty(value = "불량유형코드(PK)"
			+ "\n Defect Type Code "
			+ "\n COD200.BAD_CODE")
	@Id
	@Column(name="BAD_CODE")
	private String badCode;

	@ApiModelProperty(value = "사용자코드"
			+ "\n User Code"
			+ "\n COD200.DISPLAY_CODE")
	@Column(name="DISPLAY_CODE")
	private String displayCode;

	@ApiModelProperty(value = "불량유형명"
			+ "\n Defect Type Name"
			+ "\n COD200.BAD_NAME")
	@Column(name="BAD_NAME")
	private String badName;
	
	@ApiModelProperty(value = "불량구분"
			+ "\n Defect Type"
			+ "\n COD200.BAD_TYPE")
	@Column(name="BAD_TYPE")
	private String badType;
	
	@ApiModelProperty(value = "불량분류"
			+ "\n Defect Classification"
			+ "\n COD200.BAD_CLASS")
	@Column(name="BAD_CLASS")
	private String badClass;
	
	@ApiModelProperty(value = "공정유형"
			+ "\n Process type"
			+ "\n COD200.PROC_TYPE")
	@Column(name="PROC_TYPE")
	private String procType;
	
	@ApiModelProperty(value = "비고"
			+ "\n Remarks"
			+ "\n COD200.DESCRIPTION")
	@Column(name="DESCRIPTION")
	private String description;
	
	@ApiModelProperty(value = "생성자"
			+ "\n Author"
			+ "\n COD200.CREATE_USER")
	@Column(name="CREATE_USER", insertable = true, updatable = false)
	private String createUser;
	
	@ApiModelProperty(value = "생성일시"
			+ "\n Created time"
			+ "\n COD200.CREATE_TIME")
	@Column(name="CREATE_TIME", insertable = true, updatable = false)
	private Date createTime;
	
	@ApiModelProperty(value = "수정자"
			+ "\n Modified by"
			+ "\n COD200.UPDATE_USER")
	@Column(name="UPDATE_USER", insertable = false, updatable = true)
	private String updateUser;
	
	@ApiModelProperty(value = "수정일시"
			+ "\n Modified time"
			+ "\n COD200.UPDATE_TIME")
	@Column(name="UPDATE_TIME", insertable = false, updatable = true)
	private Date updateTime;
	
	@ApiModelProperty(value = "사용여부(0: 가용 / 1: 비가용)"
			+ "\n Use/Unuse(0 : Use / 1 : Unuse)"
			+ "\n COD200.USED")
	@Column(name="USED", insertable = false, updatable = true)
	private Integer used;

}
