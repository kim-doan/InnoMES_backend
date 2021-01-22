package com.innomes.main.master.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.innomes.main.master.model.MST220;
import com.innomes.main.master.param.MasterBomParam;
import com.innomes.main.repository.MST220Repository;

@Service
@Transactional
public class MasterBomService {
	@Autowired
	private MST220Repository mst220Repository;
	
	public List<MST220> getBomList(MasterBomParam masterBomParam) {
		return mst220Repository.findAllByPrdtIdAndProcCode(masterBomParam.getPrdtId(), masterBomParam.getProcCode());
	}
}
