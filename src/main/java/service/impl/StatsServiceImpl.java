package service.impl;

import java.util.List;

import dao.StatsDao;
import dao.impl.StastDaoImpl;
import dto.videoLikedInfo;
import service.StatsSevice;

public class StatsServiceImpl implements StatsSevice {
	
	private StatsDao statsDao;
	public  StatsServiceImpl() {
		statsDao = new StastDaoImpl();
	}
	
	@Override
	public List<videoLikedInfo> findVideoLikedInfo(){
		return statsDao.findVideoLikedInfo();
	}
}  
