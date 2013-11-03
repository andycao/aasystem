<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="util.SessionUtil;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../js/jquery.min.v1.8.3.js"></script>
<title>Change Password Page</title>
</head>
<body>
<h2>密码更改页面</h2>
<a href="login.action">返回主页</a>
<hr>
<s:actionerror/>
<s:form action="changePassword.action" method="post">
	<s:password name="oldPassword" label="老密码"></s:password>
	<s:password name="newPassword" label="新密码"></s:password>
	<s:password name="newPassword2" label="重复新密码"></s:password>
	<input type="hidden" name="userID" value="<%=SessionUtil.getUserObj().getUserID() %>"></input>
	<s:submit value="确定"></s:submit>
	<s:reset value="清除"></s:reset>
</s:form>
</body>
</html>