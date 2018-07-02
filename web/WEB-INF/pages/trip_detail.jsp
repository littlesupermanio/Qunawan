<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="my" uri="http://www.oraclecsg.com/tlt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<meta charset="UTF-8">
	<title>${detail.title}</title>
	<link rel="stylesheet"
		href="css/tourist_detail.css"
		type="text/css" />
	<script type="text/javascript"
		src="js/jquery/jquery-1.11.0.min.js"></script>
	<script type="text/javascript"
		src="js/pages/detail/calendar.js"></script>
		
	<!--  引入百度地图api地址 开始 -->
	<script type="text/javascript"
		src="http://api.map.baidu.com/api?v=2.0&ak=zUyQQ3767FesfEn38bGStyYq"></script>
	<!-- ak=zUyQQ3767FesfEn38bGStyYq  在百度申请ak -->
	<script type="text/javascript"
		src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
	<link rel="stylesheet"
		href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />
	<!--  引入百度地图api地址 结束 -->
	<base href="<%=basePath%>" />
</head>
<body>
	<!-- header【开始】 -->
	<%@ include file="/common/header.jsp"%>
	<!-- header【结束】 -->

	<!-- 隐藏域，用于存储旅游产品的价格数据 -->
	<div style="display: none" id="price_data">${my:getPriceData(detail.price_list)}</div>
	<!-- 隐藏域，用于存储当前订单总价的数据 -->
	<div style="display: none" id="current_price"></div>
	<!-- 隐藏域，用于存储地图定位经纬度、缩放等级 -->
	<div style="display: none" id="location">${detail.position.p1},${detail.position.p2},${detail.position.level}</div>
	<!-- 隐藏域，用于存储用户id -->
	<div style="display: none" id="sessinUserId">${sessionScope.user.id}</div>
	
	

	<div class="base">
		<!-- 主体【开始】 -->
		<div class="body_bg" style="padding-bottom: 30px;">
			
			<!-- 导航栏【开始】 -->
			<div class="navigation">
				<span class="font_trip_title"> <a
					href="index.jhtml">首页</a> > <a
					href="search.jhtml?triptype=${detail.type.value}">${detail.type.value}</a>
					> <a class="cur">${detail.s_title }</a>
				</span>
			</div>
			<!-- 导航栏【结束】 -->

			<!-- 顶部产品概览 开始 -->
			<div class="product-main clearfix border_gray">

				<div class="protect-icon tuangou-icon" id="tour-type"></div>

				<div class="comment_img_div">
					<div class="DB_gallery">
						<div class="DB_imgSet" style="display: block;">
							<div class="DB_imgWin">
								<img src="img/img1.jpg" />
							</div>
							<div class="DB_prevBtn">
								<img src="img/prev_off.png" />
							</div>
							<div class="DB_nextBtn">
								<img src="img/next_off.png" />
							</div>
						</div>
						<div class="DB_thumSet">
							<ul class="DB_thumMove">
							
								<c:forEach items="${detail.pic_list}" var="t_pic">
									<li><a
										href="image_cache/${t_pic.name}"><img
											src="image_cache/${t_pic.name}"></a></li>
								</c:forEach>
								
							</ul>
							<div class="DB_thumLine"></div>
							<div class="DB_prevPageBtn">
								<img src="img/prev_page.png" alt="上一页" />
							</div>
							<div class="DB_nextPageBtn">
								<img src="img/next_page.png" alt="下一页" />
							</div>
						</div>
					</div>
				</div>
				
				<div class="overview-description">
					<div class="overview-description">
						<div class="trip-name">
							<h1>${detail.title}</h1>
						</div>

						<div class="product-price">
							<div class="product-price-item clearfix">
								<div
									class="product-price-title color-style1 product-qunawan-price">
									去哪玩价：</div>
								<div class="trip-prices-content product-price-content-val">
									<span class="product-price-rmb"> <span
										class="rmb-price">¥</span> <span class="product-price-value">
											${detail.min_price} </span>
									</span> <span class="fs-12 color-style1"> 起/人(最少成团人数:10人) <i
										class="protect-icon price-explain-icon"></i> <span
										tip-content="本起价是指未包含附加服务（如单人房差、保险费等）的基本价格。您最终确认的价格将会随所选出行日期、人数及服务项目而相应变化">起价说明</span>
									</span>
								</div>
							</div>
							<div class="product-price-item clearfix">
								<div class="product-price-title color-style1">出 发 地：</div>
								<!--若有值，则是多出发地产品，按多出发地需求展示，否则按原有方式展示-->
								<div class="trip-prices-content">
									<span>${detail.start.name}</span>
								</div>
								<!--出发地	6.30 add end -->
							</div>
							<div class="product-price-item clearfix">
								<div class="product-price-title color-style1">目 的 地：</div>
								<div class="trip-prices-content">
									<span> <c:forEach var="item"
											items="${detail.placeontrip_list}">
                                    	${item.place.name}  
                                    </c:forEach>
									</span>
								</div>
							</div>
							<div class="product-price-item clearfix">
								<div class="product-price-title color-style1">往返交通：</div>
								<div class="trip-prices-content">
									<span>${detail.traffic}</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="product-over-protect">
					<div class="overview-protect-top clearfix">
						<div class="product-basic-info">
							<div class="product-no clearfix">
								<div class="pull-left">
									<span>产品编号：</span>
								</div>
								<div class="put-right">
									<span>${detail.num}</span>
								</div>
							</div>
							<div class="product-no clearfix">
								<div class="pull-left">
									<span>好评率：</span>
								</div>
								<div class="put-right">
									<span class="val">${detail.good_rate}%</span>
								</div>
							</div>
						</div>
					</div>
					<div class="overview-protect-middle clearfix">
						<div class="product-protect-info h_center">
							<ul>
								<li class="overview-protect-price"><i
									class="protect-icon price-icon margin5"></i> <a href="javascript:;"
									target="_blank" rel="nofollow" hidefocus="false">价格保障</a></li>
								<li class="overview-protect-refund"><i
									class="protect-icon refund-icon"></i> <a href="javascript:;"
									target="_blank" rel="nofollow" hidefocus="false">退货保障</a></li>
								<li class="overview-protect-help"><i
									class="protect-icon help-icon"></i> <a href="javascript:;" target="_blank"
									rel="nofollow" hidefocus="false">救援保障</a></li>
								<li class="overview-protect-allday"><i
									class="protect-icon allday-icon"></i> <a href="javascript:;"
									target="_blank" rel="nofollow" hidefocus="false">7×24小时服务</a></li>
							</ul>
						</div>
					</div>
					<div class="overview-protect-qrcode">
						<img src="img/weixin.png" alt="qr code" width="78" height="78">

						<p>扫一扫更优惠</p>
					</div>
				</div>
			</div>
			<!-- 顶部产品概览 结束 -->

			<!-- 开始预定 开始 -->
			<form method="POST" action="myorder.jhtml?type=confirmOrder"
				name="orderForm" id="order-submit">
				<div class="product-preorder clearfix color-style1"
					id="product-preorder">

					<div class="preorder-config">
						<div class="preorder-input-group chrome-hack-1">
							<span class="chrome-hack-2">出行日期：</span>

							<div class="trip-time" id="trip-time">
								<input class="preorder-start-time" id="start_time" type="text"
									placeholder="请选择出行日期" autocomplete="off"
									onclick="AjaxTime(event);" name="date" readonly="readonly">
							</div>
						</div>

						<div class="preorder-input-group">
							<span>人数：</span> <select name="people" class="young">
								<option rel="1" class="liActive">1</option>
								<option rel="2" class="">2</option>
								<option rel="3" class="">3</option>
								<option rel="4" class="">4</option>
								<option rel="5" class="">5</option>
								<option rel="6" class="">6</option>
								<option rel="7" class="">7</option>
								<option rel="8" class="">8</option>
								<option rel="9" class="">9</option>
								<option rel="10" class="">10</option>
								<option rel="11" class="">11</option>
								<option rel="12" class="">12</option>
								<option rel="13" class="">13</option>
								<option rel="14" class="">14</option>
								<option rel="15" class="">15</option>
								<option rel="16" class="">16</option>
								<option rel="18" class="">18</option>
								<option rel="19" class="">19</option>
								<option rel="20" class="">20</option>
								<option rel="21" class="">21</option>
								<option rel="22" class="">22</option>
								<option rel="23" class="">23</option>
								<option rel="24" class="">24</option>
								<option rel="25" class="">25</option>
								<option rel="26" class="">26</option>
								<option rel="27" class="">27</option>
								<option rel="28" class="">28</option>
								<option rel="29" class="">29</option>
								<option rel="30" class="">30</option>
								<option rel="31" class="">31</option>
								<option rel="32" class="">32</option>
								<option rel="33" class="">33</option>
								<option rel="34" class="">34</option>
								<option rel="35" class="">35</option>
								<option rel="36" class="">36</option>
								<option rel="37" class="">37</option>
								<option rel="38" class="">38</option>
								<option rel="39" class="">39</option>
								<option rel="40" class="">40</option>
								<option rel="41" class="">41</option>
								<option rel="42" class="">42</option>
								<option rel="43" class="">43</option>
								<option rel="44" class="">44</option>
								<option rel="45" class="">45</option>
								<option rel="46" class="">46</option>
								<option rel="47" class="">47</option>
								<option rel="48" class="">48</option>
								<option rel="49" class="">49</option>
								<option rel="50" class="">50</option>
							</select>
						</div>
					</div>
					
					<!-- 在form提交时，上传的游玩的人数&旅游产品的id -->
					<input id="num" name="num" type="hidden" />
					<input id="trip_id" name="trip_id" type="hidden" />

					<div class="preorder-confirm">
						<a id="preorder-confirm-button" hidefocus="false"
							onclick="takeOrders(${detail.id})">开始预订</a>

						<div class="preorder-total-price">
							<span class="tip-icon tip-icon-help js_tips"
								style="display: none" id="DuiJie_TraffocTip"
								tip-content="机票价格更新频繁，以实际支付为准，为避免价格波动，请您尽快预订；"></span> <span
								class="total-price-value" id="total-price-value">--</span> <span
								class="total-price-symbol rmb-price">￥</span> <span
								class="total-price-title">总价：</span> <span class="fangcha"
								style="display: none;"></span>
						</div>
					</div>
				</div>
			</form>
			<!-- 开始预定 结束 -->

			<!--  产口详情、行程介绍、交通地图、综合点评-->
			<div class="product-detail">
				<div class="empty-placeholder hidden"></div>
				<div id="subNav">
					<ul class="wrap">
						<li class="adv_active"><a href="#product_detail"
							class="protuctdetail adv_active">产品详情 </a></li>
						<li><a href="#travel_introduce" class="travelintroduce">
								行程介绍 </a></li>
						<li><a href="#tafficMap" class="trafficmap "> 交通地图 </a></li>
						<li><a href="#reviewAll" class="reviewall"> 综合点评 </a></li>
					</ul>
				</div>

				<div class="wrap" style="width: 1000px;">
					<div class="show_title">
						<div class="product-pm-recommand clearfix">
							<div class="pm-recommand-title clearfix">
								<div class="recommand-left-quotation"></div>
								<!--新增模块-->
								<!--新增模块-->
								<h3 class="recommand-title-main">产品经理推荐</h3>

								<div class="recommand-right-quotation"></div>
							</div>
							<div class="pm-recomman-body">
								<ul>
									<li>
										恩施不偏不倚，她落脚于神秘的“北纬30度”，早已气势磅礴地傲立天地间。她的神秘，源于天然和原始，毫无矫揉造作。她的奇幻在于山水的真义与不可复制。她以仙山和秀水的胸襟，以白云和绿色的情怀，诚邀世界，迎接八方！她那么美，我们一起去看看！
									</li>
									<li>行程特色：行程丰富，大牌景区，优选品质酒店，完美的超值体验。</li>
								</ul>
							</div>
						</div>
					</div>

					<!--   产品详情 开始  -->
					<div class="product-detail-instance" id="product_detail">
						<div class="flags"></div>
						<div class="instance-title clearfix">
							<div class="title-icon-container">
								<i class="product-detail-icon"></i>
							</div>

							<h3>产品详情</h3>
						</div>
						<div class="detail-instance-body">
						
							<div class="product-point ">
								<c:if test="${detail.detail.place.length() > 0}">
									<span> 
										<img alt="" src="img/scenic.jpg" height="69px;">
									</span>
									<br> 
									${detail.detail.place}
								</c:if>
							</div>

							<div class="product-point ">
								<c:if test="${detail.detail.food.length() > 0}">
									<span> 
										<img alt="" src="img/foodphotoes.jpg" height="69px;">
									</span>
									<br>
	                            	${detail.detail.food}
								</c:if>
							</div>
							
							<div class="product-point ">
								<c:if test="${detail.detail.hotel.length() > 0}">
									<span> 
										<img alt="" src="img/hotel.jpg" height="69px;">
									</span>
									<br>
	                           		${detail.detail.hotel}
								</c:if>
							</div>
						
						</div>
					</div>
					<!--   产品详情 结束  -->

					<!--   行程介绍 开始  -->
					<div class="product-detail-instance" id="travel_introduce">
						<div class="flags"></div>
						<div class="instance-title clearfix">
							<div class="title-icon-container">
								<i class="product-line-icon"></i>
							</div>
							<h3>行程介绍</h3>
						</div>
						<div class="instance_list2_box">
							<div class="instance_list2" style="display: block;">
								<div class="detail-instance-body  padding0 clearfix">
									<div class="travel-all">
										<!--   注释：每个li到链接地址相似，需要以 #travel-day-128006-n 的格式命名 ， 跳转到第n天的具体信息的div时，第n天div的id需要与li的链接相同travel-day-128006-n -->
										<ul id="J_scrollnav1">
											<a href="#travel-introduce-detail-0"><li
												class="travel-all-item active">交通</li></a>

											<c:forEach end="${detail.schedule.dates.size()}" var="i"
												begin="1">
												<a href="#travel-introduce-detail-${i}"><li
													class="travel-all-item">第${i}天</li></a>
											</c:forEach>
											
										</ul>
									</div>
									<div class="travel-case">

										<div class="travel-title clearfix">
											<div id="travel-day-128006-0"
												class=" day_flag bg_none traffic_bus"></div>
											<p class="tips fs-12">注：以下交通信息仅限参考</p>
											<div class="traffic-type clearfix" id="traffic-type">
												<ul>
													<li data-traffic="traffic1-60896" class="active">交通<i
														class="arrow"></i>
													</li>
												</ul>
											</div>
											<div class="traffic-container" id="traffic-container">
												<div id="traffic1-60896-container" class="traffic-instance"
													style="display: block;">
													<dl class="traffic-item clearfix">
														<dt class="title">去程</dt>
														<dd class="outer-container">
															<div class="container clearfix">
																<div class="left">
																	<ul>
																		<!--判断是否是转机-->
																		<li class="clearfix bus">
																			<div class="icon">
																				<i class="traffic-bus-icon"></i>
																			</div>
																			<div>
																				<p title="管家加州派酒店（地址：成都市金牛区二环路北一段14号）">上车点：${detail.schedule.traffic.go_point }</p>
																				<p>
																					发车时间：<span class="bus-time">${detail.schedule.traffic.go_time }</span>
																				</p>
																			</div>
																		</li>
																	</ul>
																</div>
															</div>
														</dd>
													</dl>
													<dl class="traffic-item clearfix">
														<dt class="title">返程</dt>
														<dd class="outer-container">
															<div class="container clearfix">
																<div class="left">
																	<ul>
																		<li class="clearfix bus">
																			<div class="icon">
																				<i class="traffic-bus-icon"></i>
																			</div>
																			<div>
																				<p title="九寨沟沟口">上车点：${detail.schedule.traffic.return_point }</p>
																				<p>
																					发车时间：<span class="bus-time">${detail.schedule.traffic.return_time }</span>
																				</p>
																			</div>
																		</li>
																	</ul>
																</div>
															</div>
														</dd>
													</dl>
												</div>
											</div>
										</div>
									</div>
									<div class="travel-days">

										<c:forEach items="${detail.schedule.dates}" var="date">
											<div class="travel-case">
												<div class="travel-title clearfix">

													<!--   注释：第n天的id需要与li的链接相同travel-day-128006-n -->
													<c:if test="${date.num==1}">
														<div id="travel-introduce-detail-${date.num}"
															class=" day_flag bg_none "></div>
													</c:if>
													<c:if test="${date.num!=1}">
														<div id="travel-introduce-detail-${date.num}" class=" day_flag"></div>
													</c:if>
													<span class="day">D${date.num}</span> <span
														class="destination">${date.title}</span>
												</div>
												<div class="travel-detailTime-line  clearfix">
													<div class="time">
														<span></span> <i class="travel-flag-icon"></i>
													</div>
													<div class="detail">${date.detail }</div>
												</div>
												<div class="travel-detailTime-line   clearfix">
													<div class="time">
														<span></span> <i class="travel-notice-icon"></i>
													</div>
													<div class="detail-care">
														<p>
															<b>住宿：</b> ${date.hotel }
														</p>
														<p>
															<b>用餐：</b> ${date.meal }
														</p>
														<p>
															<b>交通：</b> ${date.traffic }
														</p>
													</div>
												</div>
											</div>
										</c:forEach>

										<div class="clearfix"></div>
										<div class="end-of-travel">
											<i class="travel-end-icon"></i>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!--   行程介绍 结束  -->



					<!--   交通地图 开始  -->
					<div class="product-detail-instance" id="tafficMap">
						<div class="flags"></div>
						<div class="instance-title clearfix">
							<div class="title-icon-container">
								<i class="product-map-icon"></i>
							</div>
							<h3>交通地图</h3>
						</div>
						<div id="allmap"></div>
					</div>
					<!--   交通地图 结束  -->

					<!--   综合点评 开始  -->
					<div class="product-detail-instance" id="reviewAll">
						<div class="flags"></div>
						<div class="instance-title clearfix">
							<div class="title-icon-container">
								<i class="product-review-icon"></i>
							</div>
							<h3>综合点评</h3>
						</div>
						<div class="comwrap">
							<!-- 点评信息概况 -->
							<div class="new-cominfo">
								<div class="comstati clearfix">

									<div class="com-null">
										<div class="com-count">
											<i class="percentum f60" data-mark="dynamicNum"
												data-level="95">${detail.good_rate}</i><span class="f60">%</span>
											<em>好评率</em> <em>来自<a class="f60" id="comment_num"
												hidefocus="false">${num}</a>位驴友的真实点评
											</em>
										</div>

										<ul class="clearfix">
											<li><span class="comcount"><em>景点</em>（${detail.place_score}）</span>
												<span class="comlevel"> <!--  改变width的值，显示不同的进度--> <i
													data-level="4.6" data-mark="dynamicNum"
													style="width: ${detail.place_score/5*100}%"></i>
											</span></li>
											<li><span class="comcount"><em>酒店</em>（${detail.hotel_score}）</span>
												<span class="comlevel"> <i data-level="4.4"
													data-mark="dynamicNum"
													style="width: ${detail.hotel_score/5*100}%"></i>
											</span></li>
											<li><span class="comcount"><em>服务</em>（${detail.service_score}）</span>
												<span class="comlevel"> <i data-level="4.5"
													data-mark="dynamicNum"
													style="width: ${detail.service_score/5*100}%"></i>
											</span></li>
											<li><span class="comcount"><em>交通</em>（${detail.traffic_score}）</span>
												<span class="comlevel"> <i data-level="4.4"
													data-mark="dynamicNum"
													style="width: ${detail.traffic_score/5*100}%"></i>
											</span></li>
										</ul>
									</div>

									<div class="com-btns">
										<a class="nlogin" href="javascript:;" hidefocus="false">有订单，写体验点评返奖金</a>
									</div>
								</div>
								<!-- //com-btns -->
							</div>

							<div class="comheatd">
								<ul class="comheatd-ul JS_com-tabnav">
									<li class="active"><a href="javascript:;"
										hidefocus="false" id="refCmt">相关点评<span>(${num})</span></a></li>
								</ul>
							</div>
							<!-- //comhead -->

							<div id="comments_detail"></div>
							<div class="Pages"></div>
						</div>
					</div>
					<!--   综合点评 结束  -->
				</div>
			</div>
		</div>
	</div>
	<div id="userid" style="display:none">${user }</div>
	<!-- 主体【结束】 -->

	<!-- footer【开始】 -->
	<%@ include file="/common/footer.jsp"%>
	<!-- footer【结束】 -->

	<!-- 此JS文件必须放在文件末尾 -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/pages/detail/tourist_detail.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/pages/detail/jquery.DB_gallery.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/pages/detail/comment.js"></script>
	<script type="text/javascript">
	$('.DB_gallery').DB_gallery({
		thumWidth : 83,
		thumGap : 8,
		thumMoveStep : 4,
		moveSpeed : 300,
		fadeSpeed : 500
	});
	</script>
</body>
</html>