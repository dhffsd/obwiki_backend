package com.gec.obwiki.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.obwiki.entity.Category;
import com.gec.obwiki.req.CategoryQueryReq;
import com.gec.obwiki.req.CategorySaveReq;
import com.gec.obwiki.resp.CategoryQueryResp;
import com.gec.obwiki.resp.PageResp;

import java.util.List;

public interface ICategoryService extends IService<Category> {
    PageResp<CategoryQueryResp> list(CategoryQueryReq req);
    List<CategoryQueryResp> all();
    void save(CategorySaveReq req);
    void delete(Long id);
} 