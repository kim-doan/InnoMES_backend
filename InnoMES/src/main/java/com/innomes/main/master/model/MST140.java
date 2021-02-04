package com.innomes.main.master.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;
import org.springframework.data.domain.Persistable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.innomes.main.system.model.SYS800;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * TABLE ID : MST140
 * TABLE NAME : 사용자정보
 * AUTHOR : 김도안
 * 
 */
@Entity
@Table(name = "MST140")
@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MST140 implements Persistable<String>, Serializable{
	@Id
	@Column(name="USER_NO")
	private String userNo;
	
	@Column(name="USER_NAME")
	private String userName;
	
	@Column(name="DEPARTMENT_CODE")
	private String departmentCode;
	
	@Column(name="GRADE_CODE")
	private String gradeCode;
	
	@Column(name="USER_TYPE")
	private String userType;
	
	@Column(name="RECRUTE_DATE")
	private Date recruteDate;
	
	@Column(name="RESIGN_DATE")
	private Date resignDate;
	
	@Column(name="TEAM_CODE")
	private String teamCode;
	
	@Column(name="SHIFT")
	private String shift;
	
	@Column(name="LEADER_YN")
	private int leaderYN;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="CREATE_USER", insertable = true, updatable = false)
	private String createUser;
	
	@Column(name="CREATE_TIME", insertable = true, updatable = false)
	private Date createTime;
	
	@Column(name="UPDATE_USER", insertable = false, updatable = true)
	private String updateUser;
	
	@Column(name="UPDATE_TIME", insertable = false, updatable = true)
	private Date updateTime;
	
	private int used;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "TEAM_CODE", insertable = false, updatable = false)
	private MST141 mst141;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = false)
	@JoinColumn(name = "USER_NO", referencedColumnName = "USER_NO")
	private SYS800 sys800;
	
	@Transient
	private boolean isNew = false;
	
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return userNo;
	}

	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		return isNew;
	}
}
