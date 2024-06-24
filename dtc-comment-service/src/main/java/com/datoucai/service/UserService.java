package com.datoucai.service;

import com.datoucai.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService
 */
@Service
public class UserService implements IUserService{

    @Autowired
    private UserMapper userMapper;
    @Override
    public int countUser() {
        return userMapper.countUser();
    }
}
