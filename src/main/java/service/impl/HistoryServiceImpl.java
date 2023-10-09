package service.impl;

import java.sql.Timestamp;
import java.util.List;

import dao.HistoryDao;
import dao.impl.HistoryDaoImpl;
import entity.History;
import entity.User;
import entity.Video;
import net.bytebuddy.asm.Advice.Return;
import service.HistorySevice;
import service.VideoService;

public class HistoryServiceImpl implements HistorySevice {
	
	private HistoryDao dao;
	
	private VideoService videoSevice = new VideoServiceImpl();
	
	
	public HistoryServiceImpl() {
		dao = new HistoryDaoImpl();
		}
	
	@Override
	public List<History> findByUser(String username) {
		// TODO Auto-generated method stub
		return dao.findByUser(username);
	}

	@Override
	public List<History> findByUserAndLiked(String username) {
		// TODO Auto-generated method stub
		return dao.findByUserAndLiked(username);
	}

	@Override
	public History findByUserIdAndVideoId(Integer userId, Integer videoId) {
		// TODO Auto-generated method stub
		return dao.findByUserIdAndVideoId(userId, videoId);
	}

	@Override
	public History create(User user, Video video) {
		History exisHistory = findByUserIdAndVideoId(user.getId(),video.getId());
		if (exisHistory == null) {
			exisHistory = new History();
			exisHistory.setUser(user);
			exisHistory.setVideo(video);
			exisHistory.setLikeDate(new Timestamp(System.currentTimeMillis()));
			exisHistory.setislike(Boolean.FALSE);
			return dao.create(exisHistory);
		}
		return exisHistory;
		
	}

	@Override
	public boolean updateLikeOrUnlike(User user, String videoHref) {
		Video video = videoSevice.findByHref(videoHref);
		History exisHistory = findByUserIdAndVideoId(user.getId(),video.getId());
		System.out.println(exisHistory);
		if(exisHistory.getislike() == Boolean.FALSE) {
		   exisHistory.setislike(Boolean.TRUE);
		   exisHistory.setLikeDate(new Timestamp(System.currentTimeMillis()));
		}else {
			exisHistory.setislike(Boolean.FALSE);
			exisHistory.setLikeDate(null);
		}
		History updatedHistory = dao.update(exisHistory);
		return updatedHistory != null ? true : false;
	}

	@Override
	public History delete(History entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
