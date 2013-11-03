<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
//for paytype collection
	request.setAttribute("map", FormatUtil.getPayTypes());
	request.setAttribute("users", FormatUtil.getUsers());

%>
<script type="text/javascript" src="../datepicker/WdatePicker.js"></script>
<script src="../js/jquery.min.v1.8.3.js"></script>
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
<title>添加账单</title>
</head>
<body>
<h2>添加账单</h2>
<a href="login.action">返回主页</a>
<hr>
<s:actionerror/>
<s:form action="addPay.action" method="post">
	<s:textfield name="payDate" label="支付时间" onclick="WdatePicker()"></s:textfield>
	<s:textfield name="payAmount" label="支付金额"></s:textfield>
	<s:select label="消费类别" value="1" name="payType" list="#request.map"></s:select>
	
	<s:checkboxlist  name="aaUser" list="#request.users" value="" label="AA用户"></s:checkboxlist>
	<s:textarea name="payDetail" label="详细信息" cols="40" rows="10"></s:textarea>
	<input type="hidden" name="payedUserID" value="<%=SessionUtil.getUserObj().getUserID() %>"/>
	<s:submit value="确定"></s:submit>
	<s:reset value="清除"></s:reset>
</s:form>
<input type="button" id="select-all" value="AA全选"/>
</body>
</html>