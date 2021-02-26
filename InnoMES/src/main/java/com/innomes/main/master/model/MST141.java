package com.innomes.main.master.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/*
 * TABLE ID : MST141
 * TABLE NAME : 작업조정보
 * AUTHOR : 김도안
 * 
 */
@Entity
@Table(name = "MST141")
@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MST141 implements Serializable {
	@Id
	@Column(name="TEAM_CODE")
	private String teamCode;
	
	@Column(name="TEAM_NAME")
	private String teamName;
	
	@Column(name="FTR_CODE")
	private String ftrCode;
	
	@Column(name="PROC_TYPE")
	private String procType;
	
	@Column(name="LINE_CODE")
	private String lineCode;
	
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
	
	@Column(name="USED", insertable = false, updatable = true)
	private int used;	
}
