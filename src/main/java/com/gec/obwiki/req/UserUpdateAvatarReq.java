package com.gec.obwiki.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserUpdateAvatarReq {
    @NotNull(message = "【用户ID】不能为空")
    private Long id;
    
    @NotNull(message = "【头像URL】不能为空")
    private String avatar;
} 