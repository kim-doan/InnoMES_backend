package com.innomes.main.master.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.innomes.main.code.service.CodeInfoService;
import com.innomes.main.exception.CUserDuplicationException;
import com.innomes.main.exception.CUserNotFoundException;
import com.innomes.main.exception.CUserRegisterFailException;
import com.innomes.main.exception.CUserSaveException;
import com.innomes.main.jwt.JwtTokenProvider;
import com.innomes.main.master.dto.MasterUserDTO;
import com.innomes.main.master.model.MST140;
import com.innomes.main.master.param.AuthenticationRequest;
import com.innomes.main.master.param.MasterUserParam;
import com.innomes.main.repository.MST140Repository;
import com.innomes.main.repository.SYS800Repository;
import com.innomes.main.repository.SYS810Repository;
import com.innomes.main.system.model.SYS800;
import com.innomes.main.system.model.SYS810;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MasterUserService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired 
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private MST140Repository mst140Repository;
	
	@Autowired
	private SYS800Repository sys800Repository;
	
	@Autowired
	private SYS810Repository sys810Repository;
	
	@Autowired
	private CodeInfoService codeService;
	
	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private int batchSize;
	
	//전체 사용자 정보 조회
	public Page<MasterUserDTO> findAllLike(MasterUserParam masterUserParam, Pageable pageable) {
		Page<MST140> output = mst140Repository.findAllLike(masterUserParam, pageable);
		
		List<MST140> content = output.getContent();
		
		List<MasterUserDTO> userDtoList = new ArrayList<MasterUserDTO>();
		
		for(int i=0;i<content.size();i++) {
			userDtoList.add(MasterUserDTO.builder()
							.userNo(content.get(i).getUserNo())
							.userName(content.get(i).getUserName())
							.departmentCode(content.get(i).getDepartmentCode())
							.gradeCode(content.get(i).getGradeCode())
							.userType(content.get(i).getUserType())
							.recruteDate(content.get(i).getRecruteDate())
							.resignDate(content.get(i).getResignDate())
							.teamCode(content.get(i).getTeamCode())
							.shift(content.get(i).getShift())
							.leaderYN(content.get(i).getLeaderYN())
							.description(content.get(i).getDescription())
							.used(content.get(i).getUsed())
							.build());
		}
		
		return new PageImpl<>(userDtoList, pageable, output.getTotalElements());
	}
	
	//로그인
	public String login(AuthenticationRequest user) {
		SYS800 dbSys800 = sys800Repository.findByLoginUserId(user.getUserId()).orElseThrow(CUserNotFoundException::new);
		
		if(passwordEncoder.matches(user.getUserPassword(), dbSys800.getUserPassword())) {
			String token = "";
			
			if(user.getUserPassword().equals(dbSys800.getUserNo())) { // 비밀번호가 초기비밀번호인 사번이라면
				token = jwtTokenProvider.createToken(dbSys800.getUserNo(), dbSys800.getRoles(), true);
			} else {
				token = jwtTokenProvider.createToken(dbSys800.getUserNo(), dbSys800.getRoles(), false);
			}
			
			return token;
		} else {
			return null;
		}
	}
	
	//회원가입
	public boolean insertUser(MasterUserParam[] masterUserParam) {
		boolean success = true;
		
		for(int i=0;i<masterUserParam.length;i++) {
			try {
				Optional<SYS800> dbSys800;
				
				if(masterUserParam[i].getUserId() == null) {
					dbSys800 = sys800Repository.findById(masterUserParam[i].getUserNo());
				} else {
					dbSys800 = sys800Repository.findByUserId(masterUserParam[i].getUserId());
				}
				
				//아이디 중복체크
				if(dbSys800.isPresent() == false) { // 중복이 아닌경우
					if(masterUserParam[i].getUserPassword() == null) {
						masterUserParam[i].setUserPassword(passwordEncoder.encode(masterUserParam[i].getUserNo())); // 초기비밀번호 사번
					} else {
						masterUserParam[i].setUserPassword(passwordEncoder.encode(masterUserParam[i].getUserPassword()));
					}
					
					mst140Repository.save(MST140.builder()
										.userNo(masterUserParam[i].getUserNo())
										.userName(masterUserParam[i].getUserName())
										.departmentCode(masterUserParam[i].getDepartmentCode())
										.gradeCode(masterUserParam[i].getGradeCode())
										.userType(masterUserParam[i].getUserType())
										.recruteDate(masterUserParam[i].getRecruteDate())
										.resignDate(masterUserParam[i].getResignDate())
										.teamCode(masterUserParam[i].getTeamCode())
										.shift(masterUserParam[i].getShift())
										.leaderYN(masterUserParam[i].getLeaderYN())
										.description(masterUserParam[i].getDescription())
										.createUser(masterUserParam[i].getCreateUser())
										.createTime(new Date())
										.updateUser(masterUserParam[i].getUpdateUser())
										.updateTime(new Date())
										.used(masterUserParam[i].getUsed())
										.isNew(true)
										.build());
					mst140Repository.flush();
					
					sys800Repository.save(SYS800.builder()
										.userNo(masterUserParam[i].getUserNo())
										.userId(masterUserParam[i].getUserId())
										.userPassword(masterUserParam[i].getUserPassword())
										.createUser(masterUserParam[i].getCreateUser())
										.createTime(new Date())
										.updateUser(masterUserParam[i].getUpdateUser())
										.updateTime(masterUserParam[i].getUpdateTime())
										.roles(Collections.singletonList("ROLE_USER"))
										.used(masterUserParam[i].getUsed())
										.build());
					sys800Repository.flush();
					
				} else { // 이미 존재하는 아이디
					throw new CUserDuplicationException();
				}
			} catch(CUserDuplicationException e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 롤백
				throw new CUserDuplicationException();
			} catch(CUserRegisterFailException e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 롤백
				throw new CUserRegisterFailException();
			} catch(Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 롤백
				success = false;
			}
		}
		return success;
	}
	
	//아이디 중복 조회 true : 사용가능한 아이디 ,false: 사용할수 없는 아이디
	public boolean idCheck(String userId) {
		Optional<SYS800> sys800 = sys800Repository.findByUserId(userId);
		
		if(sys800.isPresent() == true) {
			return false;
		} else {
			return true;
		}
	}
	
	//회원 수정
	public boolean updateUser(MasterUserParam[] masterUserParam) {
		boolean success = true;
		
		try {
			for(int i=0;i<masterUserParam.length;i++) {
				MST140 dbMst140 = mst140Repository.findById(masterUserParam[i].getUserNo()).orElseThrow(CUserNotFoundException::new);
				
				mst140Repository.save(MST140.builder()
						.userNo(dbMst140.getUserNo())
						.userName(masterUserParam[i].getUserName())
						.departmentCode(masterUserParam[i].getDepartmentCode())
						.gradeCode(masterUserParam[i].getGradeCode())
						.userType(masterUserParam[i].getUserType())
						.recruteDate(masterUserParam[i].getRecruteDate())
						.resignDate(masterUserParam[i].getResignDate())
						.teamCode(masterUserParam[i].getTeamCode())
						.shift(masterUserParam[i].getShift())
						.leaderYN(masterUserParam[i].getLeaderYN())
						.description(masterUserParam[i].getDescription())
						.createUser(masterUserParam[i].getCreateUser())
						.createTime(new Date())
						.updateUser(masterUserParam[i].getUpdateUser())
						.updateTime(new Date())
						.used(masterUserParam[i].getUsed())
						.isNew(false)
						.build());
			}
		} catch(CUserNotFoundException e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 롤백
			throw new CUserNotFoundException();
		} catch(CUserDuplicationException e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 롤백
			throw new CUserDuplicationException();
		} catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 롤백
			success = false;
		}
			
		return success;
	}
	
	//계정 수정
	public boolean updateAccount(MasterUserParam[] masterUserParam) {
		boolean success = true;
		
		try {
			for(int i=0;i<masterUserParam.length;i++) {
				SYS800 dbSys800 = sys800Repository.findById(masterUserParam[i].getUserNo()).orElseThrow(CUserNotFoundException::new);
				
				sys800Repository.save(SYS800.builder()
						.userNo(masterUserParam[i].getUserNo())
						.userId(masterUserParam[i].getUserId())
						.userPassword(passwordEncoder.encode(masterUserParam[i].getUserPassword()))
						.createUser(masterUserParam[i].getCreateUser())
						.createTime(new Date())
						.updateUser(masterUserParam[i].getUpdateUser())
						.updateTime(masterUserParam[i].getUpdateTime())
						.roles(dbSys800.getRoles())
						.used(masterUserParam[i].getUsed())
						.build());
				}
		} catch(CUserNotFoundException e) { 
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 롤백
			throw new CUserNotFoundException();
		} catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 롤백
			success = false;
		}
		
		return success;
	}
	
	//사번으로 단건 조회
	public Optional<MST140> findByUserNo(String userNo) {
		return mst140Repository.findById(userNo);
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
	
	//권한 수정
	public boolean updateRoles(HashMap<String, Object> param) {
		boolean success = true;
		
		List<SYS810> sys810List = new ArrayList<SYS810>();
		String userNo = param.get("userNo").toString();
		String role = param.get("role").toString();
		
		try {
			Optional<SYS800> dbSys800 = sys800Repository.findById(userNo);
			
			if(dbSys800.isPresent() == true) {
				sys810Repository.deleteByUserNo(userNo);
				
				switch(role) {
					case "ROLE_ADMIN":
						for(int i=0;i<RolesLevel.values().length;i++) {
							SYS810 sys810 = SYS810.builder()
									.userNo(userNo)
									.roles(RolesLevel.values()[i].name())
									.build();
							
							sys810List.add(sys810);
						}
						break;
					case "ROLE_USER":
						SYS810 sys810 = SYS810.builder()
						.userNo(userNo)
						.roles("ROLE_USER")
						.build();
						
						sys810List.add(sys810);
						break;
				}
				
				sys810Repository.saveAll(sys810List);
				sys810Repository.flush();
				sys810List.clear();
			} else {
				throw new CUserNotFoundException();
			}
		} catch (CUserSaveException e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 롤백
			throw new CUserSaveException();
		} catch (CUserNotFoundException e) { 
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 롤백
			throw new CUserNotFoundException();
		} catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 롤백
			success = false;
		}
		
		return success;
	}
	
	//유저 비활성화 / 활성화
	public boolean deleteUserInfo(MasterUserParam[] masterUserParam) {
		boolean success = true;
		List<MST140> mst140List = new ArrayList<MST140>();
		
		try {
			for(int i=0;i<masterUserParam.length;i++) {
				MST140 dbMST140 = mst140Repository.findById(masterUserParam[i].getUserNo()).orElseThrow(CUserNotFoundException::new);
				
				MST140 mst140 = dbMST140;
				mst140.setResignDate(new Date()); // 퇴사일자 지정
				mst140.setUpdateUser(masterUserParam[i].getUpdateUser());
				mst140.setUpdateTime(new Date());
				
				if(mst140.getUsed() == 1) {
					mst140.setUsed(0);
				} else {
					mst140.setUsed(1);
				}
				
				sys800Repository.delAccount(masterUserParam[i].getUserNo(), mst140.getUsed());
				mst140List.add(mst140);
				
				if ((i + 1) % batchSize == 0 && i > 0) {
					mst140Repository.saveAll(mst140List);
					mst140Repository.flush();
					mst140List.clear();
				}
				
				mst140Repository.saveAll(mst140List);
				mst140Repository.flush();
				mst140List.clear();
			}
		} catch (CUserNotFoundException e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 롤백
			throw new CUserNotFoundException();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 롤백
			success = false;
		}
		
		return success;
	}
}
