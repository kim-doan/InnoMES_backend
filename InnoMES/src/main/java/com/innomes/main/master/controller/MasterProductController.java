package com.innomes.main.master.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.master.dto.MasterItemDTO;
import com.innomes.main.master.model.MST110;
import com.innomes.main.master.model.MST111;
import com.innomes.main.master.param.MasterItemParam;
import com.innomes.main.master.service.MasterProductService;
import com.innomes.main.response.model.ListResult;
import com.innomes.main.response.service.ResponseService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MasterProductController {

	@Autowired
	private ResponseService responseService;
	
	@Autowired
	private MasterProductService masterProductService;
	
	@CrossOrigin
	@PostMapping("/master/item/products")
	public ListResult<MST111> findAll(@Valid @RequestBody(required = false) MasterItemParam masterProductParam) {
		return responseService.getListResult(MST111.class, masterProductService.findAll(masterProductParam));
	}
}
