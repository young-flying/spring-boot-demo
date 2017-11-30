package org.jewi.work.service;

import java.util.Map;

import org.jewi.work.entity.User;
import org.jewi.work.utils.Page;

public interface UserService {
	int add(User user);
    int update(User user);
    int deleteById(long userId);
    User queryUserById(Long user);
    Page queryUserList(Map<String,Object> params);
}
