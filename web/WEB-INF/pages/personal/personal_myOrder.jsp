<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="/common/header.jsp"></jsp:include>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>个人空间-我的约单</title>
	<link type="text/css" rel="stylesheet"
		href="css/personalCss.css" />
	<script type="text/javascript"
		src="js/jquery/jquery-1.11.0.min.js"></script>
	<script type="text/javascript"
		src="js/pages/personal/personal_information.js"></script>
	<script type="text/javascript"
		src="js/pages/personal/personal_order.js"></script>
	<base href="<%=basePath%>" />
</head>
<body>
	<!-- 个人空间 开始-->
	<div class="content_infor">
		<a href="index.jhtml">我的去哪玩</a>> <a
			href="personalinfo.jhtml?type=init">个人空间</a>> <a id="target_title3"
			href="javascript:;">我的约单</a>
	</div>
	<div class="content_all">
		
		<!-- 左边  菜单栏开始-->
	
		
		<div class="content_left">
	<div class="content_left_first">
		<c:choose>
			<c:when test="${empty user.img_path}">
				<img src="img/headimg.png" class="img_head" />
			</c:when>
			<c:otherwise>
				<img src="${pageContext.request.contextPath }/user_img/${user.img_path}"
					class="img_head" />
			</c:otherwise>
		</c:choose>
		<p>编辑我的资料</p>
	</div>
	<div class="content_left_two">
		<ul>
			<li class="backgroundClass"><a
				href="${pageContext.request.contextPath }/myorder.jhtml?type=init"><span
					class="icon  icon_my_re_list "> </span> <span
					class="position_my_re_list">我的约单</span> </a></li>
			<li ><a
				href="${pageContext.request.contextPath }/personalinfo.jhtml?type=init"><span
					class="icon  icon_my_re_list  icon_personal"> </span> <span
					class="position_my_re_list">个人信息</span></a></li>
			<li ><a
				href="${pageContext.request.contextPath }/updatepwd.jhtml?type=init"><span
					class="icon  icon_my_re_list  icon_password" style=""> </span> <span
					class="position_my_re_list">密码修改</span></a></li>
			<li><a
				href="${pageContext.request.contextPath }/mycomment.jhtml?type=init"><span
					class="icon  icon_my_re_list icon_comment " style=""> </span> <span
					class="position_my_re_list">我的点评</span></a></li>
			<span class="hr_css">
				<hr style="width: 120px; border-top: 1px #ccc solid;" />
			</span>
			<li><a
				href="${pageContext.request.contextPath }/mycontact.jhtml?type=getContacts"><span
					class="icon  icon_my_re_list iconusually" style=""> </span> <span
					class="position_my_re_list">常用游客信息</span></a></li>
			<li style="cursor:default"><a href="javascript:;" style="cursor:default"><span 
					class="icon  icon_my_re_list icon_card " style="cursor: default;color: gray;"> </span> <span style="cursor:default; color: gray;"
					class="position_my_re_list">礼品卡</span></a></li>
			<li style="cursor: default;text-decoration:none;"><a href="javascript:;" style="cursor:default"><span
					class="icon  icon_my_re_list iconmoney " style="cursor: default;color: gray;"> </span> <span style="cursor: default;color: gray;"
					class="position_my_re_list">我的积分</span></a></li>
			<li style="cursor:default"><a href="javascript:;" style="cursor:default"><span
					class="icon  icon_my_re_list icon_email " style="cursor: default;color: gray;"> </span> <span style="cursor: default;color: gray;"
					class="position_my_re_list">邮件订阅</span></a></li>
			<span class="hr_css">
				<hr style="width: 120px; border-top: 1px #ccc solid;" />
			</span>
			<li style="cursor:default"><a href="javascript:;" style="cursor:default"><span
					class="icon  icon_my_re_list icon_vip" style="cursor: default;color: gray;"> </span> <span style="cursor: default;color: gray;"
					class="position_my_re_list ">会员俱乐部</span></a></li>
			<li style="cursor:default"><a href="javascript:;" style="cursor:default"><span
					class="icon  icon_my_re_list icon_way" style="cursor: default;color: gray;"> </span> <span style="cursor: default;color: gray;"
					class="position_my_re_list">我的攻略</span></a></li>
			<li style="cursor:default"><a href="javascript:;" style="cursor:default"><span
					class="icon  icon_my_re_list icon_collection" style="cursor: default;color: gray;"> </span> <span style="cursor: default;color: gray;"
					class="position_my_re_list" style="cursor:default">我的收藏</span></a></li>
			<li style="cursor:default"><a href="javascript:;" style="cursor:default"><span
					class="icon  icon_my_re_list icon_my_tour" style="cursor: default;color: gray;"> </span> <span style="cursor: default;color: gray;"
					class="position_my_re_list">我的游记</span></a></li>
		</ul>
	</div>
