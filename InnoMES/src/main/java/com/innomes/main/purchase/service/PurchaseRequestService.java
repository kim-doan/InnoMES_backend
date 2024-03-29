package com.innomes.main.purchase.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.innomes.main.pool.service.MasterPoolService;
import com.innomes.main.purchase.dto.PurchaseRequestDTO;
import com.innomes.main.purchase.dto.PurchaseRequestDetailDTO;
import com.innomes.main.purchase.model.PUR100;
import com.innomes.main.purchase.model.PUR101;
import com.innomes.main.purchase.param.PurchaseRequestParam;
import com.innomes.main.repository.PUR100Repository;
import com.innomes.main.repository.PUR101Repository;
import com.innomes.main.util.service.UtilService;

@Service
@Transactional
public class PurchaseRequestService {
	
	@Autowired
	UtilService util;
	
	@Autowired
	PUR100Repository pur100Repository;
	
	@Autowired
	PUR101Repository pur101Repository;
	
	@Autowired
	MasterPoolService masterPoolService;
	
	//제품 구매 요청 조회
	public Page<PurchaseRequestDTO> getProductPurchaseRequest(PurchaseRequestParam purchaseRequestParam, Pageable pageable) {
		return getPurchaseRequest(purchaseRequestParam, pageable, "PCS001001");
	}
	//자제 구매 요청 조회
	public Page<PurchaseRequestDTO> getMaterialPurchaseRequest(PurchaseRequestParam purchaseRequestParam, Pageable pageable) {
		return getPurchaseRequest(purchaseRequestParam, pageable, "PCS001002");
	}
	//공구 구매 요청 조회
	public Page<PurchaseRequestDTO> getToolPurchaseRequest(PurchaseRequestParam purchaseRequestParam, Pageable pageable) {
		return getPurchaseRequest(purchaseRequestParam, pageable, "PCS001003");
	}
	//예비품 구매 요청 조회
	public Page<PurchaseRequestDTO> getSparePurchaseRequest(PurchaseRequestParam purchaseRequestParam, Pageable pageable) {
		return getPurchaseRequest(purchaseRequestParam, pageable, "PCS001004");
	}
	
	public boolean setPurchaseRequest(List<PurchaseRequestParam> purchaseRequestParams) {
		try {
			List<PUR100> pur100List = util.convertModelAndDto(purchaseRequestParams, PUR100.class);
			for(PUR100 pur100 : pur100List) {
				pur100.setCreateTime(new Date());
				pur100.setUpdateTime(new Date());
			}
		} catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return true;
	}
	
	private Page<PurchaseRequestDTO> getPurchaseRequest(PurchaseRequestParam purchaseRequestParam, Pageable pageable, String priceType) {
		purchaseRequestParam.setReqType(priceType);
		Page<PUR100> output = pur100Repository.findAllLike(purchaseRequestParam, pageable);
		//List<PurchaseRequestDTO> dtoList = util.convertModelAndDto(output.getContent(), PurchaseRequestDTO.class);
		
		List<PurchaseRequestDTO> dtoList = new ArrayList<PurchaseRequestDTO>();
		for(PUR100 pur100 : output.getContent()) {
			PurchaseRequestDTO mainDTO = PurchaseRequestDTO.builder()					
										.reqNo(pur100.getReqNo())
										.reqType(pur100.getReqType())
										.reqUser(pur100.getReqUser())
										.reqDepartment(pur100.getReqDepartment())
										.reqDate(pur100.getReqDate())
										.apprUser(pur100.getApprUser())
										.apprDate(pur100.getApprDate())
										.description(pur100.getDescription())
										.build();
			
			List<PurchaseRequestDetailDTO> detailList = new ArrayList<PurchaseRequestDetailDTO>();
			for(PUR101 pur101 : pur100.getPur101()) {
				
			PurchaseRequestDetailDTO detailDTO = PurchaseRequestDetailDTO.builder()	//공통부분
											.reqNo(pur101.getReqNo())
											.reqSeq(pur101.getReqSeq())
											.reqCode(pur101.getReqCode())
											.reqQnt(pur101.getReqQnt())
											.reqUnit(pur101.getReqUnit())
											.reqComp(pur101.getReqComp())
											.compName(masterPoolService.getMST150Map().get(pur101.getReqComp()).getCompName())
											.reqDue(pur101.getReqDue())
											.reqLoc(pur101.getReqLoc())
											//.locName(적재위치)
											.orderYN(pur101.getOrderYN())
											.description(pur101.getDescription())
											.build();
			
			//품목유형별 컬럼
			switch (priceType) {
			case "PCS001001":
				detailDTO.setItemCode(masterPoolService.getMST111(pur101.getReqCode()).getItemCode());
				detailDTO.setItemName(masterPoolService.getMST111Map().get(pur101.getReqCode()).getItemName());
				break;
			case "PCS001002":
				detailDTO.setItemCode(masterPoolService.getMST112(pur101.getReqCode()).getItemCode());
				detailDTO.setItemName(masterPoolService.getMST112Map().get(pur101.getReqCode()).getItemName());
				detailDTO.setAttMatType(masterPoolService.getMST112Map().get(pur101.getReqCode()).getAttMatType());
				detailDTO.setAttDiaType(masterPoolService.getMST112Map().get(pur101.getReqCode()).getAttDiaType());
				detailDTO.setAttStdType(masterPoolService.getMST112Map().get(pur101.getReqCode()).getAttStdType());
				detailDTO.setMatProc(masterPoolService.getMST112Map().get(pur101.getReqCode()).getMatProc());
				break;
			case "PCS001003":
				detailDTO.setItemCode(masterPoolService.getMST113(pur101.getReqCode()).getItemCode());
				detailDTO.setItemName(masterPoolService.getMST113Map().get(pur101.getReqCode()).getItemName());
				detailDTO.setToolProc(masterPoolService.getMST113Map().get(pur101.getReqCode()).getToolProc());
				detailDTO.setPrdtionProc(masterPoolService.getMST113Map().get(pur101.getReqCode()).getPrdtionProc());
				detailDTO.setRepItemId(masterPoolService.getMST113Map().get(pur101.getReqCode()).getRepItemId());
				detailDTO.setRepItemCode(masterPoolService.getMST111Map().get(detailDTO.getRepItemId()).getItemCode());
				detailDTO.setRepItemName(masterPoolService.getMST111Map().get(detailDTO.getRepItemId()).getItemName());
				break;
			case "PCS001004":
				detailDTO.setItemCode(masterPoolService.getMST114(pur101.getReqCode()).getItemCode());
				detailDTO.setItemName(masterPoolService.getMST114Map().get(pur101.getReqCode()).getItemName());
				break;
			}
			
			detailList.add(detailDTO);
			}
			mainDTO.setPur101(detailList);
			dtoList.add(mainDTO);
		}
		return new PageImpl<>(dtoList, pageable, output.getTotalElements());
	}
}
