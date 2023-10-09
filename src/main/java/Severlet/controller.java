package Severlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import antlr.CharQueue;
import constant.SessionAttr;
import entity.History;
import entity.User;
import entity.Video;
import service.HistorySevice;
import service.VideoService;
import service.impl.HistoryServiceImpl;
import service.impl.VideoServiceImpl;

@WebServlet({"/indexasm","/favorite","/history"})
public class controller extends HttpServlet {
	
	private VideoService videoService = new VideoServiceImpl();
	private HistorySevice historySevice = new HistoryServiceImpl();
	private static final int VIDEO_MAX_PAGE_SIZE = 8;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		HttpSession session = req.getSession();
		String path = req.getServletPath();
		switch (path) {
			case "/indexasm": 
				doGetIndex(req, resp);
				break;
			case "/favorite":
				doGetFavorite(session,req, resp);
				break;
			case "/history":
				doGetHistory(session, req, resp);
				break;
			}
	}
	private void doGetIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Video> countVideo = videoService.findAll();
		int maxPage =(int) Math.ceil(countVideo.size()/(double)VIDEO_MAX_PAGE_SIZE);
		req.setAttribute("maxPage", maxPage);
		
		List<Video> videos;
		String pageNumber = req.getParameter("page");		
		if(pageNumber == null) {
			videos = videoService.findAll(1,VIDEO_MAX_PAGE_SIZE);
			req.setAttribute("currentPage",1);
		}else {
			videos = videoService.findAll(Integer.valueOf(pageNumber),VIDEO_MAX_PAGE_SIZE);
			req.setAttribute("currentPage",pageNumber);
		}
		req.setAttribute("videos", videos);
		req.getRequestDispatcher("/views/user/index.jsp").forward(req, resp);
	}
	private void doGetFavorite(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) session.getAttribute(SessionAttr.CURR_STRING);
		List<History> histories = historySevice.findByUserAndLiked(user.getUsername());
		List<Video> videos = new ArrayList<>();
		for (int i = 0; i < histories.size(); i++) {
			videos.add(histories.get(i).getVideo());	
			System.out.println(histories.get(i).getVideo());
		}
		req.setAttribute("videos", videos);
		req.getRequestDispatcher("/views/user/favorite.jsp").forward(req, resp);
	}
	private void doGetHistory( HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) session.getAttribute(SessionAttr.CURR_STRING);
		List<History> histories = historySevice.findByUser(user.getUsername());
		List<Video> videos = new ArrayList<>();
		for (int i = 0; i < histories.size(); i++) {
			videos.add(histories.get(i).getVideo());
			System.out.println(histories.get(i).getVideo());
		}
		req.setAttribute("videos", videos);
		req.getRequestDispatcher("/views/user/history.jsp").forward(req, resp);
	}
	
}
