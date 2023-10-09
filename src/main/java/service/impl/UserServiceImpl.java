package service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.User;
import service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDao dao;
	public UserServiceImpl() {
		dao = new UserDaoImpl();
	}
	@Override
	public User findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}
	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return dao.findByEmail(email);
	}
	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return dao.findByUsername(username);
	}
	@Override
	public User login(String username, String password) {
		return dao.findByUsernameAndPassword(username, password);
	}
	@Override
	public User resetPassword(String email) {
		User existUser = findByEmail(email);
		if (existUser != null) {
			String newpass = String.valueOf((int)(Math.random()*((9999 - 1000) +1)) + 1000);
			existUser.setPassword(newpass);
			return dao.update(existUser);
		}
			return null;
		
		
	}
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}
	@Override
	public List<User> findAll(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return dao.findAll(pageNumber, pageSize);
	}
	@Override
	public User create(String username, String password, String email) {
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setEmail(email);
		newUser.setAdmin(Boolean.FALSE);
		newUser.setActive(Boolean.TRUE);
		return dao.create(newUser);
	}
	@Override
	public User update(User entity) {
		// TODO Auto-generated method stub
		return dao.update(entity);
	}
	@Override
	public User delete(String username) {
		// TODO Auto-generated method stub
		User user = dao.findByUsername(username);
		user.setActive(Boolean.FALSE);
		return dao.update(user);
	}
	@Override
	public List<User> findUsersLikedVideoByVideoHref(String href) {
		Map<String, Object> param = new HashMap<>();
		param.put("videoHref", href);
		return dao.findUserLikedVideoByVideoHref(param);
	}
}
