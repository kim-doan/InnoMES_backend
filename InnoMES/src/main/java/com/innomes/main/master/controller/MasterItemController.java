package com.innomes.main.master.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.exception.CProductSaveException;
import com.innomes.main.master.model.MST111;
import com.innomes.main.master.param.MasterItemParam;
import com.innomes.main.master.service.MasterItemService;
import com.innomes.main.response.model.CommonResult;
import com.innomes.main.response.service.ResponseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MasterItemController {
	
	@Autowired
	private MasterItemService masterItemService;
	
	@Autowired
	private ResponseService responseService;
	
	@CrossOrigin
	@PostMapping("/master/item/products/save")
	public CommonResult savePrdtItemInfo(@Valid @RequestBody MST111[] mst111) {
		boolean result = masterItemService.saveItemInfo(mst111);
		
		if(result == true) {
			return responseService.getSuccessResult();
		} else {
			throw new CProductSaveException();
		}
	}
}
