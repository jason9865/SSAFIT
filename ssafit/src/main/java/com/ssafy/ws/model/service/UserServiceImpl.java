package com.ssafy.ws.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ws.model.dao.UserDao;
import com.ssafy.ws.model.dto.User;

@Service
public class UserServiceImpl implements UserService{
	
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
		User temp = userDao.selectByUserId(user.getId());
		if (temp != null && temp.getUserPwd().equals(user.getUserPwd()))
			return temp;
		return null;
	}


}
