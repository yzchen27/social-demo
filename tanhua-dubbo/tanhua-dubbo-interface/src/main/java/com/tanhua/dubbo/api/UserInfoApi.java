package com.tanhua.dubbo.api;

import com.tanhua.model.bo.UserInfoBO;
import com.tanhua.model.domain.UserInfo;
import com.tanhua.model.vo.UserInfoVO;

import java.util.List;

/**
 * @program: social-demo
 * @description: 用户信息
 * @author: YzChen
 * @create: 2022-04-12 10:25
 **/
public interface UserInfoApi {

    /**
     *  用户首次登录信息
     * @param userInfoVO
     * @param userInfoBO
     */
    void save(UserInfoVO userInfoVO, UserInfoBO userInfoBO);

    /**
     *  根据id查找用户信息
     * @param userID
     * @return
     */
    UserInfoVO findUserInfoById(Long userID);

    UserInfo findUserInfoByUserId(Long userId);

    /**
     *  更新用户信息
     * @param userInfo
     */
    void updateUserInfo(UserInfo userInfo);

    List<UserInfo> batchSelectByUserIdList(List<Long> collect);
}
