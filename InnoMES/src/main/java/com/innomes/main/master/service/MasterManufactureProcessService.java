	package com.innomes.main.master.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.innomes.main.master.dto.MasterBomDTO;
import com.innomes.main.master.dto.MasterManufactureItemDTO;
import com.innomes.main.master.dto.MasterManufactureProcessDTO;
import com.innomes.main.master.dto.MasterManufactureRoutingDTO;
import com.innomes.main.master.model.MST111;
import com.innomes.main.master.model.MST200;
import com.innomes.main.master.model.MST200PK;
import com.innomes.main.master.model.MST210;
import com.innomes.main.master.model.MST220;
import com.innomes.main.master.param.MasterBomParam;
import com.innomes.main.master.param.MasterManufactureProcessParam;
import com.innomes.main.master.param.MasterProductParam;
import com.innomes.main.pool.service.MasterPoolService;
import com.innomes.main.repository.MST111Repository;
import com.innomes.main.repository.MST120Repository;
import com.innomes.main.repository.MST200Repository;
import com.innomes.main.repository.MST220Repository;

@Service
@Transactional
public class MasterManufactureProcessService {
	
	@Autowired
	private MST111Repository mst111Repository;
	
	@Autowired
	private MST200Repository mst200Repository;
	
	@Autowired
	private MST220Repository mst220Repository;
	
	@Autowired
	private MasterPoolService masterPoolService;
	
	@Autowired
	private MasterBomService masterBomService;
	
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
						.procName(masterPoolService.getMST120(content.get(i).getMst210().get(j).getProcCode()).getProcName())
						.inOutType(masterPoolService.getMST120(content.get(i).getMst210().get(j).getProcCode()).getInOutType())
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
				
				List<MasterBomDTO> bomList = masterBomService.getBomList(MasterBomParam.builder()
							.prdtId(content.get(i).getMst210().get(j).getPrdtId())
							.procCode(content.get(i).getMst210().get(j).getProcCode())
							.build());
				
