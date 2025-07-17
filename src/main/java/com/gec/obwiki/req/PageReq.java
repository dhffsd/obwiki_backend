package com.gec.obwiki.req;

import lombok.Data;

@Data
public class PageReq {
    private Integer page = 1;
    private Integer size = 10;
} 