package com.innomes.main.master.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MasterTeamDTO {
	
	private String teamCode;
	
	private String teamName;
	
	private String ftrCode;
	
	private String procType;
	
	private String lineCode;
	
	private String description;
}
