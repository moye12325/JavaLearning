package com.moye.service.impl;

import com.moye.domain.ResponseResult;
import com.moye.domain.entity.LoginUser;
import com.moye.domain.entity.User;
import com.moye.domain.vo.BlogUserLoginVo;
import com.moye.domain.vo.UserInfoVo;
import com.moye.service.BlogLoginService;
import com.moye.utils.BeanCopyUtils;
import com.moye.utils.JwtUtil;
import com.moye.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authentication)) {
            throw new RuntimeException("用户名或密码错误");
        }

//        获取UserID生成token
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
//        把用户信息放入Redis
        redisCache.setCacheObject("bloglogin:" + userId, loginUser);

//        把token和userinfo封装返回
//        把User转换为UserInfo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        BlogUserLoginVo vo =new BlogUserLoginVo(jwt,userInfoVo);

        return ResponseResult.okResult(vo);
    }
}
