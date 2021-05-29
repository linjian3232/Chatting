package com.javaclimb.music;

import com.javaclimb.music.service.ActiveMQService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActiveMQServiceTest {
    @Autowired
    ActiveMQService activeMQService;
    private String[] mailTo=new String[]{"scalpellin@qq.com"};
    @Test
    public  void testSimpleMail(){
        activeMQService.sendMQ(mailTo,"纯文本邮件","你好，这是一封测试邮件");
    }
}
