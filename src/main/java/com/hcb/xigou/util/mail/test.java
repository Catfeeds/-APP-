package com.hcb.xigou.util.mail;

import java.util.ArrayList;
import java.util.List;

public class test {

	public static void main(String[] args){
        //这个类主要是设置邮件
	  MailSenderInfo mailInfo = new MailSenderInfo(); 
	  mailInfo.setMailServerHost("smtp.163.com"); 
	  mailInfo.setMailServerPort("25"); 
	  mailInfo.setValidate(true); 
	  mailInfo.setUserName("zzuhwt@163.com"); 
	  mailInfo.setPassword("hwt208");
	  mailInfo.setFromAddress("zzuhwt@163.com"); 
	  mailInfo.setSubject("java测试"); 
	  mailInfo.setContent("<a href=\"http://www.baidu.com\">如果收到了请忽略，如果没收到请忽略</a>"); 
	  List<String> toAddersses=new ArrayList<String>();
//	  toAddersses.add("zzuhwt@163.com");
//	  toAddersses.add("tonyzhou1008@163.com");
//	  toAddersses.add("515272796@qq.com");
//	  toAddersses.add("905952328@qq.com");
	  toAddersses.add("zzuhwt@163.com");
	  for(String address:toAddersses){
	  mailInfo.setToAddress(address); 
        //这个类主要来发送邮件
	  SimpleMailSender sms = new SimpleMailSender();
//         sms.sendTextMail(mailInfo);//发送文体格式 
         sms.sendHtmlMail(mailInfo);//发送html格式
	  }
	}


}
