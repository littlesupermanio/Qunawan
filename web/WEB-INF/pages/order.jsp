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
				<li class="ui-step1 ui-step-start cur">
					<div class="step-arrow">
						<i class="arrow_r1"></i> <i class="arrow_r2"></i>
					</div> <span class="step-text">1.填写订单信息</span>
				</li>
				<li class="ui-step2">
					<div class="step-arrow">
						<i class="arrow_r1"></i> <i class="arrow_r2"></i>
					</div> <span class="ui-step-text">2.选择付款方式付款</span>
				</li>
				<li class="ui-step3 ui-step-end">
					<div class="step-arrow"></div> <span class="ui-step-text">3.预订成功</span>
				</li>
			</ol>
			<!-- 步骤【结束】 -->

			<div class="panel1">
				<form id="putOrderForm" action="myorder.jhtml?type=putOrder" method="post">
				
				<!-- 订单信息1——详情【开始】 -->
				<!-- 实训场景020：订单确认信息  引用自定义标签 【start】-->
				<my:orderInfoTag trip="${put_order_trip}" time="${put_order_time }"
					num="${put_order_num}">
				</my:orderInfoTag>
				<!-- 实训场景020：订单确认信息  引用自定义标签 【end】-->
				<%-- <div class="order_box border_3 position_r">
					<h1 class="order_name">
						<span>${put_order_trip.title }</span>
					</h1>
					<!-- 订单信息 -->
					<dl class="order_line">
						<dt>国内游</dt>
						<dd>
							<table class="pro_table">
								<tbody>
									<tr>
										<th width="70%" style="text-align: left">产品信息</th>
										<th width="20%" style="text-align: left">游玩时间</th>
										<th width="10%">人数</th>
									</tr>
									<tr>
										<td>${put_order_trip.s_title }</td>
										<td><fmt:formatDate value="${put_order_time }"
												pattern="yyyy-MM-dd" /></td>
										<td style="text-align: center">${put_order_num }</td>
									</tr>
								</tbody>
							</table>
						</dd>
					</dl>
					<!-- 附加商品 -->
					<div class="clear"></div>
				</div> --%>
				<!-- 订单信息1——详情【结束】 -->

				<!-- 订单信息2——联系人信息【开始】 -->
				<div class="order_box">
					<!--联系人信息-->
					<h3 class="order_title border_b2">联系人信息</h3>
					<div id="user_info" class="user_info">
					
						<!-- 实训场景021：购买人信息确认  自定义动态标签 【start】-->
						
						<own:orderOwnTag real_name="${user.real_name }" phone="${user.phone }" email="${user.email }"/>
						<input type="hidden" id="u_name" value="${user.real_name }" />
						<input type="hidden" id="u_phone" value="${user.phone }" />
						<input type="hidden" id="u_email" value="${user.email }" />
						
						<!-- 实训场景021：购买人信息确认  自定义动态标签 【end】-->
						
						<%-- <dl class="user_dl">
							<dt>
								<span class="red">*</span>姓名：
							</dt>
							<dd class="error_show">
								<label> <c:choose>
										<c:when test="${empty real_name }">
									未设置
								</c:when>
										<c:otherwise>
									${real_name }
								</c:otherwise>
									</c:choose>
								</label>
							</dd>
						</dl>
						<dl class="user_dl">
							<dt>
								<span class="red">*</span>手机号码：
							</dt>
							<dd class="error_show">
								<label><c:choose>
										<c:when test="${empty phone }">
									未设置
								</c:when>
										<c:otherwise>
									${phone }
								</c:otherwise>
									</c:choose></label>
							</dd>
						</dl>
						<dl class="user_dl">
							<dt>
								<span class="red">*</span>邮箱地址：
							</dt>
							<dd class="error_show">
								<label><c:choose>
										<c:when test="${empty email }">
									未设置
								</c:when>
										<c:otherwise>
									${email }
								</c:otherwise>
									</c:choose></label>
							</dd>
						</dl> --%>
						<h5 class="person_title">购买人</h5>
					</div>
					<div class="dot_line"></div>
					<!-- 紧急联系人列表选中 -->
					<div class="contactlist" id="em_contactlist">
						<ul>
							<li id="em_contactlist_li">
								<c:forEach items="${put_order_contactlist }" var="contact" varStatus="i">
								<span>
									<input type="radio" name="emer_id" id="contact${i.index }" value="${contact.id }" 
										onchange="selectEmContact(this,'${contact.name }','${contact.phone}')" />&nbsp;
									<label for="contact${i.index }">${contact.name }</label>
								</span>
								</c:forEach> 
								<span id="moreEM_Contacts" class="more"><a href="javascript:void(0);" onclick="getAllContact('em')">更多</a></span>
							</li>
							<div class="clear"></div>
						</ul>
						<div class="clear"></div>
					</div>
					<!--紧急联系人信息-->
					<div id="userinfoDiv"></div>
					<div>
						<input type="hidden" name="em_id" id="em_id" value="-1">
						<div class="user_info no_bd">
							<dl class="user_dl">
								<dt>
									<span class="red">*</span>姓名：
								</dt>
								<dd>
									<input class="input realname" onchange="checkRadio()" id="em_name" name="em_name" type="text" placeholder="姓名" maxlength="16" /> 
									<span class="error_text"><i class="tip-icon tip-icon-error"></i><label>请输入姓名</label></span>
								</dd>
							</dl>
							<dl class="user_dl">
								<dt>
									<span class="red">*</span>手机号码：
								</dt>
								<dd>
									<input class="input phone" onchange="checkRadio()" id="em_phone" name="em_phone" maxlength="11" type="text" placeholder="手机号码" maxlength="11">
									<span class="prompt_text">此手机号为接收短信所用，作为订购与取票凭证，请准确填写。</span> 
									<span class="error_text"><i class="tip-icon tip-icon-error"></i>手机号码不正确</span>
								</dd>
							</dl>
							<h5 class="person_title">紧急联系人</h5>
						</div>
					</div>
					<div class="dot_line"></div>
					<!-- 游玩人列表勾选 -->
					<div class="contactlist" id="pl_contactlist">
						<ul id="count${put_order_num }">
							<li id="pl_contactlist_li">
								<c:forEach items="${put_order_contactlist }" var="contact" varStatus="i">
								<span>
									<input type="checkbox" name="player_id"
										id="contactp${i.index }" value="${contact.id }"
										onchange="selectPlContact(this,'${put_order_num }','${contact.name}','${contact.phone}','${contact.cardno}')" />&nbsp;
									<label for="contactp${i.index }">${contact.name }</label>
								</span>
								</c:forEach> 
								<span id="morePL_Contacts" class="more">
									<a href="javascript:void(0);" onclick="getAllContact('pl')">更多</a>
								</span>
							</li>
						</ul>
					</div>
					<!--游玩人信息-->

					<div>
						<c:forEach begin="1" end="${put_order_num }" var="i">
							<input type="hidden" name="w_id" id="w_id${i }" value="-1">
							<div class="user_info no_bd">
								<dl class="user_dl">
									<dt>
										<span class="red">*</span>姓名：
									</dt>
									<dd>
										<input class="input realname player${i }" id="w_name${i }" name="w_name" type="text" onchange="checkEdit('${i }')" placeholder="姓名" maxlength="16"> 
										<span class="error_text"><i class="tip-icon tip-icon-error"></i>请输入姓名</span>
									</dd>
								</dl>
								<dl class="user_dl">
									<dt>
										<span class="red">*</span>手机号码：
									</dt>
									<dd>
										<input class="input phone player${i }" id="w_phone${i }" name="w_phone" maxlength="11" onchange="checkEdit('${i }')" type="text" placeholder="手机号码"> 
										<span class="prompt_text">此手机号为接收短信所用，作为订购与取票凭证，请准确填写。</span>
										<span class="error_text"><i class="tip-icon tip-icon-error"></i>手机号码不正确</span>
									</dd>
								</dl>
								<dl class="user_dl">
									<dt>
										<span class="red">*</span>身份证号码：
									</dt>
									<dd>
										<input class="input identity player${i }" id="w_cardno${i }" name="w_cardno" type="text" onchange="checkEdit('${i }')" placeholder="身份证号码" maxlength="18"> 
										<span class="error_text"><i class="tip-icon tip-icon-error"></i>请输入合法的身份证号码</span>
									</dd>
								</dl>
								<h5 class="person_title">游玩人${i }</h5>
							</div>
						</c:forEach>
					</div>



					<!-- 上车地点 -->
					<h3 class="order_tit border_b2">上车地点</h3>
					<div class="user_info">
						<dl class="user_dl name_check">
							<dt>上车地点：</dt>
							<dd>
								<input type="hidden" name="place" value="${put_order_trip.schedule.traffic.go_point }" />
								<label class="check">${put_order_trip.schedule.traffic.go_point }</label><span
									class="ts_text">发车时间：${put_order_trip.schedule.traffic.go_time }</span>
								<span class="ts_text">备注：始发站</span><br>
							</dd>
						</dl>
					</div>
				</div>
				<!-- 订单信息2——联系人信息【结束】 -->

				<!-- 订单信息3——确认订单【开始】 -->
				<div class="pay">
					<div class="pay_box" style="position: absolute;">
						<a href="javascript:void(0);" class="pay_right" onclick="valid();"> 去付款 </a>
						<div class="pay_left">
							<div class="left_box">
								<p class="pay_p1">
									应付总价:<span><small>¥</small></span><span id="total"
										style="margin-left: 0">${put_order_price }</span>
								</p>
								<p class="pay_p2">
									（包含：商品价<span id="total1">¥ ${put_order_price }</span>）
								</p>
							</div>
						</div>
					</div>
				</div>
				</form>
			</div>
			<!-- 订单信息3——确认订单【结束】 -->
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