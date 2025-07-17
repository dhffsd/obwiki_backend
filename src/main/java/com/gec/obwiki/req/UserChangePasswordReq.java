package com.gec.obwiki.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserChangePasswordReq {
    @NotNull(message = "【用户ID】不能为空")
    private Long id;
    
    @NotNull(message = "【原密码】不能为空")
    private String oldPassword;
    
    @NotNull(message = "【新密码】不能为空")
    private String newPassword;
} 