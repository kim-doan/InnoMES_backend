package com.innomes.main.code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.code.model.COD400;
import com.innomes.main.code.param.COD400Param;
import com.innomes.main.code.service.FailCodeService;
import com.innomes.main.response.model.PageListResult;
import com.innomes.main.response.service.ResponseService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FailCodeController {
	
	@Autowired
	private ResponseService responseService;
	
	@Autowired
	private FailCodeService failCodeService;
	
	@ApiOperation(value = "불량유형 전체 조회", notes = "불량유형정보 전체를 반환합니다. (검색조건 필터링 가능)")
	@CrossOrigin
	@PostMapping("/master/failCode")
	public PageListResult<COD400> findAll(@RequestBody(required = false) COD400Param cod400Param,
			@PageableDefault(value = 1000) final Pageable pageable) {
		if(cod400Param == null) //파라메터가 없을경우
			cod400Param = new COD400Param(); // 전체 조회
		
		return responseService.getPageListResult(COD400.class, failCodeService.findAllLike(cod400Param, pageable));
	}
}
