package com.javaclimb.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.javaclimb.music.domain.Admin;
import com.javaclimb.music.domain.User;
import com.javaclimb.music.service.UserService;
import com.javaclimb.music.util.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value="/user/ifExist",method = RequestMethod.GET)
    public Object uploaderOfStudyNumber(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        String name=request.getParameter("userName").trim();
        int result=userService.ifExist(name);
        if(result==0){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"可以注册");
        }
        else {
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"用户已存在");
        }
        return jsonObject;
    }
    @RequestMapping(value = "/user/signUp",method = RequestMethod.POST)
    public Object addUser(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        String name=request.getParameter("username").trim();
        String password=request.getParameter("password").trim();
        String email=request.getParameter("email").trim();

        //保存到上传者对象中
       User user=new User();
        user.setUserName(name);
        user.setPassword(password);
        user.setEmail(email);
        boolean flag=userService.insert(user);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"注册成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"注册失败");
        return jsonObject;
    }

}
