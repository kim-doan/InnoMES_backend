package com.innomes.main.purchase.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseRequestDetailDTO {

	private String reqNo;

	private int reqSeq;

	private String reqCode;

	private double reqQnt;

	private String reqUnit;

	private String reqComp;

	private Date reqDue;

	private String reqLoc;

	private int orderYN;

	private String description;

}
