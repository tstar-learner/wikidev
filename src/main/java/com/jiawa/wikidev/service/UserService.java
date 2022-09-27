package com.jiawa.wikidev.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiawa.wikidev.domain.User;
import com.jiawa.wikidev.domain.UserExample;
import com.jiawa.wikidev.exception.BusinessException;
import com.jiawa.wikidev.exception.BusinessExceptionCode;
import com.jiawa.wikidev.mapper.UserMapper;
import com.jiawa.wikidev.req.UserQueryReq;
import com.jiawa.wikidev.req.UserResetPasswordReq;
import com.jiawa.wikidev.req.UserSaveReq;
import com.jiawa.wikidev.resp.UserQueryResp;
import com.jiawa.wikidev.resp.PageResp;
import com.jiawa.wikidev.util.CopyUtil;
import com.jiawa.wikidev.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserMapper userMapper;

    @Resource
    private SnowFlake snowFlake;

    public PageResp<UserQueryResp> list(UserQueryReq req) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (!ObjectUtils.isEmpty(req.getLoginName())) {
            criteria.andNameLike( req.getLoginName() );
        }
        PageHelper.startPage(req.getPage(), req.getSize());
        List<User> userList = userMapper.selectByExample(userExample);

        PageInfo<User> pageInfo = new PageInfo<>(userList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        // List<UserResp> respList = new ArrayList<>();
        // for (User user : userList) {
        //     // UserResp userResp = new UserResp();
        //     // BeanUtils.copyProperties(user, userResp);
        //     // 对象复制
        //     UserResp userResp = CopyUtil.copy(user, UserResp.class);
        //
        //     respList.add(userResp);
        // }

        // 列表复制
        List<UserQueryResp> list = CopyUtil.copyList(userList, UserQueryResp.class);

        PageResp<UserQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;
    }

    /**
     * 保存
     */
    public void save(UserSaveReq req) {
        User user = CopyUtil.copy(req, User.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增
            User userDB=selectByLoginName(req.getLoginName());
            if (ObjectUtils.isEmpty(userDB)){
                user.setId(snowFlake.nextId());
                userMapper.insert(user);
            }else{
                throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXTI);
            }
        } else {
            // 更新
            user.setLoginName(null);
            userMapper.updateByPrimaryKeySelective(user);
        }
    }

    /**
     * 重置密码
     */
    public void resetPassword(UserResetPasswordReq req) {
        User user = CopyUtil.copy(req, User.class);
        userMapper.updateByPrimaryKeySelective(user);
    }

    public User selectByLoginName(String loginName) {
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria=userExample.createCriteria();
        criteria.andLoginNameEqualTo(loginName);
        List<User> userList=userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(userList)){
            return null;
        }else {
            return userList.get(0);
        }
    }

    /**
     * 删除
     * @param id
     */
    public void delete(Long id){
        userMapper.deleteByPrimaryKey(id);
    }
}