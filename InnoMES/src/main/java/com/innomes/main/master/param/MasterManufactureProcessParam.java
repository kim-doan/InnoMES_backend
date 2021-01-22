package com.innomes.main.master.param;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MasterManufactureProcessParam {
	private String prdtId;

	private Integer routingRev;
	
	private String prdtStatus;
	
	private String revUser;
	
	private Date revDate;
	
	private String revCause;
	
	private String description;
	
	private String createUser;
	
	private Date createTime;
	
	private String updateUser;
	
	private Date updateTime;
	
	private int used;
	
	List<MasterManufactureRoutingParam> routingParam = new ArrayList<MasterManufactureRoutingParam>();
}
