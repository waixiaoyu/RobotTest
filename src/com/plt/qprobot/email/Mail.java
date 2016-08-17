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
		sendInfo.setUserName(prop.getProperty("UserName"));// �������û���
		sendInfo.setPassWord(prop.getProperty("PassWord"));// ����������
		sendInfo.setFromAddess(prop.getProperty("FromAddess"));// �����ߵ�����

		String[] toAddress = prop.getProperty("toAddress").split("\\|");// �����ߵ�һ������
		sendInfo.setToAddress(toAddress);

		sendInfo.setSubject("�������");
		sendInfo.setContent("������Ϣ.");
		sendInfo.setFile_path(prop.getProperty("infopath"));

		SimpleMailSender sender = new SimpleMailSender();
		boolean b = sender.sendHtmlMail(sendInfo);
		if (b) {
			Log.info("�����ʼ����ͳɹ���");
		} else {
			Log.error("�����ʼ�����ʧ�ܣ�");
		}
	}

}
