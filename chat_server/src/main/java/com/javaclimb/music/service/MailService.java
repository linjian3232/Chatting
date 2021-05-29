package com.javaclimb.music.service;

import org.springframework.core.io.FileSystemResource;

import java.io.File;

public interface MailService {
    public void sendSimpleMail(String to, String subject, String context);

    /**
     * 发送HTML邮件
     */

    public void sendMimeMail(String to, String subject, String context);


    /**
     * 发送带附件的邮件
     */
    public void sendAttachMail(String[] to, String subject, String context, String filePath);

    public void sendMail(String[] to, String subject, String context, String filePath, String resId );

    /**
     * 发送正文带图片的邮件
     */
    public void sendInlineMail(String to, String subject, String context, String filePath, String resId);
}
