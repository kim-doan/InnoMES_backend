package com.innomes.main.master.param;

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
public class MasterUserParam {
	private String userNo;
	
	private String userName;
	
	private String userId;
	
	private String userPassword;
	
	private int initialPwYN;
	
	private String departmentCode;
	
	private String gradeCode;
	
	private String userType;
	
	private Date recruteDate;
	
	private Date resignDate;
	
	private String teamCode;
	
	private String shift;
	
	private Integer leaderYN;
	
	private String description;
	
	private String createUser;
	
	private Date createTime;
	
	private String updateUser;
	
	private Date updateTime;
	
	private int used;
}
