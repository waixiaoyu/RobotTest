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
 * @Description: TODO( �ʼ���������)
 * @author A18ccms a18ccms_gmail_com
 * @date 2014��12��27�� ����9:52:43
 * 
 */
public class SimpleMailSender {
 
    /**
     * @Title: sendHtmlMail
     * @param @param mailInfo
     * @param @return �趨�ļ�
     * @return boolean ��������
     * @throws
     */
    public boolean sendHtmlMail(MailSendInfo mailInfo) {
 
        MyAuthenticator authenticator = null;
        if (mailInfo.isValidate()) {
            authenticator = new MyAuthenticator(mailInfo.getUserName(),
                    mailInfo.getPassWord());// �û��� ����
        }
        Session sendMailSession = Session.getDefaultInstance(
                mailInfo.getProperties(), authenticator);
        try {
            // ����Session����Message
            Message mailMessage = new MimeMessage(sendMailSession);
            // ����������
            Address fromAddress = new InternetAddress(mailInfo.getFromAddess());
            // ���÷�����
            mailMessage.setFrom(fromAddress);
            // ����������
            int len = mailInfo.getToAddress().length;
            Address[] toAddress = new InternetAddress[len];
            for (int i = 0; i < len; i++) {
                toAddress[i] = new InternetAddress(mailInfo.getToAddress()[i]);
            }
            // ���ý����� ����
            mailMessage.setRecipients(Message.RecipientType.TO, toAddress);
            // ���÷�������
            mailMessage.setSubject(mailInfo.getSubject());
            // ���÷���ʱ��
            mailMessage.setSentDate(new Date());
            // ���ݷ���
            // ����һ��������
            Multipart mailPart = new MimeMultipart();
            // ����һ������html���ݵ�BodyPart
            BodyPart html = new MimeBodyPart();
            // ����html����
            html.setContent(mailInfo.getContent(), "text/html;charset=utf-8");
            mailPart.addBodyPart(html);
            mailMessage.setContent(mailPart);
            // �����ʼ�
            MailcapCommandMap mc = (MailcapCommandMap) CommandMap
                    .getDefaultCommandMap();
            mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
            mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
            mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
            mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
            mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
            CommandMap.setDefaultCommandMap(mc);
            // �����ж��Ƿ���ڸ���
            if (mailInfo.getFile_path() != null) {
                // ���ø���
                BodyPart attach = new MimeBodyPart();
                DataSource source = new FileDataSource(mailInfo.getFile_path());// ������·��
                attach.setDataHandler(new DataHandler(source));
                attach.setFileName(new File(mailInfo.getFile_path()).getName());
                mailPart.addBodyPart(attach);
            }
            // ����
            Transport.send(mailMessage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}