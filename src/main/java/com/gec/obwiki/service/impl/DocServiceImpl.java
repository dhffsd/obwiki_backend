package com.gec.obwiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.obwiki.entity.Content;
import com.gec.obwiki.entity.Doc;
import com.gec.obwiki.exception.BusinessException;
import com.gec.obwiki.exception.BusinessExceptionCode;
import com.gec.obwiki.mapper.ContentMapper;
import com.gec.obwiki.mapper.DocMapper;
import com.gec.obwiki.req.DocQueryReq;
import com.gec.obwiki.req.DocSaveReq;
import com.gec.obwiki.resp.DocQueryResp;
import com.gec.obwiki.resp.PageResp;
import com.gec.obwiki.service.IContentService;
import com.gec.obwiki.service.IDocService;
import com.gec.obwiki.util.CopyUtil;
import com.gec.obwiki.util.RedisUtil;
import com.gec.obwiki.util.RequestContext;
import com.gec.obwiki.util.SnowFlake;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocServiceImpl extends ServiceImpl<DocMapper, Doc> implements IDocService {
    @Autowired
    private SnowFlake snowFlake;
    @Autowired
    private IContentService contentService;
    @Autowired
    private DocMapper docMapper;
    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public PageResp<DocQueryResp> list(DocQueryReq req) {
        QueryWrapper<Doc> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(req.getName()), "name", req.getName());
        Page<Doc> page = new Page<>(req.getPage(), req.getSize());
        IPage<Doc> pageResult = baseMapper.selectPage(page, queryWrapper);
        List<DocQueryResp> resps = CopyUtil.copyList(pageResult.getRecords(), DocQueryResp.class);
        PageResp<DocQueryResp> pageResp = new PageResp<>();
        pageResp.setList(resps);
        pageResp.setTotal(pageResult.getTotal());
        return pageResp;
    }

    @Override
    public List<DocQueryResp> all() {
        QueryWrapper<Doc> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        List<Doc> list = baseMapper.selectList(queryWrapper);
        return CopyUtil.copyList(list, DocQueryResp.class);
    }

    @Override
    public void save(DocSaveReq req) {
        Doc doc = CopyUtil.copy(req, Doc.class);
        Content content = new Content();
        content.setContent(req.getContent());

        if (req.getId() == null) {
            // 新增
            long id = snowFlake.nextId();
            doc.setId(id);
            doc.setViewCount(0);
            doc.setVoteCount(0);
            baseMapper.insert(doc);

            content.setId(id);
            contentService.save(content);
        } else {
            // 更新
            baseMapper.updateById(doc);
            content.setId(req.getId());
            contentService.updateById(content);
        }
    }

    @Override
    public void delete(Long id) {
        baseMapper.deleteById(id);
    }

    @Override
    public List<DocQueryResp> allByEbookId(Long ebookId) {
        QueryWrapper<Doc> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ebook_id", ebookId).orderByAsc("sort");
        List<Doc> list = baseMapper.selectList(queryWrapper);
        return CopyUtil.copyList(list, DocQueryResp.class);
    }

    @Override
    public String findContent(Long id) {
        docMapper.increaseViewCount(id);
        Content content = contentMapper.selectById(id);
        return content != null ? content.getContent() : "";
    }

    @Override
    public void vote(Long id) {
        String key = "DOC_VOTE_" + id + "_" + RequestContext.getRemoteAddr();
        if (redisUtil.validateRepeat(key, 3600 * 24)) {
            baseMapper.increaseVoteCount(id);
        } else {
            throw new BusinessException(BusinessExceptionCode.VOTE_REPEAT);
        }
    }

    @Override
    public void updateEbookInfo() {
        baseMapper.updateEbookInfo();
    }
} 