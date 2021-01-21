package com.innomes.main.master.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.innomes.main.master.dto.MasterManufactureItemDTO;
import com.innomes.main.master.dto.MasterManufactureRoutingDTO;
import com.innomes.main.master.model.MST111;
import com.innomes.main.master.model.MST200;
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
	
	
	//제품 - 제조공정정보 - 메인그리드
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
	
	public List<MST200> getManufactureProcess() {
		return mst200Repository.findAll();
	}
}
