package com.innomes.main.sales.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SAL100")
@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class SAL100 implements Persistable<Integer>{
	@Id
	@Column(name = "SAL_PLAN_NO")
//	@SequenceGenerator(name = "SAL_PLAN_NO", sequenceName = "SEQ_SAL_PLAN_NO", initialValue = 1, allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SAL_PLAN_NO")
	private Integer salPlanNo;
	
	@Column(name = "PLAN_YEAR")
	private Integer planYear;
	
	@Column(name = "PLAN_MONTH")
	private Integer planMonth;
	
	@Column(name = "COMP_ID")
	private String compId;
	
	@Column(name = "ITEM_ID")
	private String itemId;
	
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
	
	@Column(name = "USED", insertable = false, updatable = true)
	private int used;
	
	@OneToMany(mappedBy = "sal100", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SAL101> sal101 = new ArrayList<SAL101>();
	
	@Transient
	private boolean isNew = false;

	@Override
	public Integer getId() {
		return salPlanNo;
	}
	
	@Override
	public boolean isNew() {
		return isNew;
	}
}
