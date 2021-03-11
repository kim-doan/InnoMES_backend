package com.innomes.main.pool.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.innomes.main.code.model.COD100;
import com.innomes.main.code.service.CodeInfoService;
import com.innomes.main.master.dto.MasterCompanyDTO;
import com.innomes.main.master.dto.MasterMaterialDTO;
import com.innomes.main.master.dto.MasterProcessDTO;
import com.innomes.main.master.dto.MasterProductDTO;
import com.innomes.main.master.dto.MasterSpareDTO;
import com.innomes.main.master.dto.MasterToolDTO;
import com.innomes.main.master.dto.MasterUserDTO;
import com.innomes.main.master.model.MST111;
import com.innomes.main.master.model.MST112;
import com.innomes.main.master.model.MST113;
import com.innomes.main.master.model.MST114;
import com.innomes.main.master.service.MasterCompanyService;
import com.innomes.main.master.service.MasterMaterialService;
import com.innomes.main.master.service.MasterProcessService;
import com.innomes.main.master.service.MasterProductService;
import com.innomes.main.master.service.MasterSpareService;
import com.innomes.main.master.service.MasterToolService;
import com.innomes.main.master.service.MasterUserService;
import com.innomes.main.repository.COD100Repository;
import com.innomes.main.repository.MST110Repository;
import com.innomes.main.repository.MST111Repository;
import com.innomes.main.repository.MST112Repository;
import com.innomes.main.repository.MST113Repository;
import com.innomes.main.repository.MST114Repository;


@Service
public class MasterPoolService implements ApplicationListener<ApplicationReadyEvent> {
	@Autowired
	private MasterProductService masterProductService; // 제품 정보
	@Autowired
	private MasterMaterialService masterMaterialService; // 자재 정보
	@Autowired
	private MasterToolService masterToolService; // 공구 정보
	@Autowired
	private MasterSpareService masterSpareService; // 예비품 정보
	@Autowired
	private MasterProcessService masterProcessService; // 공정 정보
	@Autowired
	private MasterUserService masterUserService; // 유저 정보
	@Autowired
	private MasterCompanyService masterCompanyService; // 거래처 정보
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		SetMST111List();
		SetMST112List();
		SetMST113List();
		SetMST114List();
		SetMST140List();
		SetMST150List();
	}
	
	private static Map<String, MasterProductDTO> mst111Pool = new Hashtable<String, MasterProductDTO>();
	private static Map<String, MasterMaterialDTO> mst112Pool = new Hashtable<String, MasterMaterialDTO>();
	private static Map<String, MasterToolDTO> mst113Pool = new Hashtable<String, MasterToolDTO>();
	private static Map<String, MasterSpareDTO> mst114Pool = new Hashtable<String, MasterSpareDTO>();
	private static Map<String, MasterProcessDTO> mst120Pool = new Hashtable<String, MasterProcessDTO>();
	private static Map<String, MasterUserDTO> mst140Pool = new Hashtable<String, MasterUserDTO>();
	private static Map<String, MasterCompanyDTO> mst150Pool = new Hashtable<String, MasterCompanyDTO>();
	

	/** 제품상세 맵정보 */
	public Map<String, MasterProductDTO> getMST111Map() {return mst111Pool;}
	/** 자재상세 맵정보 */
	public Map<String, MasterMaterialDTO> getMST112Map() {return mst112Pool;}
	/** 공구상세 맵정보 */
	public Map<String, MasterToolDTO> getMST113Map() {return mst113Pool;}	
	/** 예비품상세 맵정보 */
	public Map<String, MasterSpareDTO> getMST114Map() {return mst114Pool;}	
	/** 공정정보 맵정보 */
	public Map<String, MasterProcessDTO> getMST120Map() { return mst120Pool; }
	/** 유저정보 맵정보 */
	public Map<String, MasterUserDTO> getMST140Map() { return mst140Pool; }
	/** 거래처정보 맵정보 */
	public Map<String, MasterCompanyDTO> getMST150Map() { return mst150Pool; }
	
	/** 제품상세정보 */
	public MasterProductDTO getMST111(String prdtId){return getMST111Map().get(prdtId);}	
	/** 자재상세정보 */
	public MasterMaterialDTO getMST112(String matId){return getMST112Map().get(matId);}	
	/** 공구상세정보 */
	public MasterToolDTO getMST113(String toolId){return getMST113Map().get(toolId);}	
	/** 예비품상세정보 */
	public MasterSpareDTO getMST114(String partId){return getMST114Map().get(partId);}	
	/** 공정상세정보 */
	public MasterProcessDTO getMST120(String procCode) { return getMST120Map().get(procCode); }
	/** 유저상세정보 */
	public MasterUserDTO getMST140(String userNo) { return getMST140Map().get(userNo); }
	/** 거래처상세정보 */
	public MasterCompanyDTO getMST150(String compId) { return getMST150Map().get(compId); }
	
	
	public void SetMST111List() {
		List<MasterProductDTO> mst111List = masterProductService.findAll();
		mst111Pool = Mst111ListToMap(mst111List);
	}

	public void SetMST112List() {
		List<MasterMaterialDTO> mst112List = masterMaterialService.findAll();
		mst112Pool = Mst112ListToMap(mst112List);
	}	

	public void SetMST113List() {
		List<MasterToolDTO> mst113List = masterToolService.findAll();
		mst113Pool = Mst113ListToMap(mst113List);
	}

	public void SetMST114List() {
		List<MasterSpareDTO> mst114List = masterSpareService.findAll();
		mst114Pool = Mst114ListToMap(mst114List);
	}
	
	public void SetMST120List() {
		List<MasterProcessDTO> mst120List = masterProcessService.findAll();
		mst120Pool = Mst120ListToMap(mst120List);
	}
	
	public void SetMST140List() {
		List<MasterUserDTO> mst140List = masterUserService.findAll();
		mst140Pool = Mst140ListToMap(mst140List);
	}
	
	public void SetMST150List() {
		List<MasterCompanyDTO> mst150List = masterCompanyService.findAll();
		mst150Pool = Mst150ListToMap(mst150List);
	}
	
	private Map<String, MasterProductDTO> Mst111ListToMap(List<MasterProductDTO> mst111List){return mst111List.stream().collect(Collectors.toMap(MasterProductDTO::getPrdtId, masterProductDTO -> masterProductDTO));}
	private Map<String, MasterMaterialDTO> Mst112ListToMap(List<MasterMaterialDTO> mst112List){return mst112List.stream().collect(Collectors.toMap(MasterMaterialDTO::getMatId, MasterMaterialDTO -> MasterMaterialDTO));}
	private Map<String, MasterToolDTO> Mst113ListToMap(List<MasterToolDTO> mst113List){return mst113List.stream().collect(Collectors.toMap(MasterToolDTO::getToolId, MasterToolDTO -> MasterToolDTO));}	
	private Map<String, MasterSpareDTO> Mst114ListToMap(List<MasterSpareDTO> mst114List){return mst114List.stream().collect(Collectors.toMap(MasterSpareDTO::getPartId, MasterSpareDTO -> MasterSpareDTO));}
	private Map<String, MasterProcessDTO> Mst120ListToMap(List<MasterProcessDTO> mst120List){return mst120List.stream().collect(Collectors.toMap(MasterProcessDTO::getProcCode, MasterProcessDTO -> MasterProcessDTO));}
	private Map<String, MasterUserDTO> Mst140ListToMap(List<MasterUserDTO> mst140List){return mst140List.stream().collect(Collectors.toMap(MasterUserDTO::getUserNo, MasterUserDTO -> MasterUserDTO));}
	private Map<String, MasterCompanyDTO> Mst150ListToMap(List<MasterCompanyDTO> mst150List){return mst150List.stream().collect(Collectors.toMap(MasterCompanyDTO::getCompId, MasterCompanyDTO -> MasterCompanyDTO));}
}
