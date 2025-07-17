package com.gec.obwiki.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.obwiki.entity.Content;
import com.gec.obwiki.mapper.ContentMapper;
import com.gec.obwiki.service.IContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements IContentService {
    @Autowired
    private ContentMapper contentMapper;

    @Override
    public String findContentById(Long id) {
        Content content = contentMapper.selectById(id);
        return content != null ? content.getContent() : "";
    }
} 