package com.javaclimb.music.service.Impl;

import com.javaclimb.music.dao.UserMapper;
import com.javaclimb.music.domain.User;
import com.javaclimb.music.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员sevice实现类 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int ifExist(String username) {
        return userMapper.ifExist(username);
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public boolean insert(User user) {
        return userMapper.insert(user)>0;
    }

    @Override
    public boolean update(User user) {
        return userMapper.update(user)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return userMapper.delete(id)>0;
    }
}
