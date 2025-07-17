package com.gec.obwiki.req;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class UserLoginReq {
    @NotNull(message = "【登录名】不能为空")
    private String loginName;

    @NotNull(message = "【密码】不能为空")
    private String password;
} 