package com.gec.obwiki.req;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserQueryReq extends PageReq {
    private String name;
    private String loginName;
    private String nickname;
    private String email;
    private String phone;
    private Integer status;
} 