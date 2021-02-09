package com.innomes.main.master.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/*
 * TABLE ID : MST142
 * TABLE NAME : 기능권한정보
 * AUTHOR : 김도안
 * 
 */

@Entity
@Table(name = "MST142")
@IdClass(MST142PK.class)
@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MST142{
	@Id
	@Column(name = "USER_NO", insertable = false, updatable = false)
	private String userNo;
	
	@Id
	@Column(name = "MENU_ID", insertable = false, updatable = false)
	private String menuId;
	
	@Column(name = "FIND_YN")
	private int findYN;
	
	@Column(name = "REG_YN")
	private int regYN;
	
	@Column(name = "MOD_YN")
	private int modYN;
	
	@Column(name = "DEL_YN")
	private int delYN;
	
	@Column(name = "S_FUNC_01_YN")
	private int sFunc01YN;
	
	@Column(name = "S_FUNC_02_YN")
	private int sFunc02YN;
	
	@Column(name = "S_FUNC_03_YN")
	private int sFunc03YN;
	
	@Column(name = "S_FUNC_04_YN")
	private int sFunc04YN;
	
	@Column(name = "S_FUNC_05_YN")
	private int sFunc05YN;
	
	@Column(name = "S_FUNC_06_YN")
	private int sFunc06YN;
	
	@Column(name = "S_FUNC_07_YN")
	private int sFunc07YN;
	
	@Column(name = "S_FUNC_08_YN")
	private int sFunc08YN;
	
	@Column(name = "S_FUNC_09_YN")
	private int sFunc09YN;
	
	@Column(name = "S_FUNC_10_YN")
	private int sFunc10YN;

	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name="CREATE_USER", insertable = true, updatable = false)
	private String createUser;
	
	@Column(name="CREATE_TIME", insertable = true, updatable = false)
	private Date createTime;
	
	@Column(name="UPDATE_USER", insertable = false, updatable = true)
	private String updateUser;
	
	@Column(name="UPDATE_TIME", insertable = false, updatable = true)
	private Date updateTime;
	
	@Column(name="USED", insertable = false, updatable = true)
	private int used;
}
