package Severlet.Admin;

import java.io.IOException;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.Case;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.SessionAttr;
import entity.User;
import entity.Video;
import service.VideoService;
import service.impl.VideoServiceImpl;

@WebServlet("/videoi")
public class VideoController extends HttpServlet  {

	
	private VideoService videoService = new VideoServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session =req.getSession();
		User currentUser = (User) session.getAttribute(SessionAttr.CURR_STRING);
		if (currentUser != null & currentUser.getisAdmin()==Boolean.TRUE) {
			String action = req.getParameter("action");
			switch (action) {
			case "view":
				doGetOverView(req, resp);
				break;
			case "delete":
				doGetDelete(req, resp);	
				break;
			case "add":
				req.setAttribute("isEdit",false);
				doGetAdd(req, resp);
				
				break;
			case "edit":
				doGetEdit(req, resp);	
				break;
			}	
		}else {
			resp.sendRedirect("indexasm");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session =req.getSession();
		User currentUser = (User) session.getAttribute(SessionAttr.CURR_STRING);
		if (currentUser != null & currentUser.getisAdmin()==Boolean.TRUE) {
			String action = req.getParameter("action");		
			switch (action) {
			case "add":
				doPostAdd(req, resp);	
				break;
			}	
		}else {
			resp.sendRedirect("indexasm");
		}
	}
	private void doPostAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		String title = req.getParameter("title");
		String descripition = req.getParameter("descripition");
		String href = req.getParameter("href");
		String poster = req.getParameter("poster");
		
		Video video = new Video();
		video.setTitle(title);
		video.setDescripition(descripition);
		video.setHref(href);
		video.setPoster(poster);
		Video videoReturn = videoService.create(video);
		if (videoReturn != null) {
			resp.setStatus(204);
		}else {
			resp.setStatus(400);
		}
	}
	protected void doGetOverView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Video> videos = videoService.findAll();
		req.setAttribute("videos", videos);
		
		req.getRequestDispatcher("/views/admin/video-overview.jsp").forward(req, resp);
	}
	protected void doGetDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		String href = req.getParameter("href");
		Video videoDeleted = videoService.delete(href);
		if (videoDeleted != null) {
			resp.setStatus(204);
		}else {
			resp.setStatus(400);
		}

	}
	protected void doGetAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Video> videos = videoService.findAll();
		req.setAttribute("videos", videos);
		
		req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
	}
	protected void doGetEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String href =req.getParameter("href");
		Video video = videoService.findByHref(href);
		req.setAttribute("video", video);
		
		req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
	}
	
}
