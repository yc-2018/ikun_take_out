package org.ikun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.ikun.entity.User;
import org.ikun.mapper.UserMapper;
import org.ikun.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
