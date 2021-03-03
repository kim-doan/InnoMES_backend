package com.innomes.main.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.master.dto.MasterPriceItemDTO;
import com.innomes.main.master.dto.MasterProductDTO;
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
	
	
	@ApiOperation(value = "품목리스트 (판매단가)", notes = "품목리스트를 반환합니다.")
	@CrossOrigin
	@PostMapping("/master/priceItem/items")
	public PageListResult<MasterProductDTO> getPriceItem(@RequestBody(required = false) MasterPriceItemParam masterPriceItemParam, final Pageable pageable){
		if(masterPriceItemParam == null)
			masterPriceItemParam = new MasterPriceItemParam();

		return responseService.getPageListResult(MasterProductDTO.class, masterPriceItemService.getSellPriceItem(masterPriceItemParam, pageable));
	}
	
	@ApiOperation(value = "품목별 단가정보", notes = "품목별 단가정보 리스트를 반환합니다.")
	@CrossOrigin
	@PostMapping("/master/priceItem/sellPrice")
	public PageListResult<MasterPriceItemDTO> getSellPrice(@RequestBody(required = false) MasterPriceItemParam masterPriceItemParam, final Pageable pageable){
		if(masterPriceItemParam == null)
			masterPriceItemParam = new MasterPriceItemParam();
		return responseService.getPageListResult(MasterPriceItemDTO.class, masterPriceItemService.getSellPriceInfo(masterPriceItemParam, pageable));
	}
	
}
