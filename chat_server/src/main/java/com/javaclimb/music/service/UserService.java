package com.javaclimb.music.service;

import com.javaclimb.music.domain.User;
import com.javaclimb.music.domain.User;

import java.util.List;

/**
 * 管理员service接口
 */
public interface UserService {
    public int ifExist(String username);
    public List<User> getAllUser();
    public boolean insert(User user);
    public boolean update(User user);
    public boolean delete(Integer id);
}
