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

import com.innomes.main.code.model.COD200;
import com.innomes.main.code.param.BadCodeParam;
import com.innomes.main.code.param.StopCodeParam;
import com.innomes.main.code.service.BadCodeService;
import com.innomes.main.exception.CStopCodeInfoSaveException;
import com.innomes.main.pool.service.CodePoolService;
import com.innomes.main.response.model.CommonResult;
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

	@Autowired
	private CodePoolService codePoolService;

	@ApiOperation(value = "불량유형 전체 조회", notes = "불량유형정보 전체를 반환합니다. (검색조건 필터링 가능)")
	@CrossOrigin
	@PostMapping("/master/badCode")
	public PageListResult<COD200> findAll(@RequestBody(required = false) BadCodeParam badCodeParam, @PageableDefault(value = 1000) final Pageable pageable) {
		if (badCodeParam == null) //파라메터가 없을경우
			badCodeParam = new BadCodeParam(); // 전체 조회

		return responseService.getPageListResult(COD200.class, badCodeService.findAllLike(badCodeParam, pageable));
	}

	//insert & update
	@ApiOperation(value = "불량유형 정보 저장", notes = "불량유형정보를 저장합니다.")
	@CrossOrigin
	@PostMapping("/master/badCode/save")
	public CommonResult saveBadCode(@Valid @RequestBody(required = true) List<BadCodeParam> badCodeParamList) {
		boolean result = badCodeService.saveBadCode(badCodeParamList);

		if (result == true) {
			codePoolService.SetCOD200List();
			return responseService.getSuccessResult();
		} else {
			throw new CStopCodeInfoSaveException();
		}
	}
}
