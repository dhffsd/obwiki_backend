package com.gec.obwiki.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserLoginResp {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String loginName;
    private String name;
    private String token;
} 