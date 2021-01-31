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
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.innomes.main.exception.CSalesOrderSaveException;
import com.innomes.main.mapper.AutoKeyMapper;
import com.innomes.main.repository.SAL110Repository;
import com.innomes.main.repository.SAL111Repository;
import com.innomes.main.sales.dto.SalesOrderDTO;
import com.innomes.main.sales.dto.SalesOrderLogDTO;
import com.innomes.main.sales.dto.SalesOrderMainDTO;
import com.innomes.main.sales.model.SAL110;
import com.innomes.main.sales.model.SAL111;
import com.innomes.main.sales.param.SalesOrderParam;

@Service
@Transactional
public class SalesOrderService {
	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private int batchSize;
	
	@Autowired
	private SAL110Repository sal110Repository;
	
	@Autowired
	private SAL111Repository sal111Repository;
	
	@Autowired
	private AutoKeyMapper autoKeyMapper;
	
	//수주관리 조회
	public Page<SalesOrderMainDTO> getSalesOrderList(SalesOrderParam salesOrderParam, Pageable pageable) {
		Page<SAL111> output = sal111Repository.getSalesOrder(salesOrderParam, pageable);
		
		List<SAL111> content = output.getContent();
		
		List<SalesOrderMainDTO> dtoList = new ArrayList<SalesOrderMainDTO>();
		
		for(int i=0;i<content.size();i++) {
			SalesOrderMainDTO salesOrderDTO = SalesOrderMainDTO.builder()
					.ordId(content.get(i).getOrdId())
					.compId(content.get(i).getSal110().getCompId())
					.itemId(content.get(i).getSal110().getItemId())
					.dueDate(content.get(i).getSal110().getDueDate())
					.ordType(content.get(i).getSal110().getOrdType())
					.ordRegType(content.get(i).getOrdRegType())
					.ordRegUser(content.get(i).getSal110().getOrdRegUser())
					.ordRegTime(content.get(i).getSal110().getOrdRegTime())
					.description(content.get(i).getSal110().getDescription())
					.createUser(content.get(i).getSal110().getCreateUser())
					.createTime(content.get(i).getSal110().getCreateTime())
					.updateUser(content.get(i).getSal110().getUpdateUser())
					.updateTime(content.get(i).getSal110().getUpdateTime())
					.used(content.get(i).getSal110().getUsed())
					.ordSeq(content.get(i).getOrdSeq())
					.ordQnt(sal111Repository.sumOrdQnt(content.get(i).getOrdId()))
					.build();
					
			dtoList.add(salesOrderDTO);
		}
		
		return new PageImpl<>(dtoList, pageable, output.getTotalElements());
	}
	
	//수주관리 조회(내역보기)
	public List<SalesOrderDTO> getSalesOrderLogList(SalesOrderParam salesOrderParam) {
		List<SAL110> content = sal110Repository.getSalesOrderLog(salesOrderParam);
		
		List<SalesOrderDTO> dtoList = new ArrayList<SalesOrderDTO>();
		
		for(int i=0;i<content.size();i++) {
			SalesOrderDTO salesOrderDTO = SalesOrderDTO.builder()
					.ordId(content.get(i).getOrdId())
					.compId(content.get(i).getCompId())
					.itemId(content.get(i).getItemId())
					.dueDate(content.get(i).getDueDate())
					.ordType(content.get(i).getOrdType())
					.ordRegUser(content.get(i).getOrdRegUser())
					.ordRegTime(content.get(i).getOrdRegTime())
					.description(content.get(i).getDescription())
					.createUser(content.get(i).getCreateUser())
					.createTime(content.get(i).getCreateTime())
					.updateUser(content.get(i).getUpdateUser())
					.updateTime(content.get(i).getUpdateTime())
					.used(content.get(i).getUsed())
					.build();
			
			List<SalesOrderLogDTO> salesOrderLogDTO = new ArrayList<SalesOrderLogDTO>();
			
			for(int j=0;j<content.get(i).getSal111().size();j++) {
				SalesOrderLogDTO orderLogDTO = SalesOrderLogDTO.builder()
						.ordId(content.get(i).getSal111().get(j).getOrdId())
						.ordSeq(content.get(i).getSal111().get(j).getOrdSeq())
						.ordQnt(content.get(i).getSal111().get(j).getOrdQnt())
						.ordRegType(content.get(i).getSal111().get(j).getOrdRegType())
						.ordRegUser(content.get(i).getSal111().get(j).getOrdRegUser())
						.ordRegTime(content.get(i).getSal111().get(j).getOrdRegTime())
						.description(content.get(i).getSal111().get(j).getDescription())
						.used(content.get(i).getSal111().get(j).getUsed())
						.build();
				
				salesOrderLogDTO.add(orderLogDTO);
			}
			
			salesOrderDTO.setSalesOrderLog(salesOrderLogDTO);
			
			dtoList.add(salesOrderDTO);
		}
		
		return dtoList;
	}
	
