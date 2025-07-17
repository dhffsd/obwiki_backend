package com.gec.obwiki.controller;

import com.gec.obwiki.req.DocQueryReq;
import com.gec.obwiki.req.DocSaveReq;
import com.gec.obwiki.resp.DocQueryResp;
import com.gec.obwiki.resp.CommonResp;
import com.gec.obwiki.resp.PageResp;
import com.gec.obwiki.service.IDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/doc")
public class DocController {
    @Autowired
    private IDocService docService;

    // 只保留如下方法，删除/注释其他 /all 相关接口
    @GetMapping("/all")
    public CommonResp<List<DocQueryResp>> allByEbookId(@RequestParam Long ebookId) {
        List<DocQueryResp> list = docService.allByEbookId(ebookId);
        return new CommonResp<>(true, "查询成功", list);
    }

    @GetMapping("/list")
    public CommonResp<PageResp<DocQueryResp>> list(@Valid DocQueryReq req) {
        PageResp<DocQueryResp> pageResp = docService.list(req);
        return new CommonResp<>(true, "查询成功", pageResp);
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody DocSaveReq req) {
        docService.save(req);
        return new CommonResp<>(true, "操作成功", null);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        docService.delete(id);
        return new CommonResp<>(true, "删除成功", null);
    }
} 