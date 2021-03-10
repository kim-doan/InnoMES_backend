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
import com.innomes.main.repository.COD100Repository;

@Service
public class CodePoolService implements ApplicationListener<ApplicationReadyEvent> {
	@Autowired
	private COD100Repository cod100Repository; // 사용자 코드
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		SetCOD100List();
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
}
