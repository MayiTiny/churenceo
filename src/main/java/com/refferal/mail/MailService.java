package com.refferal.mail;

public interface MailService {

	public boolean sendTextMail(MailSendInfo mailInfo);
	
	public boolean sendHtmlMail(MailSendInfo mailInfo);
}
