package com.innomes.main.master.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.innomes.main.exception.CUserNotFoundException;
import com.innomes.main.repository.MST140Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

	private final MST140Repository mst140Repository;

	@Override
	public UserDetails loadUserByUsername(String userNo) throws UsernameNotFoundException {
		return mst140Repository.findById(userNo).orElseThrow(CUserNotFoundException::new);
	}
}
