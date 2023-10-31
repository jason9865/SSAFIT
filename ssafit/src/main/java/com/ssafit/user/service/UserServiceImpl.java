package com.ssafit.user.service;

import com.ssafit.user.model.dao.UserDao;
import com.ssafit.user.model.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserDao userDao;
	
	@Autowired
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public List<User> getList() {
		return userDao.selectAll();
	}
	@Override
	public User searchByUserSeq(int userSeq) {
		return userDao.selectByUserSeq(userSeq);
	}

	@Override
	public User searchByUserId(String id) {
		return userDao.selectByUserId(id);
	}

	@Override
	public User searchByName(String name) {
		return userDao.selectByName(name);
	}

	@Override
	public int registUser(User user) {
		// TODO Auto-generated method stub
//		userDao.insertUser(user);
		 return userDao.insertUser(user);
	}

	@Override
	public void modifyUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public void removeUser(int userSeq) {
		userDao.deleteUser(userSeq);
	}
	
	@Override
	public User login(User user) {
		User temp = userDao.selectByUserId(user.getUserId());
		if (temp != null && temp.getUserPwd().equals(user.getUserPwd()))
			return temp;
		return null;
	}


}
