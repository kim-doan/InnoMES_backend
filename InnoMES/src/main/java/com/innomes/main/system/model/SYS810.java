package com.innomes.main.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@IdClass(SYS810PK.class)
@Entity
@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class SYS810 {
	@Id
	@Column(name = "USER_NO")
	private String userNo;
	
	@Id
	@Column(name = "ROLES")
	private String roles;
}
