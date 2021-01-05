package com.innomes.main.master.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.common.model.PageRequest;
import com.innomes.main.master.dto.MasterItemDTO;
import com.innomes.main.master.dto.MasterProductDTO;
import com.innomes.main.master.model.MST110;
import com.innomes.main.master.model.MST111;
import com.innomes.main.master.param.MasterItemParam;
import com.innomes.main.master.service.MasterProductService;
import com.innomes.main.response.model.ListResult;
import com.innomes.main.response.service.ResponseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(value = "MasterProductController")
public class MasterProductController {

	@Autowired
	private ResponseService responseService;
	
	@Autowired
	private MasterProductService masterProductService;
	
	@ApiOperation(value = "제품 전체 조회", notes = "제품정보 전체를 반환합니다. (검색조건 필터링 가능)")
	@CrossOrigin
	@PostMapping("/master/item/products")
	public ListResult<MasterProductDTO> findAll(@RequestBody(required = false) MasterItemParam masterProductParam, final Pageable pageable) {
		
		return responseService.getListResult(MasterProductDTO.class, masterProductService.findAll(masterProductParam, pageable));
	}
}
