package com.innomes.main.system.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.innomes.main.exception.CAccountNotFoundException;
import com.innomes.main.master.service.MasterUserService.RolesLevel;
import com.innomes.main.repository.SYS800Repository;
import com.innomes.main.system.dto.SystemAccountDTO;
import com.innomes.main.system.model.SYS800;


@Service
@Transactional
public class AccountService {
	@Autowired
	private SYS800Repository sys800Repository;
	
	@Cacheable(value = "sys800")
	public List<SYS800> GetSYS800All(){ return sys800Repository.findAll();}
	
	//계정 조회
	public SystemAccountDTO getAccount(String userNo) {
		SYS800 sys800 = sys800Repository.findById(userNo).orElseThrow(CAccountNotFoundException::new);
		
		return SystemAccountDTO.builder()
				.userNo(sys800.getUserNo())
				.userId(sys800.getUserId())
				.roles(findMyRoleLevel(sys800.getRoles()))
				.build();
		
	}
	
	//앞에 둘수록 높은 권한
    public enum RolesLevel {
    	ROLE_ADMIN, ROLE_USER
    }
	
	
	//권한 우선순위 반환
	public String findMyRoleLevel(List<String> roles) {
		int rowLevel = RolesLevel.values().length;
		String myRole = "";
		for(int i=0;i<roles.size();i++) {			
			if(RolesLevel.valueOf(roles.get(i)).ordinal() <= rowLevel) {
				rowLevel = RolesLevel.valueOf(roles.get(i)).ordinal();
				myRole = RolesLevel.valueOf(roles.get(i)).name();
			}
		}
		
		return myRole;
	}
}
