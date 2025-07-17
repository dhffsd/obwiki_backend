package com.gec.obwiki.resp;

import lombok.Data;

import java.util.List;

@Data
public class PageResp<T> {
    private List<T> list;
    private Long total;
} 