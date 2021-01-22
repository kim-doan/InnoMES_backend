package com.innomes.main.repository.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.innomes.main.master.model.MST200;
import com.innomes.main.master.param.MasterManufactureProcessParam;

public interface MST200RepositoryCustom {
	//제조공정정보, 라우팅 조회
	List<MST200> getManufactureProcess(MasterManufactureProcessParam masterManufactureProcessParam);
	
	//제조공정 라우팅 리비젼 MAX값 조회
	Integer getMaxRoutingRev(String prdtId);
}
