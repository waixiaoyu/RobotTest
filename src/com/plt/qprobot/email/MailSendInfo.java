package com.plt.qprobot.email;

import java.util.Properties;

public class MailSendInfo {

	// 发送邮件的服务器的IP和端口 是否需要验证
	private String mailServerHost;
	private String mailServerPort;
	private boolean isValidate;

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	// 发送者的地址
	private String fromAddess;

	// 登陆邮件服务器的用户名和密码
	private String userName;
	private String passWord;

	// 接收者的地址
	private String[] toAddress;

	// 邮件主题 内容 附件路径
	private String subject;
	private String content;
	private String file_path;

	/**
	 * 
	 * 获取邮件回话属性
	 * 
	 */
	public Properties getProperties() {
		Properties p = new Properties();
		p.put("mail.smtp.host", this.mailServerHost);
		p.put("mail.smtp.port", this.mailServerPort);
		p.put("mail.smtp.auth", isValidate ? "true" : "false");
		return p;
	}

	public boolean isValidate() {
		return isValidate;
	}

	public void setValidate(boolean isValidate) {
		this.isValidate = isValidate;
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public String getFromAddess() {
		return fromAddess;
	}

	public void setFromAddess(String fromAddess) {
		this.fromAddess = fromAddess;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String[] getToAddress() {
		return toAddress;
	}

	public void setToAddress(String[] toAddress) {
		this.toAddress = toAddress;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}