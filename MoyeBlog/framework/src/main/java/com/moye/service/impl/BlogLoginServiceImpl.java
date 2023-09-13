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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseResult logout() {
        //获取token，然后解析token值获取其中的userid。SecurityContextHolder是security官方提供的类
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //LoginUser是我们写的类
        //获取userid
        Long userId = loginUser.getUser().getId();
        //在redis根据key来删除用户的value值，注意之前我们在存key的时候，key是加了'bloglogin:'前缀
        redisCache.deleteObject("bloglogin:" + userId);
        //封装响应返回
        return ResponseResult.okResult();

    }


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
        BlogUserLoginVo vo = new BlogUserLoginVo(jwt, userInfoVo);

        return ResponseResult.okResult(vo);
    }
}
