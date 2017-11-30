package org.jewi.work.service.impl;

import java.util.Map;

import org.jewi.work.dao.UserDao;
import org.jewi.work.entity.User;
import org.jewi.work.service.UserService;
import org.jewi.work.utils.Page;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	@AutoConfigureOrder
	private UserDao userDao;
	
	@Override
	public int add(User user) {
		return userDao.add(user);
	}

	@Override
	public int update(User user) {
		return userDao.update(user);
	}

	@Override
	public int deleteById(long userId) {
		return userDao.deleteById(userId);
	}

	@Override
	public User queryUserById(Long userId) {
		return userDao.queryUserById(userId);
	}

	@Override
	public Page queryUserList(Map<String, Object> params) {
		return userDao.queryUserList(params);
	}

}
