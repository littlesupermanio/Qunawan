<%@page import="campsg.qunawan.entity.Orders"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.oraclecsg.com/tlt" prefix="my"%>
<%@taglib uri="http://www.oraclecsg.com/own" prefix="own"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>订单确认-去哪玩旅游网</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet"
		href="css/common.css">
	<link rel="stylesheet" 
		href="css/order.css">
	<script type="text/javascript"
		src="js/jquery/jquery-1.11.0.min.js"></script>
	<base href="<%=basePath%>" />
</head>
<body>
	<!-- header【开始】 -->
	<%@ include file="/common/header.jsp"%>
	<!-- header【结束】 -->

	<!-- 主体【开始】 -->
	<div class="main">
		<div class="wrap">
			<!-- 步骤【开始】 -->
			<ol class="step">
				<li class="ui-step1 ui-step-start">
					<div class="step-arrow">
						<i class="arrow_r1"></i> <i class="arrow_r2"></i>
					</div> <span class="step-text">1.填写订单信息</span>
				</li>
				<li class="ui-step2">
					<div class="step-arrow">
						<i class="arrow_r1"></i> <i class="arrow_r2"></i>
					</div> <span class="ui-step-text">2.选择付款方式付款</span>
				</li>
				<li class="ui-step3 ui-step-end cur">
					<div class="step-arrow"></div> <span class="ui-step-text">3.预订成功</span>
				</li>
			</ol>
			<!-- 步骤【结束】 -->

			<!-- 预订成功【开始】 -->
			<div class="panel3">
				<div class="order_box success_box" style="display: block" id="paysuccess">
					<p class="suc_title"><b>您的订单已支付成功</b></p>
					<div class="dot_line"></div>
					<div class="suc_info">
						<i class="green"></i> 
						<span><b>以下一个订单已支付成功</b></span>
						<span class="green_font"><b>共计 ￥${vo.price }元</b></span>
					</div>
					<div class="dot_line"></div>
					<div class="order_info">
						<p>
							<span class="number">订单号：<b>${vo.orderno }</b></span> 
							<span class="name">订单名称：${vo.title }</span>
							<span class="prices">在线支付：￥${vo.price }元</span>
						</p>
					</div>
					<div class="dot_line"></div>
					<div class="order_end">
						<div class="returnIndex"></div>
						<span><a href="myorder.jhtml?type=initOrderDetail&order_id=${vo.id }">查看订单详情 >></a></span>
					</div>
				</div>
			</div>
			<!-- 预订成功【结束】 -->

		</div>
	</div>
	<script type="text/javascript">
	$(".user_dl .input").focus(function() {
		$(this).css("border", "1px solid #74b9ef");
		$(this).addClass("outset");
	})

	$(".user_dl .input").blur(function() {
		$(this).css("border", "1px solid #aabbcc");
		$(this).removeClass("outset");
	})
	</script>
	<script type="text/javascript"
		src="js/pages/order/order.js"></script>
	<!-- 主体【结束】 -->

	<!-- 引入尾部开始 -->
	<iframe src="./common/footer.jsp"
		style="width: 100%; height: 650px; border: 0; overflow: hidden;"></iframe>
	<!-- 引入尾部结束 -->
</body>
</html>