package com.innomes.main.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.master.dto.MasterTeamDTO;
import com.innomes.main.master.param.MasterTeamParam;
import com.innomes.main.master.service.MasterTeamService;
import com.innomes.main.response.model.PageListResult;
import com.innomes.main.response.service.ResponseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(value = "MasterTeamController")
public class MasterTeamController {
	@Autowired
	private ResponseService responseService;
	
	@Autowired
	private MasterTeamService masterTeamService;
	
	@ApiOperation(value = "작업조 조회", notes = "작업조 정보를 반환합니다")
	@CrossOrigin
	@PostMapping("/master/team/getTeam")
	public PageListResult<MasterTeamDTO> getTeamList(@RequestBody(required = false) MasterTeamParam masterTeamParam, final Pageable pageable){
		if(masterTeamParam == null)
			masterTeamParam = new MasterTeamParam();
		
		return responseService.getPageListResult(MasterTeamDTO.class, masterTeamService.getTeam(masterTeamParam, pageable));
	}
}
