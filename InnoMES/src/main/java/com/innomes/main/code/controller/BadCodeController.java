package com.innomes.main.code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.code.model.COD200;
import com.innomes.main.code.param.COD200Param;
import com.innomes.main.code.service.BadCodeService;
import com.innomes.main.response.model.PageListResult;
import com.innomes.main.response.service.ResponseService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BadCodeController {
	
	@Autowired
	private ResponseService responseService;
	
	@Autowired
	private BadCodeService badCodeService;
	
	@ApiOperation(value = "불량유형 전체 조회", notes = "불량유형정보 전체를 반환합니다. (검색조건 필터링 가능)")
	@CrossOrigin
	@PostMapping("/master/badCode")
	public PageListResult<COD200> findAll(@RequestBody(required = false) COD200Param cod200Param,
			@PageableDefault(value = 1000) final Pageable pageable) {
		if(cod200Param == null) //파라메터가 없을경우
			cod200Param = new COD200Param(); // 전체 조회
		
		return responseService.getPageListResult(COD200.class, badCodeService.findAllLike(cod200Param, pageable));
	}
}
