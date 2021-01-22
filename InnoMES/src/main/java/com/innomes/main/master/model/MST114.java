package com.innomes.main.master.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "MST114")
@Builder
public class MST114 {
	@Id
	@Column(name="PART_ID")
	private String partId;
	
	@Column(name="PART_TYPE")
	private String partType;
	
	@Column(name="PART_CTG")
	private String partCtg;
	
	@Column(name="PART_GROUP")
	private String partGroup;
	
	@Column(name="PART_SHIFT_CYCLE")
	private double partShiftCycle;
	
	@Column(name="CYCLE_UNIT")
	private String cycleUnit;
	
	@Column(name="LOT_YN")
	private int lotYN;
	
	@OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "PART_ID")
    private MST110 mst110;
}
