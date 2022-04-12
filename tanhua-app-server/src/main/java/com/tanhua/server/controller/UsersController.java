package com.tanhua.server.controller;

import com.tanhua.commons.utils.JwtUtils;
import com.tanhua.model.bo.UserInfoBO;
import com.tanhua.model.domain.UserInfo;
import com.tanhua.model.vo.UserInfoVO;
import com.tanhua.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @program: social-demo
 * @description: 用户信息Controller
 * @author: YzChen
 * @create: 2022-04-12 14:51
 **/
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity getUserInfo(@RequestHeader("Authorization") String token,Long userID){
        UserInfoBO userInfoBO = JwtUtils.getClaims(token);
        UserInfoVO userInfo = userService.findUserInfoById(userInfoBO, userID);
        return ResponseEntity.ok(userInfo);
    }
}