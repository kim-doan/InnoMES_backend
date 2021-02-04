package com.innomes.main.master.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.innomes.main.exception.CUserNotFoundException;
import com.innomes.main.repository.MST140Repository;
import com.innomes.main.repository.SYS800Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private SYS800Repository sys800Repository;

	@Override
	public UserDetails loadUserByUsername(String userNo) throws UsernameNotFoundException {
		return sys800Repository.findById(userNo).orElseThrow(CUserNotFoundException::new);
	}
}
