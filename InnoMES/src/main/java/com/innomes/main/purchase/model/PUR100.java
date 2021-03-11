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
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "PUR100")
@Builder
public class PUR100 implements Persistable<String> {

	@Id
	@GenericGenerator(
			name="seq_id",
			strategy = "com.innomes.main.generator.PurchaseRequestCodeGenerator"
			)
	@GeneratedValue(generator = "seq_id")
	@Column(name = "REQ_NO")
	private String reqNo;

	@Column(name = "REQ_TYPE")
	private String reqType;

	@Column(name = "REQ_USER")
	private String reqUser;

	@Column(name = "REQ_DEPARTMENT")
	private String reqDepartment;

	@Column(name = "REQ_DATE")
	private Date reqDate;

	@Column(name = "APPR_USER")
	private String apprUser;

	@Column(name = "APPR_DATE")
	private Date apprDate;

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
	
	@OneToMany(mappedBy = "pur100", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<PUR101> pur101 = new ArrayList<>();
	
	@Transient
	private boolean isNew = false;

	@Override
	public String getId() {
		return reqNo;
	}

	@Override
	public boolean isNew() {
		return isNew;
	}
}
