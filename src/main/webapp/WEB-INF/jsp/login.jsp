<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<input type="text" id="username" placeholder="请输入用户名">
		<input type="passrod" id="password" placeholder="请输入密码">
		<input type="button" value="提交" id="doSubmit">
	</div>
	
</body>
<script type="text/javascript" src="/static/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$("#doSubmit").click(function(){
		var username = $("#username").val() || "";
		var password = $("#password").val() || "";
		$.ajax({
			method:"post",
			url:"/login",
			dataType:"json",
			data:{
				username:username,
				password:password
			},
			success:function(res) {
				if(!res) {
					alert("查询失败");
					return;
				}
				if(res.code == "1000") {
					location.href = "/learn/";
					return;
				} else {
					alert(res.message);
				}
			}
		});		
	});
</script>
</html>