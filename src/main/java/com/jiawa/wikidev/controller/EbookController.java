package com.jiawa.wikidev.controller;


import com.jiawa.wikidev.service.EbookService;
import com.jiawa.wikidev.req.EbookQueryReq;
import com.jiawa.wikidev.req.EbookSaveReq;
import com.jiawa.wikidev.resp.CommonResp;
import com.jiawa.wikidev.resp.EbookQueryResp;
import com.jiawa.wikidev.resp.PageResp;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp list(EbookQueryReq req) {
        CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>();
        PageResp<EbookQueryResp> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@RequestBody EbookSaveReq req) {
        CommonResp resp = new CommonResp<>();
        ebookService.save(req);
        return resp;
    }
}
