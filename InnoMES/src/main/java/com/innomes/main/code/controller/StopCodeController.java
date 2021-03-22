package com.innomes.main.code.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.code.model.COD300;
import com.innomes.main.code.param.COD300Param;
import com.innomes.main.code.service.StopCodeService;
import com.innomes.main.exception.CStopCodeInfoSaveException;
import com.innomes.main.pool.service.CodePoolService;
import com.innomes.main.response.model.CommonResult;
import com.innomes.main.response.model.PageListResult;
import com.innomes.main.response.service.ResponseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(value = "StopCodeController")
public class StopCodeController {

	@Autowired
	private ResponseService responseService;

	@Autowired
	private StopCodeService stopCodeService;

	@Autowired
	private CodePoolService codePoolService;

	@ApiOperation(value = "비가동유형 전체 조회", notes = "코드정보 전체를 반환합니다. (검색조건 필터링 가능)")
	@CrossOrigin
	@PostMapping("/master/stopCode")
	public PageListResult<COD300> findAll(@RequestBody(required = false) COD300Param cod300Param, @PageableDefault(value = 1000) final Pageable pageable) {
		if (cod300Param == null) //파라메터가 없을경우
			cod300Param = new COD300Param(); // 전체 조회

		return responseService.getPageListResult(COD300.class, stopCodeService.findAllLike(cod300Param, pageable));
	}

	//insert & update
	@ApiOperation(value = "비가동유형 정보 저장", notes = "비가동유형정보를 저장합니다.")
	@CrossOrigin
	@PostMapping("/master/stopCode/save")
	public CommonResult saveCodeInfo(@Valid @RequestBody(required = true) List<COD300Param> cod300ParamList) {
		boolean result = stopCodeService.saveStopCode(cod300ParamList);

		if (result == true) {
			codePoolService.SetCOD300List();
			return responseService.getSuccessResult();
		} else {
			throw new CStopCodeInfoSaveException();
		}
	}
}