	//수주관리 저장
	public boolean saveSalesOrder(SalesOrderParam[] salesOrderParam) {
		boolean success = true;
		
		List<SAL110> sal110List = new ArrayList<SAL110>();
		List<SAL111> sal111List = new ArrayList<SAL111>();
		
		try {
			for(int i=0;i<salesOrderParam.length;i++) {
				Double sumOrdQnt = null;
				Integer ordId = null;
				boolean isNew = true;
				
				if(salesOrderParam[i].getOrdId() != null) {
					//UPDATE
					ordId = salesOrderParam[i].getOrdId();
					sumOrdQnt = sal111Repository.sumOrdQnt(ordId);
					isNew = false;
				} else {
					//INSERT
					ordId = autoKeyMapper.seqOrdId();
					isNew = true;
				}
				
				SAL110 sal110 = SAL110.builder()
						.ordId(ordId)
						.compId(salesOrderParam[i].getCompId())
						.itemId(salesOrderParam[i].getItemId())
						.dueDate(salesOrderParam[i].getDueDate())
						.ordType(salesOrderParam[i].getOrdType())
						.ordRegUser(salesOrderParam[i].getOrdRegUser())
						.ordRegTime(new Date())
						.description(salesOrderParam[i].getDescription())
						.createUser(salesOrderParam[i].getCreateUser())
						.createTime(new Date())
						.updateUser(salesOrderParam[i].getUpdateUser())
						.updateTime(new Date())
						.used(salesOrderParam[i].getUsed())
						.isNew(isNew)
						.build();
				
				SAL111 sal111 = SAL111.builder()
						.ordId(ordId)
						.ordSeq(salesOrderParam[i].getOrdSeq())
						.ordQnt(salesOrderParam[i].getOrdQnt())
						.ordRegType(salesOrderParam[i].getOrdRegType())
						.ordRegUser(salesOrderParam[i].getOrdRegUser())
						.ordRegTime(new Date())
						.description(salesOrderParam[i].getDescription())
						.used(salesOrderParam[i].getUsed())
						.isNew(true)
						.build();
				
				if(sumOrdQnt != null) {
					sumOrdQnt = salesOrderParam[i].getOrdQnt() - sumOrdQnt;
					sal111.setOrdSeq(sal111Repository.maxOrdSeq(sal111.getOrdId()) + 1);
					sal111.setOrdQnt(sumOrdQnt);
				} else {
					sumOrdQnt = salesOrderParam[i].getOrdQnt();
					sal111.setOrdSeq(1);
					sal111.setOrdQnt(sumOrdQnt);
				}
				
				sal111List.add(sal111);
				sal110.setSal111(sal111List);
				
				sal110List.add(sal110);
				
				if ((i + 1) % batchSize == 0 && i > 0) {
					sal110Repository.saveAll(sal110List);
					sal110Repository.flush();
					sal110List.clear();
				}
			}
			sal110Repository.saveAll(sal110List);
			sal110Repository.flush();
			sal110List.clear();
		} catch (CSalesOrderSaveException e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 롤백
			throw new CSalesOrderSaveException();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 롤백
			success = false;
		}
		
		return success;
	}
}
