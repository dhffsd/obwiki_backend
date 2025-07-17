package com.gec.obwiki.controller;

import com.gec.obwiki.entity.Ebook;
import com.gec.obwiki.resp.CommonResp;
import com.gec.obwiki.resp.PageResp;
import com.gec.obwiki.service.IEbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Autowired
    private IEbookService ebookService;

    @GetMapping("/list")
    public CommonResp<PageResp<Ebook>> list(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String categoryIds
    ) {
        PageResp<Ebook> resp = ebookService.list(page, size, name, categoryIds);
        CommonResp<PageResp<Ebook>> result = new CommonResp<>();
        result.setContent(resp);
        return result;
    }

    @PostMapping("/save")
    public CommonResp save(@RequestBody Ebook ebook) {
        ebookService.saveOrUpdate(ebook);
        CommonResp result = new CommonResp();
        result.setSuccess(true);
        result.setMessage("保存成功");
        return result;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable String id) {
        System.out.println("收到删除请求，id=" + id + ", 类型=" + (id == null ? "null" : id.getClass().getName()));
        ebookService.removeById(Long.valueOf(id));
        CommonResp result = new CommonResp();
        result.setSuccess(true);
        result.setMessage("删除成功");
        return result;
    }

    @GetMapping("/find/{id}")
    public CommonResp<Ebook> findById(@PathVariable Long id) {
        Ebook ebook = ebookService.getById(id);
        CommonResp<Ebook> result = new CommonResp<>();
        result.setContent(ebook);
        return result;
    }

    @PostMapping("/vote/{id}")
    public CommonResp<Integer> vote(@PathVariable String id) {
        int voteCount = ebookService.vote(Long.valueOf(id));
        return new CommonResp<>(true, "点赞成功", voteCount);
    }
} 