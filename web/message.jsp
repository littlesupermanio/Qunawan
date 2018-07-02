<%@page import="campsg.qunawan.globle.Constants"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>浏览记录</title>
</head>
<body>

	<div style="font-family:Microsoft YaHei">
		
		<%
			//获取用户访问记录集合
			List<Map.Entry<String, String>> list = (List<Map.Entry<String, String>>) session
					.getAttribute(Constants.ACCESS_RECORD_IN_SESSION);
			//定义输出字符串
			String s = "";
			//遍历该list中的Entry集合
			for (Map.Entry<String, String> entry : list) {
				//获取访问页面和次数
				String key = entry.getKey();
				//获取访问时间
				String value = entry.getValue();
				//分割字符串，格式是【页面名称:访问次数】
				String[] keyData = key.split(":");
				s += value + "：第" + keyData[1] + "次访问页面" + keyData[0] + "<br/>";
			}
	
			//获取用户登录时长（当前时间-session创建时间）
			long time = new Date().getTime() - session.getCreationTime();
			//把毫秒值转换为天、时、分、秒
			long days = time / (1000 * 60 * 60 * 24);
			long hours = (time % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
			long minutes = (time % (1000 * 60 * 60)) / (1000 * 60);
			long seconds = (time % (1000 * 60)) / 1000;
	
			s += "<br/>您的在线时长为：";
			if (days != 0)
				s += days + "天";
			if (hours != 0)
				s += hours + "小时";
			if (minutes != 0)
				s += minutes + "分钟";
			if (seconds != 0)
				s += seconds + "秒<br/>";
			session.invalidate();
		%>
		<%=s %>
		<br/>
		<a href="${pageContext.request.contextPath }/login.jsp" style="text-decoration:none">&lt;&lt; 点击此出返回登录页</a>
	</div>
</body>
</html>