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
				<li class="ui-step2 cur">
					<div class="step-arrow">
						<i class="arrow_r1"></i> <i class="arrow_r2"></i>
					</div> <span class="ui-step-text">2.选择付款方式付款</span>
				</li>
				<li class="ui-step3 ui-step-end">
					<div class="step-arrow"></div> <span class="ui-step-text">3.预订成功</span>
				</li>
			</ol>
			<!-- 步骤【结束】 -->

			<!-- 选择付款方式【开始】 -->
			<div class="panel2">
				<div class="order-main paynew">
					<div class="main_pay">
						<div class="hr_a"></div>
						<div class="c-title" id="orderinfo">
							<h3>您预订：${vo.orderno }&nbsp;${vo.title }</h3>
							<div class="pay_total-price">
								<span class="pay_price-num">
									<dfn>${vo.price }</dfn> 元
								</span> 
								<strong>订单金额：</strong>
							</div>
						</div>
					</div>
					<!-- 支付方式 -->
					<div class="paynewbox">
						<div class="paytips">
							<p>友情提示：您的预订信息已提交。</p>
						</div>
						<div class="dot_line"></div>
						<h4 class="pay-price">
							您还需继续付款
							<dfn>
								<i>${vo.price }</i>
							</dfn>
							元
						</h4>
					</div>
					<!-- 选择支付方式 -->
					<div class="payment">
						<div class="pay-title">
							<h4 class="pay-type">付款方式：</h4>
							<ul class="tabnav order-tabnav">
								<li class="selected"><a href="javascript:void(0);">网银支付</a></li>
								<li class=""><a href="javascript:void(0);">支付平台</a></li>
							</ul>
						</div>
						<div class="tab-switch payment-list">
							<div class="tabcon selected">
								<div class="xhcontent">
									<ul class="bank-list">
										<li><label class="new_pay_posBox"><input
												class="input-radio" checked="check" name="bank1"
												type="radio"><i class="bank icbc" title="工商银行"></i></label></li>
										<li><label class="new_pay_posBox"><input
												class="input-radio" name="bank1" type="radio"><i
												class="bank abc" title="中国农业银行"></i></label></li>
										<li><label class="new_pay_posBox"><input
												class="input-radio" name="bank1" type="radio"><i
												class="bank ccb" title="中国建设银行"></i></label></li>
										<li><label class="new_pay_posBox"><input
												class="input-radio" name="bank1" type="radio"><i
												class="bank cmb" title="招商银行"></i></label></li>
										<li><label class="new_pay_posBox"><input
												class="input-radio" name="bank1" type="radio"><i
												class="bank boc" title="中国银行"></i></label></li>
										<li><label class="new_pay_posBox"><input
												class="input-radio" name="bank1" type="radio"><i
												class="bank comm" title="交通银行"></i></label></li>
										<li><label class="new_pay_posBox"><input
												class="input-radio" name="bank1" type="radio"><i
												class="bank ceb" title="光大银行"></i></label></li>
										<li><label class="new_pay_posBox"><input
												class="input-radio" name="bank1" type="radio"><i
												class="bank spdb" title="上海浦东发展银行"></i></label></li>
										<li><label class="new_pay_posBox"><input
												class="input-radio" name="bank1" type="radio"><i
												class="bank cgb" title="广发银行"></i></label></li>
										<li><label class="new_pay_posBox"><input
												class="input-radio" name="bank1" type="radio"><i
												class="bank ecitic" title="中信银行_WEB"></i></label></li>
										<li><label class="new_pay_posBox"><input
												class="input-radio" name="bank1" type="radio"><i
												class="bank cib" title="兴业银行"></i></label></li>
										<li><label class="new_pay_posBox"><input
												class="input-radio" name="bank1" type="radio"><i
												class="bank cmbc" title="民生银行"></i></label></li>
										<li><label class="new_pay_posBox"><input
												class="input-radio" name="bank1" type="radio"><i
												class="bank bjbank" title="北京银行"></i></label></li>
										<li><label class="new_pay_posBox"><input
												class="input-radio" name="bank1" type="radio"><i
												class="bank hzbank" title="杭州银行"></i></label></li>
										<li><label class="new_pay_posBox"><input
												class="input-radio" name="bank1" type="radio"><i
												class="bank shbank" title="上海银行"></i></label></li>
										<li><label class="new_pay_posBox"><input
												class="input-radio" name="bank1" type="radio"><i
												class="bank bjrcb" title="北京农村商业银行"></i></label></li>
										<li><label class="new_pay_posBox"><input
												class="input-radio" name="bank1" type="radio"><i
												class="bank pingan" title="平安银行"></i></label></li>
										<li><label class="new_pay_posBox"><input
												class="input-radio" name="bank1" type="radio"><i
												class="bank nbcb" title="宁波银行"></i></label></li>
										<li><label class="new_pay_posBox"><input
												class="input-radio" name="bank1" type="radio"><i
												class="bank psbc" title="中国邮政储蓄银行"></i></label></li>
										<div class="clear"></div>
									</ul>
								</div>
								<div class="order-btn">
									<a href="myorder.jhtml?type=payForOrder&id=${vo.id }">
										<button class="pbtn pbtn-big pbtn-orange">&nbsp;&nbsp;确认付款&nbsp;&nbsp;</button>
									</a>
								</div>
							</div>
							<div class="tabcon">
								<div class="xhcontent">
									<ul class="bank-list">
										<li><label class="new_pay_posBox"><input
												class="input-radio" checked="check" name="bank2"
												type="radio"><i class="bank alipay" title="支付宝"></i></label></li>
										<li><label class="new_pay_posBox"><input
												class="input-radio" name="bank2" type="radio"><i
												class="bank weixinpay" title="微信WEB扫码支付"></i></label></li>
										<li><label class="new_pay_posBox"><input
												class="input-radio" name="bank2" type="radio"><i
												class="bank alipay" title="支付宝"></i></label></li>
										<li><label class="new_pay_posBox"><input
												class="input-radio" name="bank2" type="radio"><i
												class="bank weixinpay" title="微信WEB扫码支付"></i></label></li>
										<li><label class="new_pay_posBox"><input
												class="input-radio" name="bank2" type="radio"><i
												class="bank unionpay-quick" title="银联快捷"></i></label></li>
										<li><label class="new_pay_posBox"><input
												class="input-radio" name="bank2" type="radio"><i
												class="bank tenpay" title="财付通"></i></label></li>
										<li><label class="new_pay_posBox"><input
												class="input-radio" name="bank2" type="radio"><i
												class="bank tenpay-quick" title="财付通快捷"></i></label></li>
										<li><label class="new_pay_posBox"><input
												class="input-radio" name="bank2" type="radio"><i
												class="bank baidupay" title="百度钱包WEB支付"></i></label></li>
										<li><label class="new_pay_posBox"><input
												class="input-radio" name="bank2" type="radio"><i
												class="bank alipayCode" title="支付宝扫码"></i></label></li>
										<li><label class="new_pay_posBox"><input
												class="input-radio" name="bank2" type="radio"><i
												class="bank alipay-quick" title="支付宝快捷"></i></label></li>
										<li><label class="new_pay_posBox"><input
												class="input-radio" name="bank2" type="radio"><i
												class="bank unionpay2" title="银联"></i></label></li>
										<li><label class="new_pay_posBox"><input
												class="input-radio" name="bank2" type="radio"><i
												class="bank wlt" title="万里通"></i></label></li>
										<div class="clear"></div>
									</ul>
								</div>
								<div class="order-btn">
									<a href="myorder.jhtml?type=payForOrder&id=${vo.id }">
										<button class="pbtn pbtn-big pbtn-orange">&nbsp;&nbsp;确认付款&nbsp;&nbsp;</button>
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 选择付款方式【结束】 -->

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