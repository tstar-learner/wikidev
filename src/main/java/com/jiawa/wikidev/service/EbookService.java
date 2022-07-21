package com.jiawa.wikidev.service;

import com.jiawa.wikidev.domain.Ebook;
import com.jiawa.wikidev.domain.EbookExample;
import com.jiawa.wikidev.mapper.EbookMapper;
import com.jiawa.wikidev.req.EbookReq;
import com.jiawa.wikidev.resp.CommonResp;
import com.jiawa.wikidev.resp.EbookResp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {

    @Resource
    public EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq req){
        EbookExample example=new EbookExample();
        EbookExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%"+req.getName()+"%");
        List<Ebook> ebookList = ebookMapper.selectByExample(example);

        List<EbookResp> ebookResps=new ArrayList<>();
        for (Ebook e :
                ebookList) {
            EbookResp ebookResp=new EbookResp();
            BeanUtils.copyProperties(e,ebookResp);
            ebookResps.add(ebookResp);
        }
        return ebookResps;
    }
}
