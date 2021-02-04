package com.innomes.main.master.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;

import lombok.RequiredArgsConstructor;

import lombok.Setter;

import lombok.Getter;

@Getter @Setter
@RequiredArgsConstructor
@Builder
public class MasterProfileDTO {
	private String userNo;
	
	private String userId;
	
	private String userName;
	
	private boolean tempPw;
	
	private List<String> roles = new ArrayList<>();

	public MasterProfileDTO(String userNo, String userId, String userName, boolean tempPw, List<String> roles) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userName = userName;
		this.tempPw = tempPw;
		this.roles = roles;
	}
}
