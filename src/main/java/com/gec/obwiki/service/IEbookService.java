package com.gec.obwiki.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.obwiki.entity.Ebook;
import com.gec.obwiki.resp.PageResp;

public interface IEbookService extends IService<Ebook> {
    PageResp<Ebook> list(int page, int size, String name, String categoryIds);
    int vote(Long id);
} 