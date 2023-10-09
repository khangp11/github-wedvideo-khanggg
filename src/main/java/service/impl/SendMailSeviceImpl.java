package service.impl;

import javax.servlet.ServletContext;

import Util.SendMailUtil;
import entity.User;
import service.EmailSevice;

public class SendMailSeviceImpl implements EmailSevice {
	
	private static final String EMAIL_WELCOME_SUBJECT = "Welcome to video clip";
	private static final String EMAIL_FORGOT_PASSWORD = "Video clip - New password";

	@Override
	public void SendMail(ServletContext Context, User recipient, String type) {
		String host = Context.getInitParameter("host");
	    String port = Context.getInitParameter("port");
	    String user = Context.getInitParameter("user");
	    String pass = Context.getInitParameter("pass");
        try {
			String content = null;
			String subject = null;
			switch (type) {
			case "welcome":
				subject = EMAIL_WELCOME_SUBJECT;
				content = "Dear" + recipient.getUsername() + ", hope you have a good time";
				break;
			case "forgot":
				subject = EMAIL_FORGOT_PASSWORD;
				content = "Dear" + recipient.getUsername() + ", you new password here: " + recipient.getPassword();
				break;
			default:
				subject = "video clip";
				content = "heheheheheheheheheh";				
			}
			SendMailUtil.sendEmail(host, port, user, pass, recipient.getUsername(), subject, content);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
