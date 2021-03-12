package com.innomes.main.purchase.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "PUR110")
public class PUR110 implements Persistable<String>{

	@Id
	@GenericGenerator(
			name="seq_id",
			strategy = "com.innomes.main.generator.PurchaseOrderCodeGenerator"
			)
	@GeneratedValue(generator = "seq_id")
	@Column(name = "PO_NO")
	private String poNo;

	@Column(name = "COMP_ID")
	private String compId;

	@Column(name = "INCOME_LOC")
	private String incomeLoc;

	@Column(name = "DUE_DATE")
	private Date dueDate;

	@Column(name = "ORDER_USER")
	private String orderUser;

	@Column(name = "ORDER_DATE")
	private Date orderDate;

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
	
	@OneToMany(mappedBy = "pur110", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<PUR111> pur111 = new ArrayList<PUR111>();
	
	@Transient
	private boolean isNew = false;

	@Override
	public String getId() {
		return poNo;
	}
	
	@Override
	public boolean isNew() {
		return isNew;
	}
}
