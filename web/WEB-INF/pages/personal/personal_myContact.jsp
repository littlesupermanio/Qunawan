<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	<title>个人空间-常用游客</title>
	<link type="text/css" rel="stylesheet"
		href="css/personalCss.css" />
	<link rel="stylesheet" type="text/css"
		href="css/img_account.css">
	<script type="text/javascript"
		src="js/jquery/jquery-1.11.0.min.js"></script>
	<script type="text/javascript"
		src="js/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="js/pages/personal/frame/birthday.js"></script>
	<script src="js/common.js"
		type="text/javascript"></script>
	<script type="text/javascript"
		src="js/pages/personal/personal_information.js"></script>
	<script type="text/javascript"
		src="js/pages/personal/personal_contact.js"></script>
	<base href="<%=basePath%>" />
</head>
<body>

	<!-- 个人空间 开始-->
	<div class="content_infor">
		<a href="index.jhtml">我的去哪玩</a>> <a
			href="personalinfo.jhtml?type=init">个人空间</a>>
		<a id="target_title3" href="javascript:;">我的联系人</a>
	</div>
	<div class="content_all">

		<!-- 左边  菜单栏开始-->
	
		<div class="content_left">
	<div class="content_left_first">
		<c:choose>
			<c:when test="${empty user.img_path }">
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
			<li ><a
				href="${pageContext.request.contextPath }/myorder.jhtml?type=init"><span
					class="icon  icon_my_re_list "> </span> <span
					class="position_my_re_list">我的约单</span> </a></li>
			<li><a
				href="${pageContext.request.contextPath }/personalinfo.jhtml?type=init"><span
					class="icon  icon_my_re_list  icon_personal"> </span> <span
					class="position_my_re_list">个人信息</span></a></li>
			<li><a
				href="${pageContext.request.contextPath }/updatepwd.jhtml?type=init"><span
					class="icon  icon_my_re_list  icon_password" style=""> </span> <span
					class="position_my_re_list">密码修改</span></a></li>
			<li ><a
				href="${pageContext.request.contextPath }/mycomment.jhtml?type=init"><span
					class="icon  icon_my_re_list icon_comment " style=""> </span> <span
					class="position_my_re_list">我的点评</span></a></li>
			<span class="hr_css">
				<hr style="width: 120px; border-top: 1px #ccc solid;" />
			</span>
			<li class="backgroundClass"><a
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

			<!-- 右侧    常用游客信息  开始-->
			<div class="content_right bordernone " id="usually_tourist_content">
				<span class="changePassWord">常用游客信息</span>
				<hr class="hr_css_red" />
				<div class="add_often_tourist">
					<a href="javascript:;">新增常用游客</a>
				</div>
				<span style="color: red" id="volidate_message"></span>
				<div class="tourist">
					<form id="contactForm" name="contactForm" action="" method="post">
						<ul>
							<li><label> <span class="redcolor">*</span> 中文姓名：
							</label> <input type="text" placeholder="请输入中文姓名" name="c_name"
								id="cnName" class="input_style" onBlur="checkCNName()" /> <span
								id="cnNameMsg"> </span></li>
							<li><label> <span class="redcolor">*</span>手机号：
							</label> <input type="text" placeholder="请输入手机号码" name="c_mobile"
								id="phone" class="input_style" onBlur="checkTel()" /> <span
								id="phoneMsg"> </span></li>
							<li><label> 邮箱： </label> <input type="text"
								placeholder="请输入邮箱地址" name="c_email" id="emails"
								class="input_style" /> <span id="emailsMsg"> </span></li>
							<li><label> <span class="redcolor">*</span>身份证：
							</label> <input type="text" placeholder="请输入身份证号" name="c_cardno"
								id="cardId" class="input_style" onBlur="checkCardId()" /> <span
								id="cardIdMsg"></span></li>
							<li><label> 性别：</label> <select id="c_sex" name="c_sex"
								class="bir_type">
									<option value="1">男</option>
									<option value="0">女</option>
							</select></li>
							<li><label>生日：</label> <select id="sel_year"
								name="c_sel_year" rel=""></select> <select id="sel_month"
								name="c_sel_month" rel=""></select> <select id="sel_day"
								name="c_sel_day" rel=""></select></li>
							<li><label> </label> <input type="button"
								class="save_button" id="save_tourist"
								onclick="checkAddContact()" value="确   定" /></li>
						</ul>
					</form>
				</div>

				<!--删除游客弹窗 开始-->
				<div class="del_dialog" id="del_box">
					<div class="close-this" id="close"></div>
					<div class="del_dialog_box">
						<p style="font-weight: 900;">您确定要删除吗？</p>
						<p>删除后，信息将不可恢复！</p>
						<div class="del_dialog_btn">
							<a href="javascript:void(0);" class="ui_btn  del_btn_ok">确&nbsp; 定</a> <a
								href="javascript:void(0);" class="ui_btn  del_btn_cancel">取&nbsp; 消</a>
						</div>
					</div>
				</div>
				<!--删除弹窗 结束-->
				<div class="visitor-list">
					<table class="lv-table visitor-table">
						<thead>
							<tr class="thead">
								<th class="w3">中文姓名</th>
								<th class="w3">手机号码</th>
								<th class="w3">证件类型</th>
								<th class="w1">证件号码</th>
								<th class="w3">邮箱</th>
								<th class="w3">性别</th>
								<th class="w3">出生日期</th>
								<th class="w3">旅客类型</th>
								<th class="w3">操作</th>
							</tr>
						</thead>
							<tbody class="tbody"> <!-- id="contactlist" -->
							<c:forEach items="${ formList}" var="value" >
								<tr id='${ value.id}'>
									<td>${value.name }</td>
									<td>${value.phone}</td>
									<td>身份证</td>
									<td>${value.cardno}</td>
									<td>${value.email}</td>
									<c:if test="${value.sex=='true' }">
										<td>男</td>
									</c:if>
									<c:if test="${value.sex=='false' }">
										<td>女</td>
									</c:if>
									<td>${value.birthday}</td>
									<td>成人</td>
									<td><a href="javascript:void(0);" class="modify" onclick="modifyrow(this)">修改</a>
										<a href="javascript:void(0);"class="delete" onclick="deleterow(this)">删除</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="Pages"> <!-- id="contactPages" --> 
					<c:if test="${cur!=1 }">
						<a href="mycontact.jhtml?type=getContacts&page=${cur-1}" title="上一页" class="PrevPage">上一页</a>
					</c:if>
					<c:if test="${cur==1 }">
						<a href="javascript:;" title="首页" class="PrevPage">首页</a>
					</c:if>
					
					<c:forEach var="i" begin="1" end="${pageCount}">
						<c:if test="${i==cur}">
								<a href="mycontact.jhtml?type=getContactst&page=${i}" class="pagesel" style="background-color:#DF1A7A;
							color:white" id="orderPage" onclick="cur(${i})">${i}</a>
						</c:if>
						<c:if test="${i!=cur}">
								<a href="mycontact.jhtml?type=getContacts&page=${i}" class="pagesel" 
							 id="orderPage" onclick="cur(${i})">${i}</a>
						</c:if>
					</c:forEach>
					<c:if test="${cur!=pageCount }">
						<a href="mycontact.jhtml?type=getContacts&page=${cur+1}" title="下一页" class="NextPage" onclick="next()">下一页</a>
					</c:if>
					<c:if test="${cur==pageCount }">
						<a href="javascript:;" title="尾页" class="NextPage" onclick="next()">尾页</a>
					</c:if>
				</div>

				<!-- <游客列表 -->
			</div>
			<!-- 右侧    常用游客信息  结束 -->
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