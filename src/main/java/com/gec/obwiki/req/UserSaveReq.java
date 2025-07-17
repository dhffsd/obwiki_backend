package com.gec.obwiki.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@ToString
public class UserSaveReq {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @NotNull(message = "【登录名】不能为空")
    private String loginName;

    private String nickname;
    
    @NotNull(message = "【密码】不能为空")
    private String password;
    
    @Email(message = "【邮箱】格式不正确")
    private String email;
    
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "【手机号】格式不正确")
    private String phone;
    
    private String avatar;
    
    private Integer status;
    
    private String name;
} 