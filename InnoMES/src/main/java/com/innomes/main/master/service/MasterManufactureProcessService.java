package com.innomes.main.master.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.innomes.main.master.dto.MasterManufactureItemDTO;
import com.innomes.main.master.dto.MasterManufactureProcessDTO;
import com.innomes.main.master.dto.MasterManufactureRoutingDTO;
import com.innomes.main.master.model.MST111;
import com.innomes.main.master.model.MST200;
import com.innomes.main.master.model.MST210;
import com.innomes.main.master.model.MST220;
import com.innomes.main.master.param.MasterManufactureProcessParam;
import com.innomes.main.master.param.MasterProductParam;
import com.innomes.main.repository.MST111Repository;
import com.innomes.main.repository.MST200Repository;

@Service
@Transactional
public class MasterManufactureProcessService {
	
	@Autowired
	private MST111Repository mst111Repository;
	
	@Autowired
	private MST200Repository mst200Repository;
	
	
	//제품 - 제조공정정보 - 라우팅리스트 -> 메인그리드
	public Page<MasterManufactureItemDTO> getManufactureItem(MasterProductParam masterProductParam, Pageable pageable) {
		Page<MST111> output = mst111Repository.getManufactureItem(masterProductParam, pageable);
		
		List<MST111> content = output.getContent();
		
		List<MasterManufactureItemDTO> dtoList = new ArrayList<MasterManufactureItemDTO>();
		
		for(int i=0;i<content.size();i++) {
			MasterManufactureItemDTO manufactureItemDTO = MasterManufactureItemDTO.builder()
					.prdtId(content.get(i).getPrdtId())
					.itemCode(content.get(i).getMst110().getItemCode())
					.itemName(content.get(i).getMst110().getItemName())
					.prdtType(content.get(i).getPrdtType())
					.prdtCtg(content.get(i).getPrdtCtg())
					.prdtGroup(content.get(i).getPrdtGroup())
					.attMatType(content.get(i).getAttMatType())
					.attStdType(content.get(i).getAttStdType())
					.attDiaType(content.get(i).getAttDiaType())
					.heatSpec(content.get(i).getHeatSpec())
					.surfaceSpec(content.get(i).getSurfaceSpec())
					.coatingSpec(content.get(i).getCoatingSpec())
					.build();
			
			List<MasterManufactureRoutingDTO> routeList = new ArrayList<MasterManufactureRoutingDTO>();
			
			for(int j=0;j<content.get(i).getMst210().size();j++) {
				MasterManufactureRoutingDTO routeProcess = MasterManufactureRoutingDTO.builder()
						.prdtId(content.get(i).getMst210().get(j).getPrdtId())
						.routingRev(content.get(i).getMst210().get(j).getRoutingRev())
						.procCode(content.get(i).getMst210().get(j).getProcCode())
						.routingSeq(content.get(i).getMst210().get(j).getRoutingSeq())
						.procSeq(content.get(i).getMst210().get(j).getProcSeq())
						.inQnt(content.get(i).getMst210().get(j).getInQnt())
						.outQnt(content.get(i).getMst210().get(j).getOutQnt())
						.qntUnit(content.get(i).getMst210().get(j).getQntUnit())
						.leadTime(content.get(i).getMst210().get(j).getLeadTime())
						.settingTime(content.get(i).getMst210().get(j).getSettingTime())
						.unitWeight(content.get(i).getMst210().get(j).getUnitWeight())
						.locCode(content.get(i).getMst210().get(j).getLocCode())
						.build();
				
				routeList.add(routeProcess);
			}
			
			manufactureItemDTO.setRouteList(routeList);
			
			dtoList.add(manufactureItemDTO);
		}
		
		return new PageImpl<>(dtoList, pageable, output.getTotalElements());
	}
	
