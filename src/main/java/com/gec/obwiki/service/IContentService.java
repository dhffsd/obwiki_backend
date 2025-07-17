package com.gec.obwiki.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.obwiki.entity.Content;

public interface IContentService extends IService<Content> {
    String findContentById(Long id);
} 