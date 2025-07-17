package com.gec.obwiki.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class UserResetPasswordReq {
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "【ID】不能为空")
    private Long id;

    @NotNull(message = "【密码】不能为空")
    private String password;
} 