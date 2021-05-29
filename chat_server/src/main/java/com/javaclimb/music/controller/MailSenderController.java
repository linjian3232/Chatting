package com.javaclimb.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.javaclimb.music.service.ActiveMQService;
import com.javaclimb.music.util.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author JAVA开发老菜鸟
 */

@RestController
public class MailSenderController {

    @Autowired
    ActiveMQService activeMQService;

    @Value("${mail.to}")
    private String mailTo;

    @RequestMapping(value="/sendSimpleMail",method = RequestMethod.GET)
    public Object sendSimpleMail(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        String[] email={request.getParameter("email").trim()};
        String username=request.getParameter("username").trim();
        String subject = "GoChatting注册信息";
        activeMQService.setVerifyNumbers();
        String verifyCode= activeMQService.getVerifyCode();
        String context = "亲爱的"+username+"，您的验证码为："+verifyCode+"\n  很期待您成为GoChatting的一员，希望您能有不一样的体验！";
        int result =  activeMQService.sendMQ(email, subject, context);
        if(result==1){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"邮件已发送");
        }
        else if(result==0){
            System.out.println("注册邮件："+email);
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"邮件发送失败");
        }
        return jsonObject;
    }

    @RequestMapping("/sendAttachMail")
    public void sendAttachMail(){
        String[] to = {mailTo};
        String subject = "带附件的邮件";
        String context = "<html><body>你好，<br>这是一封带附件的邮件，<br>具体请见附件</body></html>";
        String filePath = "D:\\1.jpg";
        activeMQService.sendMQ(to, subject, context, filePath);
    }

    @RequestMapping("/sendMimeMail")
    public void sendMimeMail(){
        String[] to = {mailTo};
        String subject = "普通邮件";

        String filePath = "D:\\1.jpg";
        String resId = "1.jpg";
        String context = "<html><body>你好，<br>这是一封带图片的邮件，<br>请见图片<br><img src=\'cid:"+resId+"\'></body></html>";
        activeMQService.sendMQ(to, subject, context, filePath, resId);
    }

}