package Severlet.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import constant.SessionAttr;
import dto.videoLikedInfo;
import entity.User;
import service.StatsSevice;
import service.UserService;
import service.impl.StatsServiceImpl;
import service.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/admin","/admin/favorite"}, name = "HomeControllerOfAdmin")
public class HomeController extends HttpServlet {
	
	private StatsSevice statsSevice = new StatsServiceImpl();
	private UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute(SessionAttr.CURR_STRING);
		if (currentUser != null && currentUser.getisAdmin()== Boolean.TRUE) {
			String path = req.getServletPath();
			switch (path) {
				case "/admin": 
					doGetHome(req, resp);
					break;
				case "/admin/favorite":
					doGetfavorite(req, resp);
					break;
				case "/history":
					//doGetHistory(session, req, resp);
					break;
				}	
		}else {
			resp.sendRedirect("indexasm");
		}
		
	}
	protected void doGetHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<videoLikedInfo> videos = statsSevice.findVideoLikedInfo();
		req.setAttribute("videos", videos);
		req.getRequestDispatcher("/views/admin/home.jsp").forward(req, resp);
		
	}
	protected void doGetfavorite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("aaplication/json");
		String videoHref = req.getParameter("href");	
		List<User> users = userService.findUsersLikedVideoByVideoHref(videoHref);
		if (users.isEmpty()) {
			resp.setStatus(400);
		}else {
			ObjectMapper mapper = new ObjectMapper();
			String dataResponse = mapper.writeValueAsString(users);
			resp.setStatus(200);
			out.print(dataResponse);
			out.flush();
		}
	}
	
	
}
