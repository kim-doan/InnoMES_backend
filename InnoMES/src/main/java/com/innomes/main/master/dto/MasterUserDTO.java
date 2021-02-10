package com.innomes.main.master.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class MasterUserDTO {
	private String userNo;
	
	private String userId;
	
	private Boolean tempPw;
	
	private String userName;
	
	private String departmentCode;
	
	private String gradeCode;
	
	private String userType;
	
	private Date recruteDate;
	
	private Date resignDate;
	
	private String teamCode;
	
	private String shift;
	
	private int leaderYN;
	
	private String description;
	
	private Integer used;
}