	// 제조공정정보 - 라우팅 리스트 -> 다이얼로그
	public List<MasterManufactureProcessDTO> getManufactureProcess(MasterManufactureProcessParam masterManufactureProcessParam) {
		List<MST200> content = mst200Repository.getManufactureProcess(masterManufactureProcessParam);
		
		List<MasterManufactureProcessDTO> dtoList = new ArrayList<MasterManufactureProcessDTO>();
		
		for(int i=0;i<content.size();i++) {
			MasterManufactureProcessDTO manufactureProcessDTO = MasterManufactureProcessDTO.builder()
					.prdtId(content.get(i).getPrdtId())
					.itemCode(content.get(i).getMst111().getMst110().getItemCode())
					.itemName(content.get(i).getMst111().getMst110().getItemName())
					.prdtType(content.get(i).getMst111().getPrdtType())
					.prdtCtg(content.get(i).getMst111().getPrdtCtg())
					.prdtGroup(content.get(i).getMst111().getPrdtGroup())
					.attMatType(content.get(i).getMst111().getAttMatType())
					.attStdType(content.get(i).getMst111().getAttStdType())
					.attDiaType(content.get(i).getMst111().getAttDiaType())
					.heatSpec(content.get(i).getMst111().getHeatSpec())
					.surfaceSpec(content.get(i).getMst111().getSurfaceSpec())
					.coatingSpec(content.get(i).getMst111().getCoatingSpec())
					.routingRev(content.get(i).getRoutingRev())
					.prdtStatus(content.get(i).getPrdtStatus())
					.revUser(content.get(i).getRevUser())
					.revDate(content.get(i).getRevDate())
					.revCause(content.get(i).getRevCause())
					.description(content.get(i).getDescription())
					.createUser(content.get(i).getCreateUser())
					.createTime(content.get(i).getCreateTime())
					.updateUser(content.get(i).getUpdateUser())
					.updateTime(content.get(i).getUpdateTime())
					.used(content.get(i).getUsed())
					.build();
			
			List<MasterManufactureRoutingDTO> routeList = new ArrayList<MasterManufactureRoutingDTO>();
			
			for(int j=0;j<content.get(i).getMst210().size();j++) {
				MasterManufactureRoutingDTO routeProcess = MasterManufactureRoutingDTO.builder()
						.prdtId(content.get(i).getMst210().get(j).getPrdtId())
						.routingRev(content.get(i).getMst210().get(j).getRoutingRev())
						.procCode(content.get(i).getMst210().get(j).getProcCode())
						.routingSeq(content.get(i).getMst210().get(j).getRoutingSeq())
						.procSeq(content.get(i).getMst210().get(j).getProcSeq())
						.inQnt(content.get(i).getMst210().get(j).getInQnt())
						.outQnt(content.get(i).getMst210().get(j).getOutQnt())
						.qntUnit(content.get(i).getMst210().get(j).getQntUnit())
						.leadTime(content.get(i).getMst210().get(j).getLeadTime())
						.settingTime(content.get(i).getMst210().get(j).getSettingTime())
						.unitWeight(content.get(i).getMst210().get(j).getUnitWeight())
						.locCode(content.get(i).getMst210().get(j).getLocCode())
						.build();
				
				routeList.add(routeProcess);
			}
			
			manufactureProcessDTO.setRouteList(routeList);
			
			dtoList.add(manufactureProcessDTO);
		}
		
		return dtoList;
	}
	
	public boolean saveManufactureProcess(MasterManufactureProcessParam manufactureProcessParam) {
		boolean success = true;
		
		try {
			/* 이전 제조공정정보 비활성화 */
			Integer maxRev = mst200Repository.getMaxRoutingRev(manufactureProcessParam.getPrdtId());
			
			if(maxRev == null) { 
				maxRev = 0;
			} else {
				MST200 delMst200 = new MST200();
				
//				delMst200.updateInfo(MasterManufactureProcessParam.builder()
//						.prdtId(manufactureProcessParam.getPrdtId())
//						.routingRev(maxRev)
//						.used(0)
//						.build());
				
				mst200Repository.save(delMst200);
				mst200Repository.flush();
			}
			
			/* 신규 제조공정정보 */
			maxRev++; // 리비전 1 증가
			MST200 mst200 = MST200.builder()
					.prdtId(manufactureProcessParam.getPrdtId())
					.routingRev(maxRev)
					.prdtStatus(manufactureProcessParam.getPrdtStatus())
					.revUser(manufactureProcessParam.getRevUser())
					.revDate(new Date())
					.revCause(manufactureProcessParam.getRevCause())
					.description(manufactureProcessParam.getDescription())
					.createUser(manufactureProcessParam.getCreateUser())
					.createTime(new Date())
					.updateUser(manufactureProcessParam.getUpdateUser())
					.updateTime(new Date())
					.used(manufactureProcessParam.getUsed())
					.build();
			
			/* 신규 라우팅 */
			List<MST210> mst210List = new ArrayList<MST210>();
			
			for(int i=0;i<manufactureProcessParam.getRouteList().size();i++) {
				MST210 mst210 = MST210.builder()
						.prdtId(manufactureProcessParam.getPrdtId())
						.routingRev(maxRev)
						.procCode(manufactureProcessParam.getRouteList().get(i).getProcCode())
						.routingSeq(manufactureProcessParam.getRouteList().get(i).getRoutingSeq())
						.procSeq(manufactureProcessParam.getRouteList().get(i).getProcSeq())
						.inQnt(manufactureProcessParam.getRouteList().get(i).getInQnt())
						.outQnt(manufactureProcessParam.getRouteList().get(i).getOutQnt())
						.qntUnit(manufactureProcessParam.getRouteList().get(i).getQntUnit())
						.leadTime(manufactureProcessParam.getRouteList().get(i).getLeadTime())
						.settingTime(manufactureProcessParam.getRouteList().get(i).getSettingTime())
						.unitWeight(manufactureProcessParam.getRouteList().get(i).getUnitWeight())
						.locCode(manufactureProcessParam.getRouteList().get(i).getLocCode())
						.build();
				
				mst210List.add(mst210);
			}
			
			mst200.setMst210(mst210List);
			
			mst200Repository.save(mst200);
			mst200Repository.flush();
				
		} catch (Exception e) {
			success = false;
		}
		return success;
	}
}
