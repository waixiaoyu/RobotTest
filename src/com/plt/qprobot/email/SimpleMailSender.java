package com.plt.qprobot.email;

import java.io.File;
import java.util.Date;
 
import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
 
/**
 * @ClassName: SimpleMailSender
 * @Description: TODO( 邮件发送主类)
 * @author A18ccms a18ccms_gmail_com
 * @date 2014年12月27日 上午9:52:43
 * 
 */
public class SimpleMailSender {
 
    /**
     * @Title: sendHtmlMail
     * @param @param mailInfo
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     */
    public boolean sendHtmlMail(MailSendInfo mailInfo) {
 
        MyAuthenticator authenticator = null;
        if (mailInfo.isValidate()) {
            authenticator = new MyAuthenticator(mailInfo.getUserName(),
                    mailInfo.getPassWord());// 用户名 密码
        }
        Session sendMailSession = Session.getDefaultInstance(
                mailInfo.getProperties(), authenticator);
        try {
            // 根据Session创建Message
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建发送者
            Address fromAddress = new InternetAddress(mailInfo.getFromAddess());
            // 设置发送者
            mailMessage.setFrom(fromAddress);
            // 创建接收者
            int len = mailInfo.getToAddress().length;
            Address[] toAddress = new InternetAddress[len];
            for (int i = 0; i < len; i++) {
                toAddress[i] = new InternetAddress(mailInfo.getToAddress()[i]);
            }
            // 设置接收者 发送
            mailMessage.setRecipients(Message.RecipientType.TO, toAddress);
            // 设置发送主题
            mailMessage.setSubject(mailInfo.getSubject());
            // 设置发送时间
            mailMessage.setSentDate(new Date());
            // 内容发送
            // 创建一个容器类
            Multipart mailPart = new MimeMultipart();
            // 创建一个包含html内容的BodyPart
            BodyPart html = new MimeBodyPart();
            // 设置html内容
            html.setContent(mailInfo.getContent(), "text/html;charset=utf-8");
            mailPart.addBodyPart(html);
            mailMessage.setContent(mailPart);
            // 发送邮件
            MailcapCommandMap mc = (MailcapCommandMap) CommandMap
                    .getDefaultCommandMap();
            mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
            mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
            mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
            mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
            mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
            CommandMap.setDefaultCommandMap(mc);
            // 现行判断是否存在附件
            if (mailInfo.getFile_path() != null) {
                // 设置附件
                BodyPart attach = new MimeBodyPart();
                DataSource source = new FileDataSource(mailInfo.getFile_path());// 附件的路径
                attach.setDataHandler(new DataHandler(source));
                attach.setFileName(new File(mailInfo.getFile_path()).getName());
                mailPart.addBodyPart(attach);
            }
            // 发送
            Transport.send(mailMessage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}