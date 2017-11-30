package org.jewi.work.dao;

import java.util.Map;

import org.jewi.work.entity.User;
import org.jewi.work.utils.Page;

public interface UserDao {
	int add(User user);
    int update(User user);
    int  deleteById(long userId);
    User queryUserById(Long id);
    Page queryUserList(Map<String,Object> params);
}

