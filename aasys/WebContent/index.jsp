<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*;" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆页面</title>
</head>
<body>
<h2>请登录</h2>
<s:actionerror/>
<s:form  class="login-form" method="post" action="login.action">
	<s:textfield name="userName" label="用户名"></s:textfield>
	<s:password name="userPassword" label="密码"></s:password>
	<s:submit value="提交"></s:submit>
	<s:reset value="清除"></s:reset>
</s:form>
</body>
</html>