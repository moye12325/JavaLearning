package com.moye.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moye.domain.entity.User;
import com.moye.mapper.UserMapper;
import com.moye.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author moye12325
 * @since 2023-09-14 20:24:51
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
