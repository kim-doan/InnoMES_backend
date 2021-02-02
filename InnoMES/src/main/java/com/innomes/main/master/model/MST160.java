package com.innomes.main.master.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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
@Table(name = "MST160")
@Builder
public class MST160 {
	@Id
	@ApiModelProperty(required = true, value = "적재위치코드(PK)"
			+ "\n Location Code"
			+ "\n MST160.LOC_CODE")
	@Column(name = "LOC_CODE")
	private String locCode;
	
	@ApiModelProperty(required = true, value = "위치명"
			+ "\n Location Name"
			+ "\n MST160.LOC_NAME")
	@Column(name = "LOC_NAME")
	private String locName;
	
	@ApiModelProperty(required = true, value = "위치유형"
			+ "\n Location Type"
			+ "\n MST160.LOC_TYPE")
	@Column(name = "LOC_TYPE")
	private String locType;
	
	@ApiModelProperty(required = true, value = "공장코드"
			+ "\n Factory CODE"
			+ "\n MST160.FTR_CODE")
	@Column(name = "FTR_CODE")
	private String ftrCode;
	
	@ApiModelProperty(required = true, value = "공정코드(FK - MST120.PROC_CODE)"//대기장소일 경우 생산공정, 그 외의 경우 출고공정 사용
			+ "\n Process Code"
			+ "\n MST160.PROC_CODE")
	@Column(name = "PROC_CODE")
	private String procCode;
	
	@ApiModelProperty(required = true, value = "상위위치코드(FK - MST160.LOC_CODE)" // 창고의 경우 최상위, 최상위 코드의 경우 코드 동일하게 구성
			+ "\n Parent Location Code"
			+ "\n MST160.P_LOC_CODE")
	@Column(name = "P_LOC_CODE")
	private String pLocCode;
	
	@ApiModelProperty(required = true, value = "적재용량"
			+ "\n Location Volume"
			+ "\n MST160.LOC_VOL")
	@Column(name = "LOC_VOL")
	private double locVol;
	
	@ApiModelProperty(required = true, value = "적재단위"
			+ "\n Volume Unit"
			+ "\n MST160.VOL_UNIT")
	@Column(name = "VOL_UNIT")
	private String volUnit;
	
	@ApiModelProperty(required = true, value = "비고"
			+ "\n Description"
			+ "\n MST160.DESCRIPTION")
	@Column(name = "DESCRIPTION")
	private String description;
	
	@ApiModelProperty(value = "사용여부(0: 가용 / 1: 비가용)"
			+ "\n Used/Unused"
			+ "\n MST120.USED")
	@Column(name="USED", insertable = false, updatable = true)
	private int used;
	
	@ApiModelProperty(required = true, value = "생성자"
			+ "\n Author"
			+ "\n MST120.CREATE_USER")
	@Column(name="CREATE_USER", insertable = true, updatable = false)
	private String createUser;
	
	@ApiModelProperty(required = true, value = "생성일시"
			+ "\n Created time"
			+ "\n MST120.CREATE_TIME")
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="UPDATE_TIME", insertable = false, updatable = true)
	private Date updateTime;

//	@OneToOne(fetch = FetchType.LAZY, optional = false)
//	@JoinColumn(name ="PROC_CODE", insertable = false, updatable = false, nullable = false)
//	@NotFound(action = NotFoundAction.IGNORE)
//	private MST120 mst120;
}
