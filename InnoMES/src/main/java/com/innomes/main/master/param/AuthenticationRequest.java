package com.innomes.main.master.param;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 단순히 ID와 PW를 받아오는 객체 
 * client에서 인증에 요청하는 값을 저장하는 클래스
 */

@Getter @Setter
public class AuthenticationRequest {
	private String userId;
	private String userPassword;
}