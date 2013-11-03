<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
//for paytype collection
	request.setAttribute("map", FormatUtil.getPayTypes());
	request.setAttribute("users", FormatUtil.getUsers());
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="./datepicker/WdatePicker.js"></script>
<title>编辑</title>
</head>
<body>
<h2>编辑支付信息</h2>
<a href="login.action">返回首页</a>
<hr>
<s:actionerror/>
<s:form action="editPay.action" method="get"> 
	<s:textfield name="payID" label="payid" readonly="true"></s:textfield>
	<s:textfield name="payDate" label="支付时间" onclick="WdatePicker()"></s:textfield>
	<s:textfield name="payAmount" label="支付金额"></s:textfield>
	
	<s:select label="消费类别" name="payType" list="#request.map"></s:select>
	
	<s:textarea name="payDetail" label="详细信息" cols="40" rows="10"></s:textarea>
	
	<s:select name="payedUserID" label="付款人" list="#request.users"></s:select>
	
	<s:radio list="#{0:'否',1:'是'}" label="是否结算过" name="payDone"></s:radio>
	<s:submit value="确定"></s:submit>
	<s:reset value="清除"></s:reset>
</s:form>

</body>
</html>