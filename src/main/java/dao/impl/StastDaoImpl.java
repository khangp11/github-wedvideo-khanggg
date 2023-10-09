package dao.impl;

import java.util.ArrayList;
import java.util.List;

import dao.AbstractDao;
import dao.HistoryDao;
import dao.StatsDao;
import dto.videoLikedInfo;
import entity.History;
import entity.Video;
import net.bytebuddy.asm.Advice.Return;


public class StastDaoImpl extends AbstractDao<Object[]> implements StatsDao{
	//
	@Override
	public List<videoLikedInfo> findVideoLikedInfo() {
		String sql = "SELECT v.id, v.title, v.href, sum(CAST(h.isLike AS int)) as totalLike"
		         + " FROM Video v"
		         + " LEFT JOIN history h ON v.id = h.id"
		         + " WHERE v.isActive = 1"
		         + " GROUP BY v.id, v.title, v.href"
		         + " ORDER BY totalLike DESC";
	    List<Object[]> objects = super.findManyByNativeQuery(sql);
	    List<videoLikedInfo> result = new ArrayList<>();
	    objects.forEach(object -> {
	        videoLikedInfo videoLikedInfo = setDataVideoLikedInfo(object);	        
	        result.add(videoLikedInfo);
	    });
	    return result;
	}
	private videoLikedInfo setDataVideoLikedInfo(Object[] t) {
		videoLikedInfo likedInfo = new videoLikedInfo();
		likedInfo.setVideoId((Integer)t[0]);
		likedInfo.setTitte((String)t[1]);
		likedInfo.setHref((String)t[2]);
		likedInfo.setTotallike((Integer)t[3] == null ? 0 : (Integer) t[3]);
		return likedInfo;
	}
}
