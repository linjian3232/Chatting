package com.javaclimb.music.dao;

import com.javaclimb.music.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户Dao
 */
@Repository
public interface UserMapper {

    /**
     * 新增
     * @param user
     * @return
     */
    public int insert(User user);

    /**
     * 修改
     */
    public int update(User user);

    /**
     * 删除
     */
    public int delete(Integer id);

    /**
     * 查询所有用户
     * @return
     */
    public List<User> getAllUser();

    /**
     * 根据用户名判断是否存在
     * @param userName
     * @return
     */
    public int ifExist(String userName);

}
