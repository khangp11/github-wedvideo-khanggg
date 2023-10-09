package dao.impl;

import java.util.List;

import dao.AbstractDao;
import dao.HistoryDao;
import entity.History;
import entity.Video;

public class HistoryDaoImpl extends AbstractDao<History> implements HistoryDao {

	@Override
	public List<History> findByUser(String username) {
		// TODO Auto-generated method stub
		String sql = "Select o From History o Where o.user.username =?0 And o.video.isActive = 1 Order by o.viewDate DESC";
		return super.findMany(History.class, sql,username);
	}

	@Override
	public List<History> findByUserAndLiked(String username) {
		// TODO Auto-generated method stub
		String sql = "Select o From History o Where o.user.username =?0 And o.islike = 1 AND o.video.isActive = 1 Order by o.viewDate DESC";
		return super.findMany(History.class, sql,username);
	}

	@Override
	public History findByUserIdAndVideoId(Integer userId, Integer videoId) {
		// TODO Auto-generated method stub
		String sql = "Select o From History o Where o.user.id = ?0 And o.video.id= ? 1 AND o.video.isActive = 1";
 		return super.findOne(History.class, sql, userId,videoId);
	}

	@Override
	public History create(History entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public History update(History entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public History delete(History entity) {
		// TODO Auto-generated method stub
		return super.delete(entity);
	}

}
