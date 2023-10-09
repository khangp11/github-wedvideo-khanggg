package dao;

import java.util.List;

import dto.videoLikedInfo;
import entity.User;

public interface StatsDao {
	
	List<videoLikedInfo> findVideoLikedInfo(); 
}
