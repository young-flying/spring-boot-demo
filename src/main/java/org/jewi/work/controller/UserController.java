package org.jewi.work.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jewi.work.entity.User;
import org.jewi.work.service.UserService;
import org.jewi.work.utils.Page;
import org.jewi.work.utils.ServletUtil;
import org.jewi.work.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller("/user")
public class UserController {
	@Autowired
	private UserService userService;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/queryList",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public void queryLearnList(HttpServletRequest request ,HttpServletResponse response){
        String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数
        String rows = request.getParameter("rows"); // 取得每页显示行数，,注意这是jqgrid自身的参数
        String username = request.getParameter("username");
        String phone = request.getParameter("phone");
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("page", page);
        params.put("rows", rows);
        params.put("username", username);
        params.put("phone", phone);
        Page pageObj =userService.queryUserList(params);
        List<Map<String, Object>> learnList=pageObj.getResultList();
        JSONObject jo=new JSONObject();
        jo.put("rows", learnList);
        jo.put("total", pageObj.getTotalPages());
        jo.put("records", pageObj.getTotalRows());
        ServletUtil.createSuccessResponse(200, jo, response);
    }
	
	/**
     * 新添用户
     * @param request
     * @param response
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public void addLearn(HttpServletRequest request , HttpServletResponse response){
        JSONObject result=new JSONObject();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        if(StringUtil.isBlank(username)){
            result.put("message","用户名不能为空!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        if(StringUtil.isBlank(password)){
            result.put("message","密码不能为空!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        if(StringUtil.isBlank(phone)){
            result.put("message","手机号不能为空!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
        
        int index=userService.add(user);
        System.out.println("结果="+index);
        if(index>0){
            result.put("message","用户信息添加成功!");
            result.put("flag",true);
        }else{
            result.put("message","用户信息添加失败!");
            result.put("flag",false);
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }
    /**
     * 修改用户
     * @param request
     * @param response
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void updateLearn(HttpServletRequest request , HttpServletResponse response){
        JSONObject result=new JSONObject();
        String id = request.getParameter("id");
        User user =userService.queryUserById(Long.valueOf(id));
        String phone = request.getParameter("phone");
        if(StringUtil.isBlank(phone)){
            result.put("message","手机号不能为空!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        user.setPhone(phone);
        int index=userService.update(user);
        System.out.println("修改结果="+index);
        if(index>0){
            result.put("message","用户信息修改成功!");
            result.put("flag",true);
        }else{
            result.put("message","用户信息修改失败!");
            result.put("flag",false);
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }
    /**
     * 删除用户
     * @param request
     * @param response
     */
    @RequestMapping(value="/delete",method = RequestMethod.POST)
    @ResponseBody
    public void deleteUser(HttpServletRequest request ,HttpServletResponse response){
        String ids = request.getParameter("ids");
        logger.debug("user delete ::: id {}",ids);
        JSONObject result = new JSONObject();
        result.put("message","用户信息删除失败!");
        result.put("flag",false);
        if(StringUtil.isNotBlank(ids)) {
        	
        	//删除操作
        	long id = Long.parseLong(ids);
        	int index = userService.deleteById(id);
        	if(index>0){
        		result.put("message","用户信息删除成功!");
        		result.put("flag",true);
        	}
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }
	

}
