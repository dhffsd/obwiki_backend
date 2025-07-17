package com.gec.obwiki.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.obwiki.entity.Doc;
import com.gec.obwiki.req.DocQueryReq;
import com.gec.obwiki.req.DocSaveReq;
import com.gec.obwiki.resp.DocQueryResp;
import com.gec.obwiki.resp.PageResp;

import java.util.List;

public interface IDocService extends IService<Doc> {
    PageResp<DocQueryResp> list(DocQueryReq req);
    List<DocQueryResp> all();
    void save(DocSaveReq req);
    void delete(Long id);
    List<DocQueryResp> allByEbookId(Long ebookId);
    void updateEbookInfo();
    String findContent(Long id);
    void vote(Long id);
} 