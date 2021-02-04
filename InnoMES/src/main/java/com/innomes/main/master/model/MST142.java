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

	@ManyToOne
	@JoinColumn(name = "USER_NO", insertable = false, updatable = false, nullable = false)
	private MST140 mst140;

	public MST142(String userNo, String menuId, int findYN, int regYN, int modYN, int delYN, int sFunc01YN,
			int sFunc02YN, int sFunc03YN, int sFunc04YN, int sFunc05YN, int sFunc06YN, int sFunc07YN, int sFunc08YN,
			int sFunc09YN, int sFunc10YN, String description, String createUser, Date createTime, String updateUser,
			Date updateTime, int used, MST140 mst140) {
		super();
		this.userNo = userNo;
		this.menuId = menuId;
		this.findYN = findYN;
		this.regYN = regYN;
		this.modYN = modYN;
		this.delYN = delYN;
		this.sFunc01YN = sFunc01YN;
		this.sFunc02YN = sFunc02YN;
		this.sFunc03YN = sFunc03YN;
		this.sFunc04YN = sFunc04YN;
		this.sFunc05YN = sFunc05YN;
		this.sFunc06YN = sFunc06YN;
		this.sFunc07YN = sFunc07YN;
		this.sFunc08YN = sFunc08YN;
		this.sFunc09YN = sFunc09YN;
		this.sFunc10YN = sFunc10YN;
		this.description = description;
		this.createUser = createUser;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
		this.used = used;
		this.mst140 = mst140;
	}
}
