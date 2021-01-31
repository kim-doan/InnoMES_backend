package com.innomes.main.master.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter @Setter
public abstract class BaseEntity {
	
	@ApiModelProperty(required = true, value = "생성자"
			+ "\n Author"
			+ "\n MST120.CREATE_USER")
	@Column(name="CREATE_USER", insertable = true, updatable = false)
	private String createUser;
	
	@ApiModelProperty(required = true, value = "생성일시"
			+ "\n Created time"
			+ "\n MST120.CREATE_TIME")
	//@Temporal(TemporalType.TIME)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="CREATE_TIME", insertable = true, updatable = false)
	private Date createTime;
	
	@ApiModelProperty(value = "수정자"
			+ "\n Modified by"
			+ "\n MST120.UPDATE_USER")
	@Column(name="UPDATE_USER", insertable = false, updatable = true)
	private String updateUser;
	
	@ApiModelProperty(value = "수정일시"
			+ "\n Modified time"
			+ "\n MST120.UPDATE_TIME")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="UPDATE_TIME", insertable = false, updatable = true)
	private Date updateTime;
}
