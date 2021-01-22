package com.innomes.main.master.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.innomes.main.master.model.MST110.MST110Builder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "MST120")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
@Builder
public class MST120 {
	@Id
	@Column(name = "PROC_CODE")
	private String procCode;
	
	@Column(name = "PROC_NAME")
	private String procName;
	
	@Column(name = "PROC_TYPE")
	private String procType;
	
	@Column(name = "IN_OUT_TYPE")
	private int inOutType;
	
	@Column(name = "PRDTION_YN")
	private int prdtionYN;
	
	@Column(name = "SUPLLY_YN")
	private int supplyYN;
	
	@Column(name = "WORK_ORDER_YN")
	private int workOrderYN;
	
	@Column(name = "DEFAULT_YN")
	private int defaultYN;
	
	@Column(name = "INSP_FINISHED_YN")
	private Integer inspFinishedYN;
	
	@Column(name = "AS_YN")
	private Integer asYN;
	
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
