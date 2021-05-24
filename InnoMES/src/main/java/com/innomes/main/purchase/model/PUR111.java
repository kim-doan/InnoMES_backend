package com.innomes.main.purchase.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;

import com.innomes.main.master.model.MST110;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@IdClass(PUR111PK.class)
@Data
@Table(name = "PUR111")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PUR111 implements Persistable<PUR111PK> {
 
	@Id
	@Column(name = "PO_NO")
	private String poNo;

	@Id
	@Column(name = "PO_SEQ")
	private Integer poSeq;

	@Column(name = "ORDER_ITEM")
	private String orderItem;

	@Column(name = "ORDER_QNT")
	private double orderQnt;

	@Column(name = "ORDER_UNIT")
	private String orderUnit;

	@Column(name = "DUE_DATE")
	private Date dueDate;

	@Column(name = "INCOME_LOC")
	private String incomeLoc;

	@Column(name = "REQ_NO")
	private String reqNo;

	@Column(name = "REQ_SEQ")
	private Integer reqSeq;

	@Column(name = "PRICE_STD")
	private double priceStd;

	@Column(name = "PRICE_LOG")
	private double priceLog;

	@Column(name = "MONEY_UNIT")
	private String moneyUnit;

	@Column(name = "TAX_RATE")
	private double taxRate;

	@Column(name = "AMOUNT")
	private double amount;

	@Column(name = "INCOME_YN")
	private int incomeYn;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "CREATE_USER")
	private String createUser;

	@Column(name = "CREATE_TIME")
	private Date createTime;

	@Column(name = "UPDATE_USER")
	private String updateUser;

	@Column(name = "UPDATE_TIME")
	private Date updateTime;

	@Column(name = "USED")
	private int used;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
	@JoinColumns(value = {
			@JoinColumn(name = "PO_NO", referencedColumnName = "PO_NO", insertable = false, updatable = false)
	})
	private PUR110 pur110;
	
	@OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "ORDER_ITEM")
    private MST110 mst110;
	
	@Transient
	private boolean isNew = false;

	@Override
	public PUR111PK getId() {
		return PUR111PK.builder()
				.poNo(poNo)
				.poSeq(poSeq)
				.build();
	}

	@Override
	public boolean isNew() {
		return isNew;
	}

}
