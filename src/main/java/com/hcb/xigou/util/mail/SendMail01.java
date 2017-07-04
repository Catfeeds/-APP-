package com.hcb.xigou.util.mail;


public class SendMail01 {

		public static Boolean sendHtml(String email,String msg,String subject){
			MailSenderInfo mailInfo = new MailSenderInfo(); 
			  mailInfo.setMailServerHost("smtp.189.cn"); 
			  mailInfo.setMailServerPort("25"); 
			  mailInfo.setValidate(true); 
			  mailInfo.setUserName("new_1yard@189.cn"); 
			  mailInfo.setPassword("qazxsw12345");
			  mailInfo.setFromAddress("new_1yard@189.cn"); 
			  mailInfo.setSubject(subject); 
			  mailInfo.setContent(msg); 
			  mailInfo.setToAddress(email); 
			  SimpleMailSender sms = new SimpleMailSender();
		     return sms.sendHtmlMail(mailInfo);//发送html格式
		}
		public static void main(String[]args){
			System.out.println(sendHtml("419638657@qq.com","asdf","test"));
		}
}
