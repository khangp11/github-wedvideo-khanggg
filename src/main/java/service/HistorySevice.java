package service;

import java.util.List;

import entity.History;
import entity.User;
import entity.Video;

public interface HistorySevice {
	List<History> findByUser(String username);
	List<History>findByUserAndLiked(String username);
	History findByUserIdAndVideoId(Integer userId, Integer videoId);
	History create(User user,Video video);
	boolean updateLikeOrUnlike(User user,String videoHref);
	History delete(History entity);
}	
