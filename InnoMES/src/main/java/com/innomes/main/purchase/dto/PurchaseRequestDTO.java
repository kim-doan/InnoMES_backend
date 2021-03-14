package com.innomes.main.purchase.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseRequestDTO {

	private String reqNo;

	private String reqType;

	private String reqUser;

	private String reqDepartment;

	private Date reqDate;

	private String apprUser;

	private Date apprDate;

	private String description;
	
	private List<PurchaseRequestDetailDTO> pur101 = new ArrayList<PurchaseRequestDetailDTO>();
}
