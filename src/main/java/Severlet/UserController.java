package Severlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import constant.SessionAttr;
import entity.User;
import service.EmailSevice;
import service.UserService;
import service.impl.SendMailSeviceImpl;
import service.impl.UserServiceImpl;

@WebServlet({"/login","/register","/logout","/forgotpass","/chagePass"})
public class UserController extends HttpServlet {
		
	private UserService userService = new UserServiceImpl();
	private EmailSevice emailSevice = new SendMailSeviceImpl();
		
	@Override	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String path = req.getServletPath();
		switch (path) {
			case "/login": 
				doGetLogin(req, resp);
				break;
			case "/register":
				doGetRegister(req, resp);
				break;
			case "/logout":
				doGetLogout(session, req, resp);
				break;
			case "/forgotpass":
				doGetForgotpass(req, resp);
				break;
			}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String path = req.getServletPath();
		switch (path) {
		case "/login": 
			doPostLogin(session,req, resp);
			break;
		case "/register": 
			doPostRegister(session, req, resp);
			break;
		case "/forgotpass":
			doPsotForgotpass(req, resp);
			break;
		case "/chagePass":
			doPosChagePass(session, req, resp);
			break;
		}
	}
	private void doGetLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/user/login.jsp").forward(req, resp);
	}
	private void doGetRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		req.getRequestDispatcher("/views/user/register.jsp").forward(req, resp);
	}
	private void doGetLogout(HttpSession session ,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		session.removeAttribute(SessionAttr.CURR_STRING);
		resp.sendRedirect("indexasm");
	}
	private void doGetForgotpass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		req.getRequestDispatcher("/views/user/forgot-password.jsp").forward(req, resp);
	}
	
	private void doPostLogin(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		User user = userService.login(username, password);
		if (user != null){
			session.setAttribute(SessionAttr.CURR_STRING, user);
			resp.sendRedirect("indexasm");
		}else {
			resp.sendRedirect("login");
		}		
	}
	private void doPostRegister(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		User user = userService.create(username, password, email);		
		if (user != null){
			emailSevice.SendMail(getServletContext(), user, email);
			session.setAttribute(SessionAttr.CURR_STRING, user);
			session.setAttribute("name", user);
			resp.sendRedirect("indexasm");
		}else {		
			resp.sendRedirect("register");
		}		
	}
	private void doPsotForgotpass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		String email = req.getParameter("email");
		User userWithNewPass = userService.resetPassword(email);
		if (userWithNewPass != null) {
			emailSevice.SendMail(getServletContext(), userWithNewPass, "forgot");
			resp.setStatus(204);
		}else {
			resp.setStatus(400);
		}
	}
	private void doPosChagePass(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String currentpass = req.getParameter("currentPass");
		String newpass = req.getParameter("newPass");
		
		User curentUser = (User) session.getAttribute(SessionAttr.CURR_STRING);
		
		if (curentUser.getPassword().equals(currentpass)) {
			curentUser.setPassword(newpass);
			User updateuser = userService.update(curentUser);
			if (updateuser != null) {
				session.setAttribute(SessionAttr.CURR_STRING, updateuser);
				resp.setStatus(204);
			}else {
				resp.setStatus(400);
			}
		}else {
			resp.setStatus(400);
		}
	}
}
