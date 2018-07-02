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
	<title>个人空间-修改密码</title>
	<link type="text/css" rel="stylesheet"
		href="css/personalCss.css" />
	<link type="text/css" rel="stylesheet"
		href="css/img_account.css" />
	<script type="text/javascript"
		src="js/jquery/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="js/pages/personal/personal_information.js"></script>
	<base href="<%=basePath%>" />
</head>
<body>
	<!-- 个人空间 开始-->
	<div class="content_infor">
		<a href="index.jhtml">我的去哪玩</a>> <a
			href="personalinfo.jhtml?type=init">个人空间</a>> <a id="target_title3"
			href="javascript:;">密码修改</a>
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
			<li ><a
				href="${pageContext.request.contextPath }/personalinfo.jhtml?type=init"><span
					class="icon  icon_my_re_list  icon_personal"> </span> <span
					class="position_my_re_list">个人信息</span></a></li>
			<li class="backgroundClass"}><a
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
			<li ><a
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
			<!-- 右侧    密码修改 开始-->
			<div class="content_right " id="password_detail_content">
				<span class="changePassWord">登录密码修改</span>
				<hr class="hr_css_red" />
				<div class="div_yellow">
					<span><img src="img/tips-ico.jpg" class="img_pass" />修改登录密码后，原密码将不能用来登录。</span>
				</div>
				
				<div class="change_password_detail">
					<form
						action="updatepwd.jhtml?type=modifyPwd"
						method="post">
						<ul>
							<c:if test="${err_msg != null }">
								<li><span style="color:red">${err_msg }</span></li>
							</c:if>
							<li><label> <span class="redcolor">*</span> 请输入旧密码：
							</label><input id="oldPassWord" name="oldPassWord" class="password_css"
								type="password" /> <span id="passWordMsg"
								class="password_msg_css"> <img src="img/tips-ico.jpg"
									class="img_pass " /> 请输入旧密码
							</span></li>
							<li><label> <span class="redcolor">*</span> 请输入新密码：
							</label><input id="newPassWord" name="newPassWord" class="password_css"
								type="password" /> <span id="newWordMsg"
								class="password_msg_css"> <img src="img/tips-ico.jpg"
									class="img_pass " /> 请输入新密码
							</span></li>
							<li><label> <span class="redcolor">*</span> 请确认新密码：
							</label><input id="newPassWordt" name="newPassWordt" class="password_css"
								type="password" /> <span id="newWordMsgt"
								class="password_msg_css"> <img src="img/tips-ico.jpg"
									class="img_pass " /> 请再次确认新密码
							</span></li>
							<li><input type="button" class="save_button save_left"
								onclick="javascript:if(checkPassWord())submit()" value="保  存" /></li>
						</ul>
					</form>
				</div>
			</div>
			<!-- 右侧    密码修改 结束 -->

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