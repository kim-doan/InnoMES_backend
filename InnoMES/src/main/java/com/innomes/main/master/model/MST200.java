package com.innomes.main.master.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Persistable;
import org.springframework.util.ObjectUtils;

import com.innomes.main.master.param.MasterManufactureProcessParam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@IdClass(MST200PK.class)
@Table(name = "MST200")
@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MST200 implements Persistable<MST200PK>{
	@Id
	@Column(name = "PRDT_ID", insertable = false, updatable = false)
	private String prdtId;
	
	@Id
	@Column(name = "ROUTING_REV", insertable = false, updatable = false)
	private Integer routingRev;
	
	@Column(name = "PRDT_STATUS")
	private String prdtStatus;
	
	@Column(name = "REV_USER")
	private String revUser;
	
	@Column(name = "REV_DATE")
	private Date revDate;
	
	@Column(name = "REV_CAUSE")
	private String revCause;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "CREATE_USER", insertable = true, updatable = false)
	private String createUser;
	
	@Column(name = "CREATE_TIME", insertable = true, updatable = false)
	private Date createTime;
	
	@Column(name = "UPDATE_USER", insertable = false, updatable = true)
	private String updateUser;
	
	@Column(name = "UPDATE_TIME", insertable = false, updatable = true)
	private Date updateTime;
	
	@Column(name = "USED")
	private int used;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
	@JoinColumns(value = {
			@JoinColumn(name = "PRDT_ID", referencedColumnName = "PRDT_ID", insertable = false, updatable = false)
	})
	private MST111 mst111;
	
	@OneToMany(mappedBy = "mst200", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<MST210> mst210 = new HashSet<MST210>();
	
	@Transient
	private boolean isNew = false;

	@Override
	public MST200PK getId() {
		return MST200PK.builder()
				.prdtId(prdtId)
				.routingRev(routingRev)
				.build();
	}

	@Override
	public boolean isNew() {
		return isNew;
	}
}
