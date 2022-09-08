package com.jiawa.wikidev.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiawa.wikidev.domain.Doc;
import com.jiawa.wikidev.domain.DocExample;
import com.jiawa.wikidev.mapper.DocMapper;
import com.jiawa.wikidev.req.DocQueryReq;
import com.jiawa.wikidev.req.DocSaveReq;
import com.jiawa.wikidev.resp.DocQueryResp;
import com.jiawa.wikidev.resp.PageResp;
import com.jiawa.wikidev.util.CopyUtil;
import com.jiawa.wikidev.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DocService {
    private static final Logger LOG= LoggerFactory.getLogger(DocService.class);

    @Resource
    private DocMapper docMapper;

    @Resource
    private SnowFlake snowFlake;

    public List<DocQueryResp> all(){
        DocExample docExample=new DocExample();
        docExample.setOrderByClause("sort asc");
        List<Doc> docList=docMapper.selectByExample(docExample);

        //列表复制
        List<DocQueryResp> list=CopyUtil.copyList(docList,DocQueryResp.class);
        return list;
    }

    public PageResp<DocQueryResp> list(DocQueryReq req){
        DocExample  docExample=new DocExample();
        DocExample.Criteria criteria=docExample.createCriteria();
        PageHelper.startPage(req.getPage(),req.getSize());
        List<Doc> docList=docMapper.selectByExample(docExample);

        PageInfo<Doc> pageInfo=new PageInfo<>(docList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        //列表复制
        List<DocQueryResp> list= CopyUtil.copyList(docList,DocQueryResp.class);

        PageResp<DocQueryResp> pageResp=new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;
    }

    /**
     * 保存
     */
    public void save(DocSaveReq req){
        Doc doc=CopyUtil.copy(req,Doc.class);
        if (ObjectUtils.isEmpty(req.getId())){
            //新增
            doc.setId(snowFlake.nextId());
            docMapper.insert(doc);
        }else {
            //更新
            docMapper.updateByPrimaryKey(doc);
        }
    }

    /**
     * 删除
     */
    public void delete(Long id){
        docMapper.deleteByPrimaryKey(id);
    }
}
