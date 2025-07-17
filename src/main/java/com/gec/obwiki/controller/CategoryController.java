package com.gec.obwiki.controller;

import com.gec.obwiki.req.CategoryQueryReq;
import com.gec.obwiki.req.CategorySaveReq;
import com.gec.obwiki.resp.CategoryQueryResp;
import com.gec.obwiki.resp.CommonResp;
import com.gec.obwiki.resp.PageResp;
import com.gec.obwiki.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/list")
    public CommonResp<PageResp<CategoryQueryResp>> list(@Valid CategoryQueryReq req) {
        PageResp<CategoryQueryResp> pageResp = categoryService.list(req);
        return new CommonResp<>(true, "查询成功", pageResp);
    }

    @GetMapping("/all")
    public CommonResp<List<CategoryQueryResp>> all() {
        List<CategoryQueryResp> list = categoryService.all();
        return new CommonResp<>(true, "查询成功", list);
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody CategorySaveReq req) {
        categoryService.save(req);
        return new CommonResp<>(true, "操作成功", null);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        categoryService.delete(id);
        return new CommonResp<>(true, "删除成功", null);
    }
} 