				routeProcess.setBomList(bomList);
				routeList.add(routeProcess);
			}
			
			//라우팅 정렬
			List<MasterManufactureRoutingDTO> sortRouteList = new ArrayList<MasterManufactureRoutingDTO>();
			
			routeList.stream().sorted(Comparator.comparing(MasterManufactureRoutingDTO::getRoutingSeq))
			.forEach(x -> sortRouteList.add(x));
			
			manufactureItemDTO.setRouteList(sortRouteList);
			
			dtoList.add(manufactureItemDTO);
		}
		
		return new PageImpl<>(dtoList, pageable, output.getTotalElements());
	}
	
	// 제조공정정보 - 라우팅 리스트 -> 다이얼로그
	public MasterManufactureProcessDTO getManufactureProcess(MasterManufactureProcessParam masterManufactureProcessParam) {
		Optional<MST200> content = mst200Repository.getManufactureProcess(masterManufactureProcessParam);
		
			MasterManufactureProcessDTO manufactureProcessDTO = MasterManufactureProcessDTO.builder()
					.prdtId(content.get().getPrdtId())
					.itemCode(content.get().getMst111().getMst110().getItemCode())
					.itemName(content.get().getMst111().getMst110().getItemName())
					.prdtType(content.get().getMst111().getPrdtType())
					.prdtCtg(content.get().getMst111().getPrdtCtg())
					.prdtGroup(content.get().getMst111().getPrdtGroup())
					.attMatType(content.get().getMst111().getAttMatType())
					.attStdType(content.get().getMst111().getAttStdType())
					.attDiaType(content.get().getMst111().getAttDiaType())
					.heatSpec(content.get().getMst111().getHeatSpec())
					.surfaceSpec(content.get().getMst111().getSurfaceSpec())
					.coatingSpec(content.get().getMst111().getCoatingSpec())
					.routingRev(content.get().getRoutingRev())
					.prdtStatus(content.get().getPrdtStatus())
					.revUser(content.get().getRevUser())
					.revDate(content.get().getRevDate())
					.revCause(content.get().getRevCause())
					.description(content.get().getDescription())
					.createUser(content.get().getCreateUser())
					.createTime(content.get().getCreateTime())
					.updateUser(content.get().getUpdateUser())
					.updateTime(content.get().getUpdateTime())
					.used(content.get().getUsed())
					.build();
			
			List<MasterManufactureRoutingDTO> routeList = new ArrayList<MasterManufactureRoutingDTO>();
			
			for(int j=0;j<content.get().getMst210().size();j++) {
				MasterManufactureRoutingDTO routeProcess = MasterManufactureRoutingDTO.builder()
						.prdtId(content.get().getMst210().get(j).getPrdtId())
						.routingRev(content.get().getMst210().get(j).getRoutingRev())
						.procCode(content.get().getMst210().get(j).getProcCode())
						.procName(masterPoolService.getMST120(content.get().getMst210().get(j).getProcCode()).getProcName())
						.inOutType(masterPoolService.getMST120(content.get().getMst210().get(j).getProcCode()).getInOutType())
						.routingSeq(content.get().getMst210().get(j).getRoutingSeq())
						.procSeq(content.get().getMst210().get(j).getProcSeq())
						.inQnt(content.get().getMst210().get(j).getInQnt())
						.outQnt(content.get().getMst210().get(j).getOutQnt())
						.qntUnit(content.get().getMst210().get(j).getQntUnit())
						.leadTime(content.get().getMst210().get(j).getLeadTime())
						.settingTime(content.get().getMst210().get(j).getSettingTime())
						.unitWeight(content.get().getMst210().get(j).getUnitWeight())
						.locCode(content.get().getMst210().get(j).getLocCode())
						.build();
				
				List<MasterBomDTO> bomList = masterBomService.getBomList(MasterBomParam.builder()
						.prdtId(content.get().getMst210().get(j).getPrdtId())
						.procCode(content.get().getMst210().get(j).getProcCode())
						.build());
			
				routeProcess.setBomList(bomList);
				routeList.add(routeProcess);
			}
			
			//라우팅 정렬
			List<MasterManufactureRoutingDTO> sortRouteList = new ArrayList<MasterManufactureRoutingDTO>();
			
			routeList.stream().sorted(Comparator.comparing(MasterManufactureRoutingDTO::getRoutingSeq))
			.forEach(x -> sortRouteList.add(x));
			
			manufactureProcessDTO.setRouteList(sortRouteList);
			
			return manufactureProcessDTO;
	}
	
	public boolean saveManufactureProcess(MasterManufactureProcessParam manufactureProcessParam) {
		boolean success = true;
		
		try {
			/* 이전 제조공정정보 비활성화 */
			Integer maxRev = mst200Repository.getMaxRoutingRev(manufactureProcessParam.getPrdtId());
			
			if(maxRev == null) { 
				maxRev = 0;
			} else {
				MST200PK delMst200 = MST200PK.builder()
						.prdtId(manufactureProcessParam.getPrdtId())
						.routingRev(maxRev)
						.build();
				
				mst200Repository.delManufactureProcess(delMst200, 0);
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
			
			/* 신규 라우팅 (무조건 INSERT) */
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
						.isNew(true)
						.build();
				
				mst210List.add(mst210);
				
				List<MST220> mst220List = new ArrayList<MST220>();
				
				/* 투입소재 등록 */
				for(int j=0;j<manufactureProcessParam.getRouteList().get(i).getBomList().size();j++) {
					MST220 mst220 = MST220.builder()
							.prdtId(manufactureProcessParam.getRouteList().get(i).getPrdtId())
							.procCode(manufactureProcessParam.getRouteList().get(i).getProcCode())
							.bomSeq(manufactureProcessParam.getRouteList().get(i).getBomList().get(j).getBomSeq())
							.swapSeq(manufactureProcessParam.getRouteList().get(i).getBomList().get(j).getSwapSeq())
							.itemId(manufactureProcessParam.getRouteList().get(i).getBomList().get(j).getItemId())
							.inQnt(manufactureProcessParam.getRouteList().get(i).getBomList().get(j).getInQnt())
							.inUnit(manufactureProcessParam.getRouteList().get(i).getBomList().get(j).getInUnit())
							.description(manufactureProcessParam.getRouteList().get(i).getBomList().get(j).getDescription())
							.createUser(manufactureProcessParam.getRouteList().get(i).getBomList().get(j).getCreateUser())
							.createTime(manufactureProcessParam.getRouteList().get(i).getBomList().get(j).getCreateTime())
							.updateUser(manufactureProcessParam.getRouteList().get(i).getBomList().get(j).getUpdateUser())
							.updateTime(manufactureProcessParam.getRouteList().get(i).getBomList().get(j).getUpdateTime())
							.used(manufactureProcessParam.getRouteList().get(i).getBomList().get(j).getUsed())
							.build();
					
					mst220List.add(mst220);
				}
				
				mst220Repository.saveAll(mst220List);
				mst220Repository.flush();
				mst220List.clear();
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
