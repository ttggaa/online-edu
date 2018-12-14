package com.education.framework.baseModule.module.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.education.framework.base.BaseServices;

@Service
public class MailServices extends BaseServices{

	@Autowired
	JavaMailSenderImpl mailSender;
	
	public void sendMail(String toEmail,String subject, String text) throws MessagingException{
	   MimeMessage msg = mailSender.createMimeMessage();
	   MimeMessageHelper helper = new MimeMessageHelper(msg, true,"UTF-8");  
	   helper.setTo(toEmail);
	   helper.setFrom(mailSender.getUsername());
	   helper.setSubject(subject);
       helper.setText(text,true);
       mailSender.send(msg);
	}
}
