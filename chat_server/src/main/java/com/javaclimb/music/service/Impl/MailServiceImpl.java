package com.javaclimb.music.service.Impl;
import com.javaclimb.music.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailServiceImpl implements MailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JavaMailSender mailSender;//注入JavaMailSender，具体发送工作需要它完成

    @Value("${spring.mail.username}")//从配置文件中获取发件人邮箱
    public String from;

    /**
     * 发送普通文本邮件
     */
    @Override
    public void sendSimpleMail(String to, String subject, String context){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);//发件人
        mailMessage.setTo(to);//收件人
        mailMessage.setSubject(subject);//邮件主题
        mailMessage.setText(context);//邮件正文

        mailSender.send(mailMessage);//发送邮件
        logger.info("邮件发送成功");
    }

    /**
     * 发送HTML邮件
     */
    @Override
    public void sendMimeMail(String to, String subject, String context){
        MimeMessage mailMessage = mailSender.createMimeMessage();
        try{//发送非纯文本的邮件都需要用的helper来解析
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage);
            helper.setFrom(from);
            helper.setTo(to);
//            helper.setBcc("xxxx@qq.com");//抄送人
            helper.setSubject(subject);
            helper.setText(context,true);//这里的第二个参数要为true才会解析html内容
            mailSender.send(mailMessage);
            logger.info("邮件发送成功");
        } catch(Exception ex){
            logger.error("邮件发送失败",ex);
        }
    }

    /**
     * 发送带附件的邮件
     */
    @Override
    public void sendAttachMail(String[] to, String subject, String context, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(context);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            helper.addAttachment(file.getFilename(),file);//添加附件，需要用到FileStstemResource

            mailSender.send(message);
            logger.info("带邮件的附件发送成功");
        }catch(Exception ex){
            logger.error("带附件的邮件发送失败",ex);
        }
    }

    /**
     * 发送正文带图片的邮件
     */
    @Override
    public void sendInlineMail(String to, String subject, String context, String filePath, String resId) {
        MimeMessage message = mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(context,true);

            FileSystemResource res = new FileSystemResource(new File(filePath));
            helper.addInline(resId, res);
            mailSender.send(message);
            logger.info("邮件发送成功");
        } catch (Exception ex){
            logger.error("邮件发送失败",ex);
        }
    }

    @Override
    public void sendMail(String[] to, String subject, String context, String filePath, String resId ) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(context, true);
            if (!StringUtils.isEmpty(filePath) && !StringUtils.isEmpty(resId)) {//文件路径和resId都不为空，视为静态图片
                FileSystemResource resource = new FileSystemResource(new File(filePath));
                helper.addInline(resId, resource);
            } else if (!StringUtils.isEmpty(filePath)) {//只有文件路径不为空，视为附件
                FileSystemResource resource = new FileSystemResource(new File(filePath));
                helper.addAttachment(resource.getFilename(), resource);
            }

            mailSender.send(message);
            logger.info("邮件发送成功");
        } catch (Exception ex) {
            logger.error("邮件发送错误:", ex);
        }
    }

}