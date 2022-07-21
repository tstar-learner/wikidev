package com.jiawa.wikidev.controller;

import com.jiawa.wikidev.domain.Ebook;
import com.jiawa.wikidev.req.EbookReq;
import com.jiawa.wikidev.resp.CommonResp;
import com.jiawa.wikidev.resp.EbookResp;
import com.jiawa.wikidev.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class EbookController {

    @Resource
    public EbookService ebookService;

    @GetMapping("/ebook/list")
    public CommonResp list(EbookReq req){
        CommonResp<List<EbookResp>> commonResp=new CommonResp<>();
        List<EbookResp> res=ebookService.list(req);
        commonResp.setContent(res);
        return commonResp;
    }
}
