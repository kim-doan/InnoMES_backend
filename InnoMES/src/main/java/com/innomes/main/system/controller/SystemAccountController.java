package com.innomes.main.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.master.dto.MasterLocationDTO;
import com.innomes.main.master.dto.MasterToolDTO;
import com.innomes.main.master.param.MasterLocationParam;
import com.innomes.main.response.model.PageListResult;
import com.innomes.main.response.model.SingleResult;
import com.innomes.main.response.service.ResponseService;
import com.innomes.main.system.dto.SystemAccountDTO;
import com.innomes.main.system.service.AccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(value = "SystemAccountController")
public class SystemAccountController {

	@Autowired
	private ResponseService responseService;
	
	@Autowired
	private AccountService accountService;
	
	@ApiOperation(value = "계정 조회", notes = "계정정보를 반환합니다.")
	@CrossOrigin
	@GetMapping("/system/accountInfo")
	public SingleResult<SystemAccountDTO> getAccount(@RequestParam("userNo") String userNo){
		
		SystemAccountDTO accountDTO = accountService.getAccount(userNo);

		return responseService.getSingleResult(accountDTO);
		
	}
}
