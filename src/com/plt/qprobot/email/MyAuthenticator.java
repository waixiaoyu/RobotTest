package com.plt.qprobot.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthenticator extends Authenticator {

	String userName = null;
	String passWord = null;

	public MyAuthenticator(String username, String password) {
		this.userName = username;
		this.passWord = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, passWord);
	}
}