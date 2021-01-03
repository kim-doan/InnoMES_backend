package com.innomes.main.master.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * TABLE ID : MST140
 * TABLE NAME : 사용자정보
 * AUTHOR : 김도안
 * 
 */
@Entity
@Table(name = "MST140")
@Getter @Setter
@JsonIgnoreProperties({"enabled", "credentialsNonExpired", "accountNonLocked", "accountNonExpired", "password", "mst141"})
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MST140 implements UserDetails {
	@Id
	@Column(name="USER_NO")
	private String userNo;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="USER_NAME") // user 아이디 api roles때문에 username으로 변환
	private String username;
	
	@Column(name="USER_PASSWORD")
	private String userPassword;
	
	@Column(name="DEPARTMENT_CODE")
	private String departmentCode;
	
	@Column(name="GRADE_CODE")
	private String gradeCode;
	
	@Column(name="USER_TYPE")
	private String userType;
	
	@Column(name="RECRUTE_DATE")
	private Date recruteDate;
	
	@Column(name="RESIGN_DATE")
	private Date resignDate;
	
	@Column(name="TEAM_CODE")
	private String teamCode;
	
	@Column(name="SHIFT")
	private String shift;
	
	@Column(name="LEADER_YN")
	private int leaderYN;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="CREATE_USER", insertable = true, updatable = false)
	private String createUser;
	
	@Column(name="CREATE_TIME", insertable = true, updatable = false)
	private Date createTime;
	
	@Column(name="UPDATE_USER", insertable = false, updatable = true)
	private String updateUser;
	
	@Column(name="UPDATE_TIME", insertable = false, updatable = true)
	private Date updateTime;
	
	@Column(name="USED", insertable = false, updatable = true)
	private int used;

//	@ElementCollection(fetch = FetchType.LAZY)
//	@CollectionTable(name = "SYS800", joinColumns = @JoinColumn(name = "userNo"))
//	@Builder.Default
//	private List<String> roles = new ArrayList<>();
	
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO Auto-generated method stub
//		return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
}
