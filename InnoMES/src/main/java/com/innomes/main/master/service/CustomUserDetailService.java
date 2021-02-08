package com.innomes.main.master.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.innomes.main.exception.CUserNotFoundException;
import com.innomes.main.repository.MST140Repository;
import com.innomes.main.repository.SYS800Repository;
import com.innomes.main.system.model.SYS800;
import com.innomes.main.system.service.AccountService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
	@Autowired
	private AccountService accountService;

	@Override
	public UserDetails loadUserByUsername(String userNo) throws UsernameNotFoundException {
		Optional<SYS800> userInfo = accountService.GetSYS800All().stream()
				.filter(a -> a.getUserNo().equals(userNo))
				.findFirst();
		return userInfo.orElseThrow(CUserNotFoundException::new);
	}
}
