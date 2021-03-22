package com.innomes.main.repository.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.innomes.main.master.model.MST151;
import com.innomes.main.master.model.MST151PK;
import com.innomes.main.master.param.MasterPriceItemParam;
import com.innomes.main.master.param.MasterPriceParam;

public interface MST151RepositoryCustom {
	//전체 조회
	Page<MST151> findAllLike(MasterPriceItemParam masterPriceItemParam, Pageable pageable);
	
	//최상위 단가 조회
	List<MST151> findPriceRevList(String priceType);
	
	//단가 이력 조회
	List<MST151> findPriceRevLog(MasterPriceItemParam masterPriceItemParam);
	
	//최상위 리비젼 조회
	Integer findMaxRev(MST151PK pk);
	
	//이전 리비전 비활성화
	long delBeforeRev(MST151PK pk, int used);
}
