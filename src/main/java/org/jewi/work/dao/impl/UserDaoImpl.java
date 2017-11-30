package org.jewi.work.dao.impl;

import java.util.List;
import java.util.Map;

import org.jewi.work.dao.UserDao;
import org.jewi.work.entity.User;
import org.jewi.work.utils.Page;
import org.jewi.work.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public int add(User user) {
		return jdbcTemplate.update("insert into user (username,password,phone) values (?,?,?)"
				,user.getUsername(),user.getPassword(),user.getPhone());
	}

	@Override
	public int update(User user) {
		return jdbcTemplate.update("update user set username = ?,password = ?,phone = ?"
				,user.getUsername(),user.getPassword(),user.getPhone());
	}

	@Override
	public int deleteById(long userId) {
		return jdbcTemplate.update("delete from user where id = " + userId);
	}

	@Override
	public User queryUserById(Long id) {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<User> list = jdbcTemplate.query("select * from user where id = ?", new Object[]{id}, new BeanPropertyRowMapper(User.class));
        if(null != list && list.size()>0){
            User user = list.get(0);
            return user;
        }else{
            return null;
        }
	}

	@Override
	public Page queryUserList(Map<String, Object> params) {
		StringBuffer sql =new StringBuffer();
        sql.append("select * from user where 1=1");
        if(!StringUtil.isBlank((String)params.get("username"))){
            sql.append(" and username like '%").append((String)params.get("username")).append("%'");
        }
        if(!StringUtil.isBlank((String)params.get("phone"))){
            sql.append(" and phone like '%").append((String)params.get("phone")).append("%'");
        }
        Page page = new Page(sql.toString(), Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()), jdbcTemplate);
        return page;
	}

}
