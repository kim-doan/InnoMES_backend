package com.innomes.main.code.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/*
 * TABLE ID : COD100
 * TABLE NAME : 코드정보
 * AUTHOR : 김도안
 * 
 */
@Entity
@Table(name = "COD100") // 임시
@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class COD100 {
	@Id
	@Column(name="CODE")
	private String code;
	
	@Column(name="CODE_TYPE")
	private Integer codeType;
	
	@JsonProperty(value="pCode")
	@Column(name="P_CODE")
	private String pCode;
	
	@Column(name="CODE_KR")
	private String codeKR;
	
	@Column(name="CODE_EN")
	private String codeEN;
	
	@Column(name="CODE_CH")
	private String codeCH;
	
	@Column(name="CODE_JP")
	private String codeJP;
	
	@Column(name="MAX_LEVEL")
	private Integer maxLevel;
	
	@Column(name="FIX_LEVEL")
	private Integer fixLevel;
	
	@Column(name="CODE_LEVEL")
	private Integer codeLevel;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="DISPLAY_CODE_YN")
	private Integer displayCodeYN;
	
	@Column(name="DISPLAY_CODE")
	private String displayCode;
	
	@Column(name="CODE_CTG")
	private String codeCtg;
	
	@Column(name="DEFAULT_YN")
	private Integer defaultYN;
	
	@Column(name="CREATE_USER", insertable = true, updatable = false)
	private String createUser;
	
	@Column(name="CREATE_TIME", insertable = true, updatable = false)
	private Date createTime;
	
	@Column(name="UPDATE_USER", insertable = false, updatable = true)
	private String updateUser;
	
	@Column(name="UPDATE_TIME", insertable = false, updatable = true)
	private Date updateTime;
	
	@Column(name="USED", insertable = true, updatable = true)
	private Integer used;
}
