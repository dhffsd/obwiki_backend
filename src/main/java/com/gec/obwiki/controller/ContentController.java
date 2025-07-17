package com.gec.obwiki.controller;

import com.gec.obwiki.resp.CommonResp;
import com.gec.obwiki.service.IContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private IContentService contentService;

    @GetMapping("/findContent/{id}")
    public CommonResp<String> findContent(@PathVariable Long id) {
        String content = contentService.findContentById(id);
        CommonResp<String> resp = new CommonResp<>();
        resp.setContent(content);
        return resp;
    }
} 