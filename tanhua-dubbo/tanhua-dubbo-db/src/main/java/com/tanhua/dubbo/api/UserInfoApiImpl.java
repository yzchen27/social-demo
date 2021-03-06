package com.tanhua.dubbo.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tanhua.dubbo.mappers.UserInfoMapper;
import com.tanhua.model.domain.UserInfo;
import com.tanhua.model.bo.UserInfoBO;
import com.tanhua.model.vo.UserInfoVO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @program: social-demo
 * @description: 用户信息实现类
 * @author: YzChen
 * @create: 2022-04-12 10:31
 **/
@DubboService
public class UserInfoApiImpl implements UserInfoApi{

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void save(UserInfoVO userInfoVO, UserInfoBO userInfoBO) {
        UserInfo userInfo = userInfoMapper.selectOne(new QueryWrapper<UserInfo>().lambda().eq(UserInfo::getId, userInfoBO.getId()));
        if (userInfo == null){
            UserInfo newUserInfo = new UserInfo();
            BeanUtils.copyProperties(userInfoVO, newUserInfo);
            BeanUtils.copyProperties(userInfoBO, newUserInfo);
            userInfoMapper.insert(newUserInfo);
        }else {
            BeanUtils.copyProperties(userInfoVO, userInfo);
            BeanUtils.copyProperties(userInfoBO, userInfo);
            userInfoMapper.updateById(userInfo);
        }
    }

    @Override
    public UserInfoVO findUserInfoById(Long userID) {
        UserInfo userInfo = userInfoMapper.selectOne(new QueryWrapper<UserInfo>().lambda().eq(UserInfo::getId, userID));
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(userInfo, userInfoVO);
        return userInfoVO;
    }

    @Override
    public UserInfo findUserInfoByUserId(Long userId) {
        return userInfoMapper.selectOne(new QueryWrapper<UserInfo>().lambda().eq(UserInfo::getId, userId));
    }

    @Override
    public void updateUserInfo(UserInfo userInfo) {
        userInfoMapper.updateById(userInfo);
    }

    @Override
    public List<UserInfo> batchSelectByUserIdList(List<Long> collect) {
        return userInfoMapper.selectBatchIds(collect);
    }
}
