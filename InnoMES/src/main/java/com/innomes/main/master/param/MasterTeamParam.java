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
public class MasterTeamParam {

	private String teamCode;

	private String teamName;

	private String ftrCode;

	private String procType;

	private String lineCode;

	private String description;

	private String createUser;

	private Date createTime;

	private String updateUser;

	private Date updateTime;

	private int used;

}
