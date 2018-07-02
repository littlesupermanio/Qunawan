<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>订单详情-去哪玩旅游网</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="css/common.css">
	<link rel="stylesheet" href="css/order_detail.css">
	<script src="js/jquery-1.10.2.min.js"></script>
	<base href="<%=basePath%>" />
</head>
<body>
	<!-- header【开始】 -->
	<%@ include file="/common/header.jsp"%>
	<!-- header【结束】 -->

	<!-- 主体【开始】 -->
	<div class="body_bg" style="padding-bottom: 30px;">
		<div class="base">
			<!-- 导航栏【开始】 -->
			<div class="navigation">
				<span class="font_g9"> <a href="index.jhtml">我的去哪玩</a> > <a href="myorder.jhtml?type=init">我的订单</a>
					> <a class="cur" href="javascript:void(0);">订单详情</a> <span>
			</div>
			<!-- 导航栏【结束】 -->
			<div class="content">
				<!-- 订单详情>> -->
				<div class="box">
					<div class="box_title">
						<h3>订单详情</h3>
					</div>
					<!-- 订单信息>> -->
					<div class="view-box">
						<h4>游客信息</h4>
						<p class="p-info first">
							联系人姓名： <strong>${orderDetailForm.contact_one.name }</strong>
							联系人手机： <strong>${orderDetailForm.contact_one.phone }</strong>
						</p>
						<table class="p-table">
							<tbody>
								<tr>
									<th>姓名</th>
									<th>手机</th>
									<th>性别</th>
									<th>邮箱</th>
									<th>出生年月</th>
								</tr>
								<c:forEach items="${orderDetailForm.contact_many}" var="c_play">
									<tr>
										<td>${c_play.name}</td>
										<td>${c_play.phone}</td>
										<td>
											<c:if test="${c_play.sex==true}">男</c:if>
											<c:if test="${c_play.sex==false}">女</c:if>
										</td>
										<td>${c_play.email}</td>
										<td>${c_play.birthday}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<h4>订单信息</h4>
						<ul class="order-info">
							<li>订单号：${orderDetailForm.order.orderNo}</li>
							<li>订单状态：${orderDetailForm.order.state.value}</li>
							<li>下单时间：${orderDetailForm.order.create_time}</li>
							<li>购  买  人：${orderDetailForm.order.user.name}</li>
							<div class="clear"></div>
						</ul>
						<!-- 订单列表>> -->
						<div class="order_list-box">
							<table class="r-table">
								<thead>
									<tr class="thead">
										<th class="product-name">产品名称</th>
										<th class="play-date">游玩日期</th>
										<th class="price">现售价</th>
										<th class="price">游玩人数</th>
										<th class="other">小计</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>【${orderDetailForm.order.trip.title}】</td>
										<td>${orderDetailForm.order.start_time}</td>
										<td>${orderDetailForm.order.total_price/orderDetailForm.order.num}</td>
										<td>${orderDetailForm.order.num}</td>
										<td>${orderDetailForm.order.total_price}</td>
									</tr>
								</tbody>
							</table>
							<!--赠品开始 -->
							<!--赠品结束 -->
							<p class="price-sum">
								<b>订单结算总额：</b>
								<dfn>
									¥<i class="f24">${orderDetailForm.order.total_price}</i>
								</dfn>
							</p>
						</div>
						<!-- <<订单列表 -->
					</div>
				</div>
			</div>
			<div class="buttom">
				<div class="buttom_list">
					<b class="blt buttom_list_tit1">订购指南</b> <a rel="nofollow" href="#">门票订购流程是怎样的？</a>
					<a rel="nofollow" href="#">二维码使用时有什么注意事项？</a> <a rel="nofollow"
						href="#">可以预定多长时间的门票？</a> <a rel="nofollow" href="#">想要修改订单，怎么操作？</a>
				</div>
				<div class="buttom_list">
					<b class="blt buttom_list_tit2">注册与登录</b> <a rel="nofollow"
						href="#">手机没有收到激活/验证短信怎么办？</a> <a rel="nofollow" href="#">怎样才能成为驴妈妈会员？</a>
					<a rel="nofollow" href="#">在哪登录会员帐号？</a> <a rel="nofollow" href="#">如何修改我的帐号密码？</a>
				</div>
				<div class="buttom_list">
					<b class="blt buttom_list_tit3">支付与取票</b> <a rel="nofollow"
						href="#">付款方式有哪些？</a> <a rel="nofollow" href="#">在线支付安全吗？</a> <a
						rel="nofollow" href="#">为什么支付不成功？</a> <a rel="nofollow" href="#">想要退款，应该怎么做？</a>
				</div>
				<div class="buttom_list" style="border: medium none;">
					<b class="blt buttom_list_tit4">沟通与订阅</b> <a rel="nofollow"
						href="#">我想咨询门票，应该怎么办？</a> <a rel="nofollow" href="#">我有意见，在哪可以提？</a>
					<a rel="nofollow" href="#">我想投诉，应该怎么反映？</a> <a rel="nofollow"
						href="#">我是景区负责人，怎么联系驴妈妈？</a>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/order_detail.js"></script>
	<!-- 主体【结束】 -->

	<!-- footer【开始】 -->
	<!-- 引入尾部开始 -->
	<iframe src="./common/footer.jsp"
		style="width: 100%; height: 650px; border: 0; overflow: hidden;"></iframe>
	<!-- 引入尾部结束 -->
	<script type="text/javascript">
		var x = -200;
		var y = -50;
		var row = 0;
		var col = 0;
		$(".end_base ul.ico li.i").each(function(e) {
			row = parseInt(e / 4);
			col = e % 4;
			var p_x = -200;
			var p_y = -50;
			p_x = p_x - 100 * col;
			p_y = p_y - 39 * row;
			var position = p_x + "px " + p_y + "px";
			$(this).css("background-position", position);
		})
	</script>
	<!-- footer【结束】 -->
</body>
</html>