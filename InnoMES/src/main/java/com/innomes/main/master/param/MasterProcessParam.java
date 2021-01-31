package com.innomes.main.master.param;

import com.innomes.main.master.model.MST120;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MasterProcessParam {
	/* search */
	private Integer defaultYN;
	
	private String procCode;
	
	private String procName;
	
	private String procType;
	/* save */
	private MST120 mst120;
}
