package com.innomes.main.repository.custom;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.innomes.main.master.model.MST200;
import com.innomes.main.master.model.MST200PK;
import com.innomes.main.master.param.MasterManufactureProcessParam;

public interface MST200RepositoryCustom {
	//제조공정정보, 라우팅 조회
	Optional<MST200> getManufactureProcess(MasterManufactureProcessParam masterManufactureProcessParam);
	
	//제조공정 라우팅 리비젼 MAX값 조회
	Integer getMaxRoutingRev(String prdtId);
	
	//제조공정정보 이력보기
	List<MST200> getManufactureProcessRevLog(MasterManufactureProcessParam masterManufactureProcessParam);
	
	//제조공정정보 비활성화 / 활성화
	Long delManufactureProcess(MST200PK pk, int used);
	
}
