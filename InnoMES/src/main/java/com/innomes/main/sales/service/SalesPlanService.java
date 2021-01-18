package com.innomes.main.sales.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.innomes.main.repository.SAL100Repository;
import com.innomes.main.repository.SAL101Repository;
import com.innomes.main.sales.dto.SalesPlanDTO;
import com.innomes.main.sales.model.SAL100;
import com.innomes.main.sales.model.SAL101;
import com.innomes.main.sales.param.SalesPlanParam;

@Service
@Transactional
public class SalesPlanService {
	
	@Autowired
	private SAL100Repository sal100Repository;
	
	@Autowired
	private SAL101Repository sal101Repository;
	
	//판매계획관리 조회
	public Page<SalesPlanDTO> getSalesPlanList(SalesPlanParam salesPlanParam, Pageable pageable) {
		Page<SAL101> output = sal101Repository.getSalesPlan(salesPlanParam, pageable);
		
		List<SAL101> content = output.getContent();
		
		List<SalesPlanDTO> dtoList = new ArrayList<SalesPlanDTO>();
		
		for(int i=0;i<content.size();i++) {
			SalesPlanDTO salesPlanDTO = SalesPlanDTO.builder()
					.salPlanNo(content.get(i).getSalPlanNo())
					.planYear(content.get(i).getSal100().getPlanYear())
					.planMonth(content.get(i).getSal100().getPlanMonth())
					.compId(content.get(i).getSal100().getCompId())
					.itemId(content.get(i).getSal100().getItemId())
					.description(content.get(i).getSal100().getDescription())
					.createUser(content.get(i).getSal100().getCreateUser())
					.createTime(content.get(i).getSal100().getCreateTime())
					.updateUser(content.get(i).getSal100().getUpdateUser())
					.updateTime(content.get(i).getSal100().getUpdateTime())
					.used(content.get(i).getSal100().getUsed())
					.salPlanSeq(content.get(i).getSalPlanSeq())
					.regType(content.get(i).getRegType())
					.regUser(content.get(i).getRegUser())
					.regTime(content.get(i).getRegTime())
					.regCause(content.get(i).getRegCause())
					.planQnt(sal101Repository.sumPlanQnt(content.get(i).getSalPlanNo()))
					.build();
			
			dtoList.add(salesPlanDTO);
		}
		
		return new PageImpl<>(dtoList, pageable, output.getTotalElements());
	}
	
	//판매계획관리 조회 (피벗)
	
	//판매게획관리 저장
}
