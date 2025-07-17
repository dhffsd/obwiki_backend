package com.gec.obwiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.obwiki.entity.Category;
import com.gec.obwiki.mapper.CategoryMapper;
import com.gec.obwiki.req.CategoryQueryReq;
import com.gec.obwiki.req.CategorySaveReq;
import com.gec.obwiki.resp.CategoryQueryResp;
import com.gec.obwiki.resp.PageResp;
import com.gec.obwiki.service.ICategoryService;
import com.gec.obwiki.util.CopyUtil;
import com.gec.obwiki.util.SnowFlake;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
    @Autowired
    private SnowFlake snowFlake;

    @Override
    public PageResp<CategoryQueryResp> list(CategoryQueryReq req) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(req.getName()), "name", req.getName());
        Page<Category> page = new Page<>(req.getPage(), req.getSize());
        IPage<Category> pageResult = baseMapper.selectPage(page, queryWrapper);
        List<CategoryQueryResp> resps = CopyUtil.copyList(pageResult.getRecords(), CategoryQueryResp.class);
        PageResp<CategoryQueryResp> pageResp = new PageResp<>();
        pageResp.setList(resps);
        pageResp.setTotal(pageResult.getTotal());
        return pageResp;
    }

    @Override
    public List<CategoryQueryResp> all() {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        List<Category> list = baseMapper.selectList(queryWrapper);
        return CopyUtil.copyList(list, CategoryQueryResp.class);
    }

    @Override
    public void save(CategorySaveReq req) {
        Category category = CopyUtil.copy(req, Category.class);
        if (req.getId() == null) {
            category.setId(snowFlake.nextId());
            baseMapper.insert(category);
        } else {
            baseMapper.updateById(category);
        }
    }

    @Override
    public void delete(Long id) {
        baseMapper.deleteById(id);
    }
} 