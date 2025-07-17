package com.gec.obwiki.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class UserQueryResp {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    
    private String loginName;
    private String nickname;
    private String email;
    private String phone;
    private String avatar;
    private Integer status;
    private String name;
    
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
} 