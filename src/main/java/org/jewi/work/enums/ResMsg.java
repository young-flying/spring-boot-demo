package org.jewi.work.enums;

import com.alibaba.fastjson.JSONObject;

public enum ResMsg {
	SUCCESS(1000,"成功"),
	USER_NOT_EXIST(2001,"用户不存在"),
	USER_PASSWORD_ERROR(2002,"用户密码错误"),
	USER_NAME_BLANK(2003,"用户名称不能为空"),
	USER_PASSWORD_BLANK(2004,"用户密码不能为空"),
	FAIL(9999,"失败");
	
	private int code ;
	private String message;
	
	private ResMsg (int code,String message) {
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String toJSONString() {
		JSONObject json = new JSONObject();
		json.put("message", getMessage());
		json.put("code", getCode());
        return json.toJSONString();
    }
	
}
