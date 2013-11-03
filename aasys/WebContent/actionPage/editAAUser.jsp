<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="util.FormatUtil;" %>
<%
	request.setAttribute("users", FormatUtil.getUsers());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit AA User</title>
<script src="./js/jquery.min.v1.8.3.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#select-all").click(function(){
		$('#aaUser-').attr("checked",true);
		$('input[type=checkbox]').each(function(){
			this.checked = true;
		});
	});
});
</script>
</head>
<body>
<h3>AA用户编辑</h3>
<a href="./login.action">返回首页</a>
<hr>
<s:form action="editAAUser.action" method="post">
	<s:textfield name="payID" label="payid" readonly="true"></s:textfield>
	<s:checkboxlist  name="aaUser" list="#request.users" value="#request.payUser" label="AA用户"></s:checkboxlist>
	
	<s:submit value="提交"></s:submit>
	<s:reset value="重置"></s:reset>
</s:form>
<input type="button" id="select-all" value="AA全选"/>
</body>
</html>