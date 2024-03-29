package com.innomes.main.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;


public class JwtAuthenticationFilter extends GenericFilterBean {
	private JwtTokenProvider jwtTokenProvider;
	
	// jwt provider 주입
	public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	// Request로 들어오는 Jwt Token의 유효성을 검증(jwtTokenProvider.validateToken)하는 필터를 필터 체인에 등록
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain)
			throws IOException, ServletException {
		String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
		
		if(token != null && jwtTokenProvider.validateToken(token)) {
			Authentication auth = jwtTokenProvider.getAuthentication(token);
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		filterchain.doFilter(request, response);
	}
}
