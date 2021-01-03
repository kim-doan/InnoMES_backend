package com.innomes.main.system.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DateService {

	private static String workdateChangeTime;
	
	@Value("${spring.factoryview.workdate_change_time}")
	private void setWorkdateChangeTime(String workdate_change_time) {
		workdateChangeTime = workdate_change_time;
	}
	
	public DateService() {
		setWorkdateChangeTime(null);
	}
	
	/**
	 * 현재시간
	 * @return
	 */
	public Date NowTime() {
		return new Date();
	}

	/**
	 * 오늘날짜
	 * @return
	 */
	public Date Today() {
		return java.sql.Date.valueOf(LocalDate.now());
	}
	
	/**
	 * 어제날짜
	 * @return
	 */
	public Date YesterDay() {
		return java.sql.Date.valueOf(LocalDate.now().minusDays(1));
	}

	/**
	 * 작업일자
	 * @return
	 */
	public Date WorkDate() {	
		return java.sql.Date.valueOf(WorkLocalDate());
	}
	
	/**
	 * 작업일자(String)
	 * @return
	 */
	public String WorkDateString() {	
		return WorkLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	/**
	 * 작업변경시간(String)
	 * @return
	 */
	public String WorkDateChangeTimeString() {
		return WorkdateChangeTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}
	

	
	private LocalDate WorkLocalDate() {
		LocalDate today = LocalDate.now();
		LocalDate yesterday = today.minusDays(1);
		LocalTime nowTime = LocalTime.now();
		
		LocalTime targetTime = WorkdateChangeTime();
		
		if(nowTime.isBefore(targetTime)){		
			return yesterday;
		} else {
			return today;
		}	
	}
	public LocalTime WorkdateChangeTime() {
		LocalTime targetTime = LocalTime.parse(workdateChangeTime);
		return targetTime;
	}
}
