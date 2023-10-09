package service;

import javax.servlet.ServletContext;

import entity.User;

public interface EmailSevice {
	
	void SendMail(ServletContext Context, User user,String type);
	
}
