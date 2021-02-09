package com.innomes.main.master.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.exception.CAuthenticationEntryPointException;
import com.innomes.main.exception.CUserDuplicationException;
import com.innomes.main.exception.CUserNotFoundException;
import com.innomes.main.exception.CUserRegisterFailException;
import com.innomes.main.exception.CUserSaveException;
import com.innomes.main.jwt.JwtTokenProvider;
import com.innomes.main.master.dto.MasterProfileDTO;
import com.innomes.main.master.dto.MasterUserDTO;
import com.innomes.main.master.model.MST140;
import com.innomes.main.master.param.AuthenticationRequest;
import com.innomes.main.master.param.MasterUserParam;
import com.innomes.main.master.service.MasterUserService;
import com.innomes.main.pool.service.MasterPoolService;
import com.innomes.main.response.model.CommonResult;
import com.innomes.main.response.model.ListResult;
import com.innomes.main.response.model.LoginResult;
import com.innomes.main.response.model.PageListResult;
import com.innomes.main.response.model.SingleResult;
import com.innomes.main.response.service.ResponseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MasterUserController {
	
	@Autowired
	private MasterUserService masterUserService;
	
	@Autowired
	private ResponseService responseService;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private MasterPoolService masterPoolService;
	
	//로그인 토큰 확인
	@CrossOrigin
	@GetMapping("/master/userInfo/validToken")
	public SingleResult<MasterProfileDTO> getLoginSession(@RequestHeader(value="X-AUTH-TOKEN") String token) {
		if(token != null && jwtTokenProvider.validateToken(token)) { // 토큰 만료 확인	
			MST140 user = masterUserService.findByUserNo(jwtTokenProvider.getUserPk(token)).orElseThrow(CUserNotFoundException::new);
			
			MasterProfileDTO profile = MasterProfileDTO.builder()
					.userNo(user.getUserNo())
					.userName(user.getUserName())
					.userId(user.getSys800().getUserId())
					.tempPw(Boolean.parseBoolean(jwtTokenProvider.getTempPw(token)))
					.roles(user.getSys800().getRoles())
					.build();
			
			return responseService.getSingleResult(profile);
		} else {
			throw new CAuthenticationEntryPointException();
		}
	}
	
	//사용자 정보 전체 조회
	@CrossOrigin
	@PostMapping("/master/userInfo/users")
	public PageListResult<MasterUserDTO> getUserAll(@Valid @RequestBody(required = false) MasterUserParam masterUserParam, final Pageable pageable) {
		if(masterUserParam == null) //파라메터가 없을경우
			masterUserParam = new MasterUserParam(); // 전체 조회
		
		Page<MasterUserDTO> userDTO = masterUserService.findAllLike(masterUserParam, pageable);
		
		return responseService.getPageListResult(MasterUserDTO.class, userDTO);
	}
	
	// 로그인
	@CrossOrigin
	@PostMapping("/master/userInfo/login")
	public LoginResult login(@Valid @RequestBody(required = true) AuthenticationRequest user) {
		String token = masterUserService.login(user);
		
		if(token != null) {
			return responseService.getLoginResult(token);
		} else {
			throw new CUserNotFoundException();
		}
	}
	
	//회원 가입 (유저 정보(MST140) + 계정 정보(SYS800, 810))
	@CrossOrigin
	@PostMapping("/master/userInfo/register")
	public CommonResult insertUser(@Valid @RequestBody(required = true) MasterUserParam[] masterUserParam) {
		boolean success = masterUserService.insertUser(masterUserParam);
		
		if(success == true) { // 성공
			return responseService.getSuccessResult();
		} else {
			throw new CUserRegisterFailException();
		}
	}
	
	//회원 가입 아이디체크
	@CrossOrigin
	@PostMapping("/master/userInfo/idCheck")
	public CommonResult idCheck(@RequestBody @Valid MasterUserParam[] masterUserParam) throws Exception {
		boolean success = masterUserService.idCheck(masterUserParam[0].getUserId());
		
		if(success == true) { // 성공
			return responseService.getSuccessResult();
		} else {
			throw new CUserDuplicationException();
		}
	}
	
	//사용자 수정(사용자 정보(MST140))
	@CrossOrigin
	@PostMapping("/master/userInfo/update")
	public CommonResult updateUser(@Valid @RequestBody(required = true) MasterUserParam[] masterUserParam) {
		boolean success = masterUserService.updateUser(masterUserParam);
		
		if(success == true) {
//			dataPoolService.SetMST140List();
			return responseService.getSuccessResult();
		} else {
			throw new CUserSaveException();
		}
	}
	
	//계정 수정(계정 정보(SYS800, 810))
	@CrossOrigin
	@PostMapping("/system/account/update")
	public CommonResult updateAccount(@Valid @RequestBody(required = true) MasterUserParam[] masterUserParam) {
		boolean success = masterUserService.updateAccount(masterUserParam);
		
		if(success == true) {
			return responseService.getSuccessResult();
		} else {
			throw new CUserSaveException();
		}
	}
	
	//API 권한 변경
	@CrossOrigin
	@PostMapping("/master/userInfo/rolesModify")
	public CommonResult RolesModify(@Valid @RequestBody(required = true) HashMap<String, Object> param) {
		boolean result = masterUserService.updateRoles(param);
		
		if(result) {			
			return responseService.getSuccessResult();
		} else {
			throw new CUserSaveException();
		}
	}
	
	//유저정보 삭제 used =1
	@CrossOrigin
	@PostMapping("/master/userInfo/delete")
	public CommonResult deleteProcessInfo(@Valid @RequestBody MasterUserParam[] masterUserParam) {
		boolean result = masterUserService.deleteUserInfo(masterUserParam);
		
		if(result == true) {	
			return responseService.getSuccessResult();
		} else {
			throw new CUserNotFoundException();
		}
	}
}
