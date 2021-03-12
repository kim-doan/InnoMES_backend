package com.innomes.main.purchase.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class PUR111PK implements Serializable {
	private String poNo;
	
	private Integer poSeq;
}
