package com.jiawa.wikidev.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiawa.wikidev.domain.Ebook;
import com.jiawa.wikidev.domain.EbookExample;
import com.jiawa.wikidev.mapper.EbookMapper;
import com.jiawa.wikidev.req.EbookReq;
import com.jiawa.wikidev.resp.CommonResp;
import com.jiawa.wikidev.resp.EbookResp;
import com.jiawa.wikidev.resp.PageResp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.jiawa.wikidev.util.CopyUtil.copyList;

@Service
public class EbookService {

    @Resource
    public EbookMapper ebookMapper;

    public PageResp<EbookResp> list(EbookReq req){
        EbookExample example=new EbookExample();
        EbookExample.Criteria criteria = example.createCriteria();
        if (!ObjectUtils.isEmpty(req.getName())){
            criteria.andNameLike("%"+req.getName()+"%");
        }
        PageHelper.startPage(req.getStart(), req.getSize());
        List<Ebook> ebookList = ebookMapper.selectByExample(example);


//        List<EbookResp> ebookResps=new ArrayList<>();
//        for (Ebook e :
//                ebookList) {
//            EbookResp ebookResp=new EbookResp();
//            BeanUtils.copyProperties(e,ebookResp);
//            ebookResps.add(ebookResp);
//        }

        List<EbookResp> list = copyList(ebookList, EbookResp.class);

        PageInfo<Ebook> pageInfo=new PageInfo<Ebook>(ebookList);

        PageResp<EbookResp> pageResp=new PageResp<>();
        pageResp.setList(list);
        pageResp.setTotal(pageInfo.getTotal());
        return pageResp;
    }
}
