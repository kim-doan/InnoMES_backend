package com.innomes.main.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.master.dto.MasterPriceItemDTO;
import com.innomes.main.master.param.MasterPriceItemParam;
import com.innomes.main.master.service.MasterPriceItemService;
import com.innomes.main.response.model.PageListResult;
import com.innomes.main.response.service.ResponseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value = "MasterPriceItemController")
public class MasterPriceItemController {
	@Autowired
	private ResponseService responseService;
	
	@Autowired
	private MasterPriceItemService masterPriceItemService;
	
	@ApiOperation(value = "판매단가정보 조회", notes = "판매단가 정보를 반환 합니다")
	@CrossOrigin
	@PostMapping("/master/priceItem/sell")
	public PageListResult<MasterPriceItemDTO> getSellPrice(@RequestBody(required = false) MasterPriceItemParam masterPriceItemParam, final Pageable pageable){
		if(masterPriceItemParam == null)
			masterPriceItemParam = new MasterPriceItemParam();
		return responseService.getPageListResult(MasterPriceItemDTO.class, masterPriceItemService.getPriceItem(masterPriceItemParam, pageable));
	}
	
}
