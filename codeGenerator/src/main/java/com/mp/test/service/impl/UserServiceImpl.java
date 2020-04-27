package com.mp.test.service.impl;

import com.mp.common.base.dao.mybatis.BaseMapper;
import com.mp.common.base.service.impl.BaseMybatisServiceImpl;
import com.mp.test.entity.User;
import com.mp.test.mapper.UserMapper;
import com.mp.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @date 2019-12-21 11:25:32
 **/
@Service
public class UserServiceImpl extends BaseMybatisServiceImpl<User,Long> implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    protected BaseMapper<User, Long> getBaseMapper() {
        return userMapper;
    }
}
