	package com.innomes.main.master.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
import com.innomes.main.master.model.MST210PK;
import com.innomes.main.master.model.MST220;
import com.innomes.main.master.param.MasterBomParam;
import com.innomes.main.master.param.MasterManufactureProcessParam;
import com.innomes.main.master.param.MasterProductParam;
import com.innomes.main.pool.service.MasterPoolService;
import com.innomes.main.repository.MST111Repository;
import com.innomes.main.repository.MST120Repository;
import com.innomes.main.repository.MST200Repository;
import com.innomes.main.repository.MST210Repository;
import com.innomes.main.repository.MST220Repository;

@Service
@Transactional
public class MasterManufactureProcessService {
	
	@Autowired
	private MST111Repository mst111Repository;
	
	@Autowired
	private MST200Repository mst200Repository;
	
	@Autowired
	private MST210Repository mst210Repository;
	
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
		
		for(MST111 mst111 : content) {
			MasterManufactureItemDTO manufactureItemDTO = MasterManufactureItemDTO.builder()
					.prdtId(mst111.getPrdtId())
					.itemCode(mst111.getMst110().getItemCode())
					.itemName(mst111.getMst110().getItemName())
					.prdtType(mst111.getPrdtType())
					.prdtCtg(mst111.getPrdtCtg())
					.prdtGroup(mst111.getPrdtGroup())
					.attMatType(mst111.getAttMatType())
					.attStdType(mst111.getAttStdType())
					.attDiaType(mst111.getAttDiaType())
					.heatSpec(mst111.getHeatSpec())
					.surfaceSpec(mst111.getSurfaceSpec())
					.coatingSpec(mst111.getCoatingSpec())
					.build();
			
			//라우트 목록
			List<MasterManufactureRoutingDTO> routeList = new ArrayList<MasterManufactureRoutingDTO>();
			//BOM 목록
			List<MasterBomDTO> bomList = new ArrayList<MasterBomDTO>();
			
			//라우트가 있을 경우 해당 품목 BOM 조회
			if(mst111.getMst210().size() > 0) {
				bomList = masterBomService.getBomList(MasterBomParam.builder()
						.prdtId(mst111.getPrdtId())
						.build());
			}
			
			for(MST210 mst210 : mst111.getMst210()) {
				if(mst210.getUsed() == 1) {
					MasterManufactureRoutingDTO routeProcess = MasterManufactureRoutingDTO.builder()
							.prdtId(mst210.getPrdtId())
							.routingRev(mst210.getRoutingRev())
							.procCode(mst210.getProcCode())
							.procName(masterPoolService.getMST120(mst210.getProcCode()).getProcName())
							.passYN(mst210.getRoutingSeq() <= 0 ? true : false)
							.inOutType(masterPoolService.getMST120(mst210.getProcCode()).getInOutType())
							.routingSeq(mst210.getRoutingSeq())
							.procSeq(mst210.getProcSeq())
							.inQnt(mst210.getInQnt())
							.outQnt(mst210.getOutQnt())
							.qntUnit(mst210.getQntUnit())
							.leadTime(mst210.getLeadTime())
							.settingTime(mst210.getSettingTime())
							.unitWeight(mst210.getUnitWeight())
							.locCode(mst210.getLocCode())
							.build();
					
					//BOM 목록 중 라우팅 공정 필터링 
					List<MasterBomDTO> procBomList = bomList.stream().filter(c -> c.getProcCode().equals(mst210.getProcCode()))
					.collect(Collectors.toList());
					routeProcess.setBomList(procBomList);
					
					routeList.add(routeProcess);
				}
			}
			
			//라우팅 정렬
			List<MasterManufactureRoutingDTO> sortRouteList = new ArrayList<MasterManufactureRoutingDTO>();
			
			routeList.stream().sorted(Comparator.comparing(MasterManufactureRoutingDTO::getProcSeq))
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
				
			for (MST210 mst210 : content.get().getMst210()) {
				MasterManufactureRoutingDTO routeProcess = MasterManufactureRoutingDTO.builder()
						.prdtId(mst210.getPrdtId())
						.routingRev(mst210.getRoutingRev())
						.procCode(mst210.getProcCode())
						.procName(masterPoolService.getMST120(mst210.getProcCode()).getProcName())
						.inOutType(masterPoolService.getMST120(mst210.getProcCode()).getInOutType())
						.routingSeq(mst210.getRoutingSeq())
						.procSeq(mst210.getProcSeq())
						.inQnt(mst210.getInQnt())
						.outQnt(mst210.getOutQnt())
						.qntUnit(mst210.getQntUnit())
						.leadTime(mst210.getLeadTime())
						.settingTime(mst210.getSettingTime())
						.unitWeight(mst210.getUnitWeight())
						.locCode(mst210.getLocCode())
						.build();
				
				List<MasterBomDTO> bomList = masterBomService.getBomList(MasterBomParam.builder()
						.prdtId(mst210.getPrdtId())
						.procCode(mst210.getProcCode())
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
	
	//제조공정정보 저장
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
			Set<MST210> mst210List = new HashSet<MST210>();
			
			mst210Repository.delManufactureRoute(manufactureProcessParam.getPrdtId(), 0);
			
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
						.used(1)
						.isNew(true)
						.build();
				
				mst210List.add(mst210);
				
				List<MST220> mst220List = new ArrayList<MST220>();
				
				/* 투입소재 등록 */
				for(int j=0;j<manufactureProcessParam.getRouteList().get(i).getBomList().size();j++) {
					MST220 mst220 = MST220.builder()
							.prdtId(manufactureProcessParam.getPrdtId())
							.procCode(manufactureProcessParam.getRouteList().get(i).getProcCode())
							.bomSeq(manufactureProcessParam.getRouteList().get(i).getBomList().get(j).getBomSeq())
							.swapSeq(manufactureProcessParam.getRouteList().get(i).getBomList().get(j).getSwapSeq())
							.itemId(manufactureProcessParam.getRouteList().get(i).getBomList().get(j).getItemId())
							.inQnt(manufactureProcessParam.getRouteList().get(i).getBomList().get(j).getInQnt())
							.inUnit(manufactureProcessParam.getRouteList().get(i).getBomList().get(j).getInUnit())
							.description(manufactureProcessParam.getRouteList().get(i).getBomList().get(j).getDescription())
							.createUser(manufactureProcessParam.getRouteList().get(i).getBomList().get(j).getCreateUser())
							.createTime(new Date())
							.updateUser(manufactureProcessParam.getRouteList().get(i).getBomList().get(j).getUpdateUser())
							.updateTime(new Date())
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
