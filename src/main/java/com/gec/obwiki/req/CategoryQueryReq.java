package com.gec.obwiki.req;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CategoryQueryReq extends PageReq {
    private String name;
} 