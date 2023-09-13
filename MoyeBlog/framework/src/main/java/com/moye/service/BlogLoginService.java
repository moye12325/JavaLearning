package com.moye.service;

import com.moye.domain.ResponseResult;
import com.moye.domain.entity.User;

public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
