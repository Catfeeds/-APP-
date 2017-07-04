package hello;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class SingleMailSend {
	
	public static void main(String[] args) {
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		senderImpl.setHost("smtp.163.com");//邮件服务器
		SimpleMailMessage  simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo("my_mail@163.com");
		simpleMailMessage.setFrom("my_mail@163.com");
		simpleMailMessage.setSubject("这个是邮件主题");
		simpleMailMessage.setText("这个是邮件内容");
		
		 senderImpl.setUsername( "my_mail_username" ) ;  //  根据自己的情况,设置username 
		 senderImpl.setPassword( "my_mail_password" ) ;  //  根据自己的情况, 设置password 

		
		Properties prop  =   new  Properties() ;
		prop.put( "mail.smtp.auth" ,  "true" ) ;  //  将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确 
		prop.put( "mail.smtp.timeout" ,  "25000" ) ; 
		senderImpl.setJavaMailProperties(prop);  
	    // 发送邮件  
	    senderImpl.send(simpleMailMessage); 
	     
	    System.out.println( " 邮件发送成功.. " ); 
		
		
	}

}
