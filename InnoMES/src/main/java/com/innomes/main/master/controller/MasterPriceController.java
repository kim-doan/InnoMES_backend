package com.innomes.main.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innomes.main.master.dto.MasterPriceDTO;
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
@Api(value = "MasterPriceController")
public class MasterPriceController {
	@Autowired
	private ResponseService responseService;
	
	@Autowired
	private MasterPriceItemService masterPriceItemService;
	
	
	@ApiOperation(value = "판매단가 품목리스트", notes = "판매단가 유형의 품목리스트를 반환합니다.")
	@CrossOrigin
	@PostMapping("/master/price/sell/itemList")
	public PageListResult<MasterPriceItemDTO> getSellItemList(@RequestBody(required = false) MasterPriceItemParam masterPriceItemParam, final Pageable pageable){
		if(masterPriceItemParam == null)
			masterPriceItemParam = new MasterPriceItemParam();

		return responseService.getPageListResult(MasterProductDTO.class, masterPriceItemService.getSellItemList(masterPriceItemParam, pageable));
	}
	
	@ApiOperation(value = "품목별 판매단가 정보", notes = "품목별 판매단가 정보 리스트를 반환합니다.")
	@CrossOrigin
	@PostMapping("/master/price/sell/priceList")
	public PageListResult<MasterPriceDTO> getSellPriceList(@RequestBody(required = false) MasterPriceItemParam masterPriceItemParam, final Pageable pageable){
		if(masterPriceItemParam == null)
			masterPriceItemParam = new MasterPriceItemParam();
		return responseService.getPageListResult(MasterPriceItemDTO.class, masterPriceItemService.getSellPriceList(masterPriceItemParam, pageable));
	}
	
	@ApiOperation(value = "구매단가 품목리스트", notes = "구매단가 유형의 품목리스트를 반환합니다.")
	@CrossOrigin
	@PostMapping("/master/price/purchase/itemList")
	public PageListResult<MasterPriceItemDTO> getPurchaseItemList(@RequestBody(required = false) MasterPriceItemParam masterPriceItemParam, final Pageable pageable){
		if(masterPriceItemParam == null)
			masterPriceItemParam = new MasterPriceItemParam();

		return responseService.getPageListResult(MasterProductDTO.class, masterPriceItemService.getPurchaseItemList(masterPriceItemParam, pageable));
	}
	
	@ApiOperation(value = "품목별 구매단가 정보", notes = "품목별 구매단가 정보 리스트를 반환합니다.")
	@CrossOrigin
	@PostMapping("/master/price/purchase/priceList")
	public PageListResult<MasterPriceDTO> getPurchasePriceList(@RequestBody(required = false) MasterPriceItemParam masterPriceItemParam, final Pageable pageable){
		if(masterPriceItemParam == null)
			masterPriceItemParam = new MasterPriceItemParam();
		return responseService.getPageListResult(MasterPriceItemDTO.class, masterPriceItemService.getPurchasePriceList(masterPriceItemParam, pageable));
	}
}
