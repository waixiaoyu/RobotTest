package com.plt.qprobot.email;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Mail {
	private static Logger Log = Logger.getLogger(Mail.class);

	public static void main(String[] args) {
		Mail.send();
	}

	public static void send() {
		Properties prop = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "\\mail.properties");
			prop.load(fis);
		} catch (IOException e) {
			Log.error(e);
		}

		MailSendInfo sendInfo = new MailSendInfo();
		sendInfo.setMailServerHost(prop.getProperty("ServerHost"));
		sendInfo.setMailServerPort(prop.getProperty("ServerPort"));
		sendInfo.setValidate(prop.getProperty("Validate").equals("true") ? true : false);
		sendInfo.setUserName(prop.getProperty("UserName"));// 发送者用户名
		sendInfo.setPassWord(prop.getProperty("PassWord"));// 发送者密码
		sendInfo.setFromAddess(prop.getProperty("FromAddess"));// 发送者的邮箱

		String[] toAddress = prop.getProperty("toAddress").split("\\|");// 接收者的一组邮箱
		sendInfo.setToAddress(toAddress);

		sendInfo.setSubject("程序出错");
		sendInfo.setContent("错误信息.");
		sendInfo.setFile_path(prop.getProperty("infopath"));

		SimpleMailSender sender = new SimpleMailSender();
		boolean b = sender.sendHtmlMail(sendInfo);
		if (b) {
			Log.info("错误邮件发送成功！");
		} else {
			Log.error("错误邮件发送失败！");
		}
	}

}