</div>
		
		<!-- 左边  菜单栏结事-->

		<!-- 右侧  选项卡开始-->
		<div class="content_right_pa">
			<!-- 右侧    我的约单 开始-->
			<div class="content_right bordernone" id="my_re_list_content">
				<span class="changePassWord">我的约单</span>
				<div class="re_my_detail">
					<div class="feature_spoc">
					<input type="hidden" id="length" value="${fn:length(orderFormList) }">
						<table id="ordersTable">
							<thead>
								<tr class="col-name">
									<th class="product_name text_left">产品名称</th>
									<th class="price">价格</th>
									<th class="number">人数</th>
									<th class="other">操作</th>
									<th class="other">状态</th>
								</tr>
								<tr class="tr_width  margin-right5 text_left">
									<td colspan="5"><span class="text_color ">待出行的订单状态在出行日会自动更新为游玩中。如有疑问，请致电客服1010-6060咨询。</span>
									</td>
								</tr>
								<tr class="sep-row">
									<td colspan="5"></td>
								</tr>
							</thead>
							<!-- 一个tbody 一个订单-->
							<tbody>
							<c:forEach items="${orderFormList}" var="item" varStatus="varStatus">
								<tr class="tr_width margin-right5  text_left">
									<td colspan="5">
										<label class="text_middle">订单号：</label>
										<span class="text_middle margin-right15">${item.orderno}</span>
										<label class="text_middle">&nbsp;&nbsp;&nbsp;时间：</label>
										<span class="text_middle margin-right15">${fn:substring(item.create_time, 0, fn:length(item.create_time)-2)}</span>
									</td>
								</tr>
								<tr>
									<td>
										<p>${item.content}</p>
									</td>
									<td class="text_color_orange">${item.price}</td>
									<td>${item.person_num}</td>
									<td>
										<span class="text_color span_width ">
											<a class="text_color" href=
												<c:if test="${ item.state.keying == 0}">
													'myorder.jhtml?type=showPayFor&id=${ item.orderid}'
												</c:if>  
												<c:if test="${ item.state.keying == 1}">
													'#'
												</c:if> 												
												<c:if test="${ item.state.keying == 3}">
													'mycomment.jhtml?type=init'
												</c:if>>
												<c:if test="${ item.state.keying == 0}">
													付款
												</c:if>  
												<c:if test="${ item.state.keying == 1}">
													退款
												</c:if> 												
												<c:if test="${ item.state.keying == 3}">
													评价
												</c:if> 
											</a></span><br />
										<span class="text_color span_width"><a  href="myorder.jhtml?type=initOrderDetail&order_id=${ item.orderid}" target="view_window">订单详情</a></span>
									</td>
									<td><span class="text_color span_width  ">
												<c:if test="${ item.state.value=='待评价'}">
													<a title="为了方便测试,付款后直接进入待评价状态" href="javascript:;">
												</c:if>
												<c:if test="${ item.state.value!='待评价'}">
													<a href="javascript:;">
												</c:if>${item.state.value}</a>
										</span> <br />
										<span class="text_color_blue " id="${ item.orderno}"></span>
										<input type="hidden" value="${item.orderno }" id="timeid${varStatus.index }">
										<input type="hidden" value="${item.start_time}" id="start_time${varStatus.index }">
										<input type="hidden" value="${item.create_time }" id="create_time${varStatus.index }">
										<input type="hidden" value="${item.state.keying }" id="keying${varStatus.index }">
										<input type="hidden" value="${item.totalDays }" id="totaldays${varStatus.index }">
										<span id="time"></span>
									</td>
								</tr>
								<tr class="sep-row">
									<td colspan="5"></td>
								</tr>
							</c:forEach>
						</tbody>
							
						</table>
					</div>
					<!-- 订单分页页码 -->
					
					<div class="Pages" ><!-- id="ordersPages" -->
					<c:if test="${cur!=1 }">
						<a href="myorder.jhtml?type=init&page=${cur-1}" title="上一页" class="PrevPage">上一页</a>
					</c:if>
					<c:if test="${cur==1 }">
						<a href="javascript:;" title="首页" class="PrevPage">首页</a>
					</c:if>
					
					<c:forEach var="i" begin="1" end="${pageCount}">
						<c:if test="${i==cur}">
								<a href="myorder.jhtml?type=init&page=${i}" class="pagesel" style="background-color:#DF1A7A;
							color:white" id="orderPage" onclick="cur(${i})">${i}</a>
						</c:if>
						<c:if test="${i!=cur}">
								<a href="myorder.jhtml?type=init&page=${i}" class="pagesel" 
							 id="orderPage" onclick="cur(${i})">${i}</a>
						</c:if>
					</c:forEach>
					<c:if test="${cur!=pageCount }">
						<a href="myorder.jhtml?type=init&page=${cur+1}" title="下一页" class="NextPage" onclick="next()">下一页</a>
					</c:if>
					<c:if test="${cur==pageCount }">
						<a href="javascript:;" title="尾页" class="NextPage" onclick="next()">尾页</a>
					</c:if>
					</div>
			
				</div>
			</div>
			<!-- 右侧    我的约单 结束 -->
		</div>
		<!-- 右边  选项卡结束-->
	</div>
	<!-- 个人空间 结束-->

	<!-- 引入尾部开始 -->
	<iframe src="common/footer.jsp"
		style="width: 100%; height: 650px; border: 0; overflow: hidden;"></iframe>
	<!-- 引入尾部结束 -->

</body>
</html>
