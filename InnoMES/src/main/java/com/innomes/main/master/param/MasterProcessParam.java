package com.innomes.main.master.param;

import java.util.Date;

import com.innomes.main.master.model.MST120;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MasterProcessParam {
	/* search */
	
	
	private String procCode;
	
	private String procName;
	
	private String procType;
	
	private String inOutType;
	
	private Integer prdtionYN;
	
	private Integer supplyYN;
	
	private Integer workOrderYN;
	
	private Integer defaultYN;
	
	private Integer inspFinishedYN;
	
	private Integer asYN;
	
	private String description;
	
	private Integer used;
	
	private String createUser;
	
	private Date createTime;
	
	private String updateUser;
	
	private Date updateTime;
	/* save */
	//private MST120 mst120;
}
