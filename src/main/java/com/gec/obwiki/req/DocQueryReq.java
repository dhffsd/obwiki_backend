package com.gec.obwiki.req;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DocQueryReq extends PageReq {
    private String name;
} 