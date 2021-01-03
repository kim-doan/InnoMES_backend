package com.innomes.main.response.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginResult {
    @ApiModelProperty(value = "응답 성공여부 : true/false")
    private boolean success;

    @ApiModelProperty(value = "응답 코드 번호 : > 0 정상, < 0 비정상")
    private String code;

    @ApiModelProperty(value = "응답 메시지")
    private String msg;
    
    @ApiModelProperty(value = "토큰")
    
    private String token;
}
