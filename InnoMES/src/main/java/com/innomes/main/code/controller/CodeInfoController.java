package com.innomes.main.code.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.code.model.COD100;
import com.innomes.main.code.param.CodeInfoParam;
import com.innomes.main.code.service.CodeInfoService;
import com.innomes.main.exception.CProductSaveException;
import com.innomes.main.master.dto.MasterProductDTO;
import com.innomes.main.master.model.MST111;
import com.innomes.main.master.param.MasterItemParam;
import com.innomes.main.response.model.CommonResult;
import com.innomes.main.response.model.ListResult;
import com.innomes.main.response.service.ResponseService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CodeInfoController {
	
	@Autowired
	private ResponseService responseService;
	
	@Autowired
	private CodeInfoService codeInfoService;
	
	@ApiOperation(value = "코드 전체 조회", notes = "코드정보 전체를 반환합니다. (검색조건 필터링 가능)")
	@CrossOrigin
	@PostMapping("/master/code")
	public ListResult<COD100> findAll(@RequestBody(required = false) CodeInfoParam codeInfoParam,
			@PageableDefault(value = 1000) final Pageable pageable) {
		if(codeInfoParam == null) //파라메터가 없을경우
			codeInfoParam = new CodeInfoParam(); // 전체 조회
		
		return responseService.getListResult(COD100.class, codeInfoService.findAllLike(codeInfoParam, pageable));
	}
}
