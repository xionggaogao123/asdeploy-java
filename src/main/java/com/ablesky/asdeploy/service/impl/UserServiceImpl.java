package com.ablesky.asdeploy.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ablesky.asdeploy.dao.IUserDao;
import com.ablesky.asdeploy.pojo.User;
import com.ablesky.asdeploy.service.IUserService;
import com.ablesky.asdeploy.util.Page;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	@Override
	public void saveOrUpdateUser(User user) {
		userDao.saveOrUpdate(user);
	}
	
	@Override
	public void deleteUser(User user) {
		userDao.delete(user);
	}
	
	@Override
	public User getUserById(Long id) {
		return userDao.getById(id);
	}
	
	@Override
	public User getUserByUsername(String username) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("username", username);
		return userDao.unique(param);
	}
	
	@Override
	public List<User> getUserListResult(int start, int limit, Map<String, Object> param) {
		return userDao.list(start, limit, param);
	}
	
	@Override
	public Page<User> getUserPageResult(int start, int limit, Map<String, Object> param) {
		return userDao.paginate(start, limit, param);
	}
	
	@Override
	public void createNewUser(String username, String rawPassword) {
		String password = rawPassword;
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		User user = new User(username, password, ts, ts);
		saveOrUpdateUser(user);
	}
}
