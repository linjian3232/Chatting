package com.javaclimb.music.service.Impl;


import com.alibaba.fastjson.JSON;
import com.javaclimb.music.domain.MailBean;
import com.javaclimb.music.service.ActiveMQService;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.ConnectionFactory;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ActiveMQServiceImpl implements ActiveMQService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String verifyCode="";
    @Bean
    ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory();
    }

    @Bean
    JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setPriority(999);
        return jmsTemplate;
    }

    @Autowired
    JmsTemplate template;

    @Value("${com.sam.mail.queue}")
    private String queueName;

    @Override
    public int sendMQ(String[] to, String subject, String content) {
        try{
        this.sendMQ(to,subject,content,null);
        return 1;
        }catch (Exception e){
            System.out.println(e.toString());
            return 0;
        }
    }

    @Override
    public int sendMQ(String[] to, String subject, String content, String filePath) {
        try{
        this.sendMQ(to,subject,content,filePath,null);
        return 1;
        }catch (Exception e){
            System.out.println(e.toString());
            return 0;
        }
    }

    @Override
    public int sendMQ(String[] to, String subject, String content, String filePath, String srcId) {
        try {
            MailBean bean = new MailBean();
            bean.setTo(to);
            bean.setSubject(subject);
            bean.setContent(content);
            bean.setFilePath(filePath);
            bean.setSrcId(srcId);
            template.convertAndSend(queueName, bean);
            logger.info("邮件已经发送到MQ:" + JSON.toJSONString(bean));
            return 1;
        }catch (Exception e){
            System.out.println(e.toString());
            return 0;
        }

    }
    @Override
    public void setVerifyNumbers() {
        String[] beforeShuffle = new String[] {
                "0","1", "2", "3", "4", "5", "6", "7", "8", "9",
                "a","b", "d", "c", "e", "f", "g", "h", "i", "j",
                "0","1", "2", "3", "4", "5", "6", "7", "8", "9",
                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v","w", "x", "y", "z" };
        List list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        this.verifyCode = afterShuffle.substring(3, 9);
    }

    @Override
    public String getVerifyCode() {
        return this.verifyCode;
    }

}
