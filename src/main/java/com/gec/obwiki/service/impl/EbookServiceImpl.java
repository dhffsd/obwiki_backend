package com.gec.obwiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.obwiki.entity.Ebook;
import com.gec.obwiki.mapper.EbookMapper;
import com.gec.obwiki.resp.PageResp;
import com.gec.obwiki.service.IEbookService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class EbookServiceImpl extends ServiceImpl<EbookMapper, Ebook> implements IEbookService {
    @Override
    public PageResp<Ebook> list(int page, int size, String name, String categoryIds) {
        QueryWrapper<Ebook> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("name", name);
        }
        if (StringUtils.isNotBlank(categoryIds)) {
            String[] ids = categoryIds.split(",");
            wrapper.and(w -> w.in("category1_id", (Object[]) ids).or().in("category2_id", (Object[]) ids));
        }
        Page<Ebook> pageObj = new Page<>(page, size);
        Page<Ebook> result = baseMapper.selectPage(pageObj, wrapper);
        PageResp<Ebook> resp = new PageResp<>();
        resp.setList(result.getRecords());
        resp.setTotal(result.getTotal());
        return resp;
    }

    @Override
    public int vote(Long id) {
        // 原子自增，防止并发问题
        baseMapper.increaseVoteCount(id);
        Ebook ebook = baseMapper.selectById(id);
        return ebook != null && ebook.getVoteCount() != null ? ebook.getVoteCount() : 0;
    }
} 