package com.innomes.main.sales.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.innomes.main.repository.SAL100Repository;
import com.innomes.main.repository.SAL101Repository;
import com.innomes.main.sales.dto.SalesPlanPivotDTO;
import com.innomes.main.sales.dto.SalesPlanDTO;
import com.innomes.main.sales.dto.SalesPlanLogDTO;
import com.innomes.main.sales.model.SAL100;
import com.innomes.main.sales.model.SAL101;
import com.innomes.main.sales.param.SalesPlanParam;

@Service
@Transactional
public class SalesPlanService {
	
	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private int batchSize;
	
	@Autowired
	private SAL100Repository sal100Repository;
	
	@Autowired
	private SAL101Repository sal101Repository;
	
	//판매계획관리 조회
	public Page<SalesPlanPivotDTO> getSalesPlanList(SalesPlanParam salesPlanParam, Pageable pageable) {
		Page<SAL101> output = sal101Repository.getSalesPlan(salesPlanParam, pageable);
		
		List<SAL101> content = output.getContent();
		
		List<SalesPlanPivotDTO> dtoList = new ArrayList<SalesPlanPivotDTO>();
		
		for(int i=0;i<content.size();i++) {
			SalesPlanPivotDTO salesPlanDTO = SalesPlanPivotDTO.builder()
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
	
	//판매계획관리 조회(내역보기)
	public List<SalesPlanDTO> getSalesPlanLogList(SalesPlanParam salesPlanParam) {
		List<SAL100> content = sal100Repository.getSalesPlanLog(salesPlanParam);
		
		List<SalesPlanDTO> dtoList = new ArrayList<SalesPlanDTO>();
		
		for(int i=0;i<content.size();i++) {
			SalesPlanDTO salesPlanDTO = SalesPlanDTO.builder()
					.salPlanNo(content.get(i).getSalPlanNo())
					.planYear(content.get(i).getPlanYear())
					.planMonth(content.get(i).getPlanMonth())
					.compId(content.get(i).getCompId())
					.itemId(content.get(i).getItemId())
					.description(content.get(i).getDescription())
					.createUser(content.get(i).getCreateUser())
					.createTime(content.get(i).getCreateTime())
					.updateUser(content.get(i).getUpdateUser())
					.updateTime(content.get(i).getUpdateTime())
					.used(content.get(i).getUsed())
					.build();
			
			List<SalesPlanLogDTO> salesPlanLog = new ArrayList<SalesPlanLogDTO>();
			
			for(int j=0;j<content.get(i).getSal101().size();j++) {
				SalesPlanLogDTO salesPlanLogDTO = SalesPlanLogDTO.builder()
						.salPlanNo(content.get(i).getSal101().get(j).getSalPlanNo())
						.salPlanSeq(content.get(i).getSal101().get(j).getSalPlanSeq())
						.regType(content.get(i).getSal101().get(j).getRegType())
						.regUser(content.get(i).getSal101().get(j).getRegUser())
						.regTime(content.get(i).getSal101().get(j).getRegTime())
						.regCause(content.get(i).getSal101().get(j).getRegCause())
						.planQnt(content.get(i).getSal101().get(j).getPlanQnt())
						.build();
				
				salesPlanLog.add(salesPlanLogDTO);
			}
			
			salesPlanDTO.setSalesPlanLog(salesPlanLog);
			
			dtoList.add(salesPlanDTO);
		}
		
		return dtoList;
	}
	
	//판매계획관리 저장
	public boolean saveSalesPlan(SalesPlanParam[] salesPlanParam) {
		boolean success = true;
		
		List<SAL100> sal100List = new ArrayList<SAL100>();
		List<SAL101> sal101List = new ArrayList<SAL101>();
		
		try {
			for(int i=0;i<salesPlanParam.length;i++) {
				Double sumPlanQnt = null;
				
				if(salesPlanParam[i].getSalPlanNo() != null)
					sumPlanQnt = sal101Repository.sumPlanQnt(salesPlanParam[i].getSalPlanNo());
				
				SAL100 sal100 = SAL100.builder()
						.salPlanNo(salesPlanParam[i].getSalPlanNo())
						.planYear(salesPlanParam[i].getPlanYear())
						.planMonth(salesPlanParam[i].getPlanMonth())
						.compId(salesPlanParam[i].getCompId())
						.itemId(salesPlanParam[i].getItemId())
						.description(salesPlanParam[i].getDescription())
						.createUser(salesPlanParam[i].getCreateUser())
						.createTime(new Date())
						.updateUser(salesPlanParam[i].getUpdateUser())
						.updateTime(new Date())
						.used(salesPlanParam[i].getUsed())
						.build();
				
				
				SAL101 sal101 = SAL101.builder()
						.salPlanNo(salesPlanParam[i].getSalPlanNo())
						.regType(salesPlanParam[i].getRegType())
						.regUser(salesPlanParam[i].getRegUser())
						.regTime(new Date())
						.regCause(salesPlanParam[i].getRegCause())
						.planQnt(sumPlanQnt)
						.isNew(true)
						.build();
				
				if(sumPlanQnt != null) {
					sumPlanQnt = salesPlanParam[i].getPlanQnt() - sumPlanQnt; // 계획량 - (내역 계획량 SUM)
					
					sal101.setSalPlanSeq(sal101Repository.maxPlanQnt(sal101.getSalPlanNo()) + 1); // 해당 계획의 maxSeq + 1
					sal101.setPlanQnt(sumPlanQnt);
				} else {
					sumPlanQnt = salesPlanParam[i].getPlanQnt(); // 계획량
					
					sal101.setSalPlanSeq(1);
					sal101.setPlanQnt(sumPlanQnt);
				}
				
				sal101.setSal100(sal100);
//				sal101List.add(sal101);
//				sal100.setSal101(sal101List);
				
//				sal100List.add(sal100);
				sal101List.add(sal101);
				
				if ((i + 1) % batchSize == 0 && i > 0) {
//					sal100Repository.saveAll(sal100List);
//					sal100Repository.flush();
//					sal100List.clear();
					sal101Repository.saveAll(sal101List);
					sal101Repository.flush();
					sal101List.clear();
				}
			}
//			sal100Repository.saveAll(sal100List);
//			sal100Repository.flush();
//			sal100List.clear();
			sal101Repository.saveAll(sal101List);
			sal101Repository.flush();
			sal101List.clear();
		} catch (Exception e) {
			success = false;
		}
		
		return success;
	}
}
