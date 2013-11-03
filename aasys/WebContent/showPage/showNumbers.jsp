<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AA分摊金额</title>
</head>
<body>
<h3>AA后结果</h3>
<a href="./finishAll.action">完成AA</a>
<br>
<a href="./login.action">取消本次AA</a>
<hr>
<p><s:property value="currentAverage"/></p>
<table>
	<tr>
	<th>用户名</th>
	<th>应付金额</th>	
	</tr>
	<s:iterator value="currentAverage" id="id">
		<tr>
		<td><s:property value="#id.getKey()" /></td>
		<td><s:number name="#id.getValue()" type="number" maximumFractionDigits="2"/>元</td>
		</tr>
	</s:iterator>
</table>
</body>
</html>