package com.refferal.mail;

import com.refferal.mail.*;

public class MailTest {

	public static void main(String[] args) {
		// 这个类主要是设置邮件
		MailSendInfo mailInfo = new MailSendInfo();
		mailInfo.setMailServerHost("smtp.qq.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("gotoali@foxmail.com");
		mailInfo.setPassword("zty0703");// 您的邮箱密码
		mailInfo.setFromAddress("gotoali@foxmail.com");
		mailInfo.setToAddress("churenceo@foxmail.com");
		mailInfo.setSubject("搬砖网-阿里-5年-产品经理-高田");
		mailInfo.setContent("您好，这是我的简历，请查收。");
		mailInfo.setAttachFileName("中文简历.pdf");
		mailInfo.setAttachFilePath("C:\\Users\\sunyufei\\Desktop\\高田-简历-互联网产品.pdf");
		// 这个类主要来发送邮件
		MailServiceImpl sms = new MailServiceImpl();
		sms.sendTextMail(mailInfo);// 发送文体格式
	}
}
