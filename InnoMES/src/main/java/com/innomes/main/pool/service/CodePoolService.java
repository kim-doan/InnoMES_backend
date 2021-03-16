package com.innomes.main.pool.service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.innomes.main.code.model.COD100;
import com.innomes.main.code.model.COD200;
import com.innomes.main.code.model.COD300;
import com.innomes.main.code.model.COD400;
import com.innomes.main.repository.COD100Repository;
import com.innomes.main.repository.COD200Repository;
import com.innomes.main.repository.COD300Repository;
import com.innomes.main.repository.COD400Repository;

@Service
public class CodePoolService implements ApplicationListener<ApplicationReadyEvent> {
	
	/************************************** COD100 사용자코드 *****************************************/
	@Autowired
	private COD100Repository cod100Repository; // 사용자 코드
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		SetCOD100List();
		SetCOD200List();
		SetCOD300List();
		SetCOD400List();
	}

	private static Map<String, COD100> cod100Pool = new Hashtable<String, COD100>();
	
	/** 코드맵정보 */
	public Map<String, COD100> getCOD100Map() {return cod100Pool;}
	
	/** 코드정보 */
	public COD100 getCOD100(String code){ return getCOD100Map().get(code); }
	
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
	
	private Map<String, COD100> Cod100ListToMap(List<COD100> cod100List){return cod100List.stream().collect(Collectors.toMap(COD100::getCode, cod100 -> cod100));}
	
	
	/************************************** COD200 불량유형정보 *****************************************/
	@Autowired
	private COD200Repository cod200Repository;
	
	private static Map<String, COD200> cod200Pool = new Hashtable<String, COD200>();
	
	/** 코드맵정보 */
	public Map<String, COD200> getCOD200Map() {return cod200Pool;}
	
	/** 코드300 정보 */
	public COD200 getCOD200(String code){ return getCOD200Map().get(code); }
	
	public void SetCOD200List() {
		List<COD200> cod200List = cod200Repository.findAll();
		cod200Pool = Cod200ListToMap(cod200List);
	}
	private Map<String, COD200> Cod200ListToMap(List<COD200> cod200List){return cod200List.stream().collect(Collectors.toMap(COD200::getBadCode, cod200 -> cod200));}
	
	 
	/************************************** COD300 비가동유형정보 *****************************************/
	@Autowired
	private COD300Repository cod300Repository;
	
	private static Map<String, COD300> cod300Pool = new Hashtable<String, COD300>();
	
	/** 코드맵정보 */
	public Map<String, COD300> getCOD300Map() {return cod300Pool;}
	
	/** 코드300 정보 */
	public COD300 getCOD300(String code){ return getCOD300Map().get(code); }
	
	public void SetCOD300List() {
		List<COD300> cod300List = cod300Repository.findAll();
		cod300Pool = Cod300ListToMap(cod300List);
	}
	private Map<String, COD300> Cod300ListToMap(List<COD300> cod300List){return cod300List.stream().collect(Collectors.toMap(COD300::getStopCode, cod300 -> cod300));}
	
	
	/************************************** COD400 고장유형정보 *****************************************/
	 @Autowired
	private COD400Repository cod400Repository;
	
	private static Map<String, COD400> cod400Pool = new Hashtable<String, COD400>();
	
	/** 코드맵정보 */
	public Map<String, COD400> getCOD400Map() {return cod400Pool;}
	
	/** 코드400 정보 */
	public COD400 getCOD400(String code){ return getCOD400Map().get(code); }
	
	public void SetCOD400List() {
		List<COD400> cod400List = cod400Repository.findAll();
		cod400Pool = Cod400ListToMap(cod400List);
	}
	private Map<String, COD400> Cod400ListToMap(List<COD400> cod400List){return cod400List.stream().collect(Collectors.toMap(COD400::getFailCode, cod400 -> cod400));}
		
}
