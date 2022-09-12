package com.jiawa.wikidev.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiawa.wikidev.domain.Content;
import com.jiawa.wikidev.domain.Doc;
import com.jiawa.wikidev.domain.DocExample;
import com.jiawa.wikidev.mapper.ContentMapper;
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
    private ContentMapper contentMapper;

    @Resource
    private SnowFlake snowFlake;

    public List<DocQueryResp> all(Long ebookId){
        DocExample docExample=new DocExample();
        docExample.createCriteria().andEbookIdEqualTo(ebookId);
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
        Content content=CopyUtil.copy(req,Content.class);
        if (ObjectUtils.isEmpty(req.getId())){
            //新增
            doc.setId(snowFlake.nextId());
            content.setId(doc.getId());
            contentMapper.insert(content);
            docMapper.insert(doc);
        }else {
            //更新
            docMapper.updateByPrimaryKey(doc);
            int count = contentMapper.updateByPrimaryKeyWithBLOBs(content);
            if (count==0){
                contentMapper.insert(content);
            }
        }
    }

    /**
     * 删除
     */
    public void delete(Long id){
        docMapper.deleteByPrimaryKey(id);
    }

    /**
     * 删除
     */
    public void delete(List<String> ids){
        DocExample  docExample=new DocExample();
        DocExample.Criteria criteria=docExample.createCriteria();
        criteria.andIdIn(ids);
        docMapper.deleteByExample(docExample);
    }

    /**
     * 显示内容
     */
    public String findContent(Long id){
        Content content = contentMapper.selectByPrimaryKey(id);
        return content.getContent();
    }
}
