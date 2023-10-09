package Severlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.SessionAttr;
import entity.History;
import entity.User;
import entity.Video;
import service.HistorySevice;
import service.VideoService;
import service.impl.HistoryServiceImpl;
import service.impl.VideoServiceImpl;

@WebServlet("/video")
public class VideoController extends HttpServlet {

	
	private VideoService videoService = new VideoServiceImpl();
	private HistorySevice historySevice = new HistoryServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String actionParam = req.getParameter("action");
		String href = req.getParameter("id");
		HttpSession session = req.getSession();
		switch (actionParam) {
			case "watch":
				doGetWatch(session, href, req, resp);
				break;
			case "like":
				doGetLike(session, href, req, resp);
				break;
				}
		}	
	
	private void doGetWatch(HttpSession session,String href, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Video video = videoService.findByHref(href);
		req.setAttribute("video", video);
		
		User currentUser = (User) session.getAttribute(SessionAttr.CURR_STRING);
		if (currentUser != null) {
			History history = historySevice.create(currentUser, video);
			req.setAttribute("flagLikedBtn", history.getislike());			
		}
		
		req.getRequestDispatcher("/views/user/video-detail.jsp").forward(req, resp);
	}
	private void doGetLike(HttpSession session,String href, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		User currentUser = (User) session.getAttribute(SessionAttr.CURR_STRING);
		Boolean result = historySevice.updateLikeOrUnlike(currentUser, href);
		if (result == true) {
			resp.setStatus(204);
		}else {
			resp.setStatus(400);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	
	
}
