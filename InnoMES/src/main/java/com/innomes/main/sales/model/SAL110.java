package com.innomes.main.sales.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SAL110")
@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class SAL110 implements Persistable<Integer>{
	@Id
	@Column(name = "ORD_ID")
	private Integer ordId;
	
	@Column(name = "COMP_ID")
	private String compId;
	
	@Column(name = "ITEM_ID")
	private String itemId;
	
	@Column(name = "DUE_DATE")
	private Date dueDate;
	
	@Column(name = "ORD_TYPE")
	private  String ordType;
	
	@Column(name = "ORD_REG_USER")
	private String ordRegUser;
	
	@Column(name = "ORD_REG_TIME")
	private Date ordRegTime;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "CREATE_USER", insertable = true, updatable = false)
	private String createUser;
	
	@Column(name = "CREATE_TIME", insertable = true, updatable = false)
	private Date createTime;
	
	@Column(name = "UPDATE_USER", insertable = false, updatable = true)
	private String updateUser;
	
	@Column(name = "UPDATE_TIME", insertable = false, updatable = false)
	private Date updateTime;
	
	@Column(name = "USED", insertable = false, updatable = true)
	private int used;
	
	@OneToMany(mappedBy = "sal110", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SAL110> SAL110 = new ArrayList<SAL110>(); 
	
	@Transient
	private boolean isNew = false;
	
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return ordId;
	}

	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		return isNew;
	}
}
