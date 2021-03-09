package com.innomes.main.util.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilService {
	@Autowired
	ModelMapper modelMapper;
	
	/*
	 *   MODEL	=>	DTO
	 *   DTO	=>	MODEL
	 *   변환 메서드
	 */
	public <S,T> List<T> convertModelAndDto(List<S> source, Class<T> targetClass) {
		return source
				.stream()
				.map(element -> modelMapper.map(element, targetClass))
				.collect(Collectors.toList());
	}
}
