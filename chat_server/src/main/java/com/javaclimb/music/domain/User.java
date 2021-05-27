package com.javaclimb.music.domain;

import java.io.Serializable;

//Serializable实现前后端的传输
public class User implements Serializable {
    //    主键
    private Integer id;
    //    用户名
    private String userName;
    //    密码
    private String password;
    //    邮箱
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }





}
