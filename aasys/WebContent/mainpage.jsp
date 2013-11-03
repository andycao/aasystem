<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="util.SessionUtil,java.util.HashMap" %>
<%@ page import="util.FormatUtil" %>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
request.setAttribute("users", FormatUtil.getUsers());
request.setAttribute("types",FormatUtil.getPayTypes());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
</head>
<body>
<h2>Hello <%=SessionUtil.getUserObj().getUserName() %></h2>
<hr>
<a href="actionPage/changePassword.jsp">更改密码</a>
<br>
<a href="actionPage/addPay.jsp">添加账单</a>
<br>
<a href="calcCurrentAverage.action">AA账单</a>
<br>
<a href="logout.action">登出</a>
<hr>
<table>
<tr>
   <th>消费日期</th>
   <th>付款人</th>
   <th>消费类型</th>
   <th>消费数额</th>
   <th>AA过了吗</th>
</tr>
<s:iterator value="showList" var="pay">
   <tr>
   <td><s:date name="payDate" format="MM-dd"/></td>
   <td>${users[payedUserID]}</td>
   <td>${types[payTypeInt]}</td>
   <td><s:number name="payAmount"/></td>
   <td>${payDone==0?"否":"是"}
   </td>
   <td><a href="pre_editpay.action?payID=<s:property value='payID' />">修改</a></td>
   <td><a href="javascript:if(confirm('确认删除吗?'))window.location='removePay.action?payID=<s:property value="payID" />'">删除</a></td>
   <td><a href="preEditAAUser.action?payID=<s:property value='payID' />">编辑AA用户</a></td>
   </tr>
</s:iterator>
</table>
<a href="javascript:if(confirm('确认删除吗?'))window.location='removeAllMyPay.action'">删除我的账单</a>
</body>
</html>