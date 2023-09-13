package com.moye.controller;

import com.moye.domain.ResponseResult;
import com.moye.domain.entity.User;
import com.moye.enums.AppHttpCodeEnum;
import com.moye.exception.SystemException;
import com.moye.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlogLoginController {
    @Autowired
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    //ResponseResult是framework工程里面写的实体类
    public ResponseResult login(@RequestBody User user) {
        if (!StringUtils.hasText(user.getUserName())) {
//            提示必须填写用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }

        return blogLoginService.login(user);
    }

    @PostMapping("/logout")
    public ResponseResult logout(){
        return blogLoginService.logout();
    }


}
