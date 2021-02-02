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
import com.innomes.main.master.dto.MasterProductDTO;
import com.innomes.main.master.model.MST111;
import com.innomes.main.master.model.MST112;
import com.innomes.main.master.model.MST113;
import com.innomes.main.master.model.MST114;
import com.innomes.main.master.service.MasterProductService;
import com.innomes.main.repository.COD100Repository;
import com.innomes.main.repository.MST110Repository;
import com.innomes.main.repository.MST111Repository;
import com.innomes.main.repository.MST112Repository;
import com.innomes.main.repository.MST113Repository;
import com.innomes.main.repository.MST114Repository;


@Service
public class MasterPoolService implements ApplicationListener<ApplicationReadyEvent> {
	
	@Autowired
	private COD100Repository cod100Repository; // 사용자 코드
	@Autowired
	private MasterProductService masterProductService; // 제품 정보
	@Autowired
	private MST112Repository mst112Repository; // 자재 정보
	@Autowired
	private MST113Repository mst113Repository; // 공구 정보
	@Autowired
	private MST114Repository mst114Repository; // 예비품 정보
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		SetCOD100List();
		SetMST111List();
		SetMST112List();
		SetMST113List();
		SetMST114List();
	}
	
	private static Map<String, COD100> cod100Pool = new Hashtable<String, COD100>();
	private static Map<String, MasterProductDTO> mst111Pool = new Hashtable<String, MasterProductDTO>();
	private static Map<String, MST112> mst112Pool = new Hashtable<String, MST112>();
	private static Map<String, MST113> mst113Pool = new Hashtable<String, MST113>();
	private static Map<String, MST114> mst114Pool = new Hashtable<String, MST114>();
	
	/** 코드맵정보 */
	public Map<String, COD100> getCOD100Map() {return cod100Pool;}
	/** 제품상세 맵정보 */
	public Map<String, MasterProductDTO> getMST111Map() {return mst111Pool;}
	/** 자재상세 맵정보 */
	public Map<String, MST112> getMST112Map() {return mst112Pool;}
	/** 공구상세 맵정보 */
	public Map<String, MST113> getMST113Map() {return mst113Pool;}	
	/** 예비품상세 맵정보 */
	public Map<String, MST114> getMST114Map() {return mst114Pool;}	
	
	/** 코드정보 */
	public COD100 getCOD100(String code){ return getCOD100Map().get(code);}
	/** 제품상세정보 */
	public MasterProductDTO getMST111(String prdtId){return getMST111Map().get(prdtId);}	
	/** 자재상세정보 */
	public MST112 getMST112(String matId){return getMST112Map().get(matId);}	
	/** 공구상세정보 */
	public MST113 getMST113(String toolId){return getMST113Map().get(toolId);}	
	/** 예비품상세정보 */
	public MST114 getMST114(String partId){return getMST114Map().get(partId);}	
	
	/** 코드명 */
	public String getCodeName(String code, String language) {
		COD100 cod100 = getCOD100(code);
		if(cod100 == null) {
			return null;
		}
        switch (language) {
	        case "KR": return cod100.getCodeKR();
	        case "EN": return cod100.getCodeEN();
	        case "JP": return cod100.getCodeJP();
	        case "CH": return cod100.getCodeCH();
	        default: return cod100.getCodeEN();
        }
	}
	
	public void SetCOD100List() {
		List<COD100> cod100List = cod100Repository.findAll();
		cod100Pool = Cod100ListToMap(cod100List);
	}
	
	public void SetMST111List() {
		List<MasterProductDTO> mst111List = masterProductService.findAll();
		mst111Pool = Mst111ListToMap(mst111List);
	}

	public void SetMST112List() {
		List<MST112> mst112List = mst112Repository.findAll();
		mst112Pool = Mst112ListToMap(mst112List);
	}	

	public void SetMST113List() {
		List<MST113> mst113List = mst113Repository.findAll();
		mst113Pool = Mst113ListToMap(mst113List);
	}

	public void SetMST114List() {
		List<MST114> mst114List = mst114Repository.findAll();
		mst114Pool = Mst114ListToMap(mst114List);
	}
	
	private Map<String, COD100> Cod100ListToMap(List<COD100> cod100List){return cod100List.stream().collect(Collectors.toMap(COD100::getCode, cod100 -> cod100));}
	private Map<String, MasterProductDTO> Mst111ListToMap(List<MasterProductDTO> mst111List){return mst111List.stream().collect(Collectors.toMap(MasterProductDTO::getPrdtId, masterProductDTO -> masterProductDTO));}
	private Map<String, MST112> Mst112ListToMap(List<MST112> mst112List){return mst112List.stream().collect(Collectors.toMap(MST112::getMatId, mst112 -> mst112));}
	private Map<String, MST113> Mst113ListToMap(List<MST113> mst113List){return mst113List.stream().collect(Collectors.toMap(MST113::getToolId, mst113 -> mst113));}	
	private Map<String, MST114> Mst114ListToMap(List<MST114> mst114List){return mst114List.stream().collect(Collectors.toMap(MST114::getPartId, mst114 -> mst114));}
}
