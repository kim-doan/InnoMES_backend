package com.innomes.main.master.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.innomes.main.master.param.MasterManufactureProcessParam;
import com.innomes.main.master.param.MasterManufactureRoutingParam;
import com.innomes.main.master.param.MasterManufactureProcessParam.MasterManufactureProcessParamBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MasterManufactureProcessDTO {
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
