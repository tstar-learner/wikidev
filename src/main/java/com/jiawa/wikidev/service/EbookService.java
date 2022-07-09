package com.jiawa.wikidev.service;

import com.jiawa.wikidev.domain.Ebook;
import com.jiawa.wikidev.mapper.EbookMapper;
import com.jiawa.wikidev.resp.CommonResp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    @Resource
    public EbookMapper ebookMapper;

    public List<Ebook> list(){
        return ebookMapper.selectByExample(null);
    }
}
