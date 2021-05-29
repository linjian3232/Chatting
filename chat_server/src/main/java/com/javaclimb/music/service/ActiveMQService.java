package com.javaclimb.music.service;

import com.alibaba.fastjson.JSON;

public interface ActiveMQService {

    public int sendMQ(String[] to, String subject, String content) ;
    public int sendMQ(String[] to, String subject, String content, String filePath) ;
    public int sendMQ(String[] to, String subject, String content, String filePath, String srcId);
    public void setVerifyNumbers();
    public String getVerifyCode();
}
