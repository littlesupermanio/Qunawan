<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>${s_bean.search_key}旅游_${s_bean.search_key}旅游报价_路线推荐-去哪玩</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<link rel="stylesheet" href="css/main.css" />
	<base href="<%=basePath%>" />
</head>
<body>
<!-- 顶端 -->
<%@ include file="/common/header.jsp"%>

<!-- 主体【开始】 -->
<div class="body_bg">
	<div class="base">
		<!-- 导航栏【开始】 -->
		<div class="navigation">
			<span class="font_g9">
				您当前所在的位置：
				<a href="index.jhtml" class="live">首页</a> > 
				  
				<c:choose>
					<c:when test="${s_bean.place_name == null || s_bean.place_name == '' }">
						<c:choose>
							<c:when test="${s_bean.type_name == null || s_bean.type_name == '' }"><a href="javascript:void(0);" class="atype font_gc">所有产品</a></c:when>
							<c:otherwise><a href="javascript:void(0);" class="atype font_gc">${s_bean.type_name }</a></c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${s_bean.type_name == null || s_bean.type_name == '' }">
								<a href="javascript:void(0);" class="atype live">所有产品 </a>
								 > 
								<a href="javascript:void(0);" class="aplace font_gc">${s_bean.place_name } </a>
							</c:when>
							<c:otherwise>
								<a href="javascript:void(0);" class="atype live">${s_bean.type_name } </a>
								 > 
								<a href="javascript:void(0);" class="aplace font_gc">${s_bean.place_name } </a>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
				
			</span>
		</div>
		<!-- 导航栏【结束】 -->

		<!-- 筛选框【开始】 -->
		<div class="filter_box">
			<div class="head">
				<ul>
					<c:choose>
						<c:when test="${s_bean.type_name == null || s_bean.type_name == '' }">
							<li class="all cur vo_type">全部</li>
						</c:when>
						<c:otherwise>
							<li class="all vo_type">全部</li>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${s_bean.type_name == '自驾游' }">
							<li class="other cur vo_type">自驾游</li>
						</c:when>
						<c:otherwise>
							<li class="other vo_type">自驾游</li>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${s_bean.type_name == '国内游' }">
							<li class="other cur vo_type">国内游</li>
						</c:when>
						<c:otherwise>
							<li class="other vo_type">国内游</li>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${s_bean.type_name == '出境游' }">
							<li class="other cur vo_type">出境游</li>
						</c:when>
						<c:otherwise>
							<li class="other vo_type">出境游</li>
						</c:otherwise>
					</c:choose>				
					<!-- <li class="all cur vo_type">全部</li>
					<li class="other vo_type">自驾游</li>
					<li class="other vo_type">国内游</li>
					<li class="other vo_type">出境游</li> -->
					<div class="clear"></div>
				</ul>
			</div>
			<div class="body">
				
				<c:if test="${(s_bean.place_name == null || s_bean.place_name =='') && fn:length(s_bean.placeList) != 0}">
					<div class="area option">
						<div class="title">地区</div>
						<dl class="ddlist">
							<dt><span><a class="cur" href="javascript:void(0);">全部</a></span></dt>
							<dd>
								<c:forEach items="${s_bean.placeList }" var="place">
									<span><a class="vo_place" href="javascript:void(0);">${place }</a></span>
								</c:forEach>
							</dd>
						</dl>
						<a href="javascript:void(0)" class="more more_y">更多<i class="arrow_down"></i></a>
						<div class="clear"></div>
					</div>
				</c:if>
				
				<c:if test="${(s_bean.start_place_name == null || s_bean.start_place_name =='') && fn:length(s_bean.startList) != 0 }">
					<div class="setout option topborder">
						<div class="title">出发地</div>
						<dl class="ddlist">
							<dt><span><a class="cur" href="javascript:void(0);">全部</a></span></dt>
							<dd>
								<c:forEach items="${s_bean.startList }" var="start">
									<span><a class="vo_start" href="javascript:void(0);">${start }</a></span>
								</c:forEach>
							</dd>
						</dl>
						<a href="javascript:void(0)" class="more more_y">更多<i class="arrow_down"></i></a>
						<div class="clear"></div>
					</div>
				</c:if>
				
				<c:if test="${(s_bean.theme_name == null || s_bean.theme_name =='') && fn:length(s_bean.themeList) != 0 }">
					<div class="theme option topborder">
						<div class="title">主题</div>
						<dl class="ddlist">
							<dt><span><a class="cur" href="javascript:void(0);">全部</a></span></dt>
							<dd>
								<c:forEach items="${s_bean.themeList }" var="theme">
									<span><a class="vo_theme" href="javascript:void(0);">${theme }</a></span>
								</c:forEach>
							</dd>
						</dl>
						<a href="javascript:void(0)" class="more more_y">更多<i class="arrow_down"></i></a>
						<div class="clear"></div>
					</div>
				</c:if>
				
				<c:if test="${(s_bean.traffic == null || s_bean.traffic =='') && fn:length(s_bean.trafficList) != 0 }">
					<div class="traffic option topborder">
						<div class="title">交通工具</div>
						<dl class="ddlist">
							<dt><span><a class="cur" href="javascript:void(0);">全部</a></span></dt>
							<dd>
								<c:forEach items="${s_bean.trafficList }" var="traffic">
									<span><a class="vo_traffic" href="javascript:void(0);">${traffic }</a></span>
								</c:forEach>
								<!-- <span><a href="#">飞机(131)</a></span>
								<span><a href="#">火车 (26)</a></span>
								<span><a href="#">游轮 (131)</a></span> -->
							</dd>
						</dl>
						<a href="javascript:void(0)" class="more more_y">更多<i class="arrow_down"></i></a>
						<div class="clear"></div>
					</div>
				</c:if>
				
				<c:if test="${(s_bean.time == null || s_bean.time =='') && fn:length(s_bean.timeList) != 0 }">
					<div class="days option topborder">
						<div class="title">出行天数</div>
						<dl class="ddlist">
							<dt><span><a class="cur" href="javascript:void(0);">全部</a></span></dt>
							<dd>
								<c:forEach items="${s_bean.timeList }" var="time">
									<span><a class="vo_time" href="javascript:void(0);">${time }天</a></span>
								</c:forEach>
							</dd>
						</dl>
						<a href="javascript:void(0)" class="more more_y">更多<i class="arrow_down"></i></a>
						<div class="clear"></div>
					</div>
				</c:if>
				
			</div>
			<div class="tail">
				<p class="result">
					<b>${s_bean.totalResult }</b>条结果
				</p>
				<dl class="request_list" style="display:block">
					<dt>您已选择：</dt>
					<c:if test="${s_bean.place_name != null && s_bean.place_name !='' }">
						<dd><span>地区：${s_bean.place_name}<a class="request_close" href="javascript:void(0);">×</a></span></dd>
					</c:if>
					<c:if test="${s_bean.start_place_name != null && s_bean.start_place_name !='' }">
						<dd><span>出发地：${s_bean.start_place_name}<a class="request_close" href="javascript:void(0);">×</a></span></dd>
					</c:if>
					<c:if test="${s_bean.theme_name != null && s_bean.theme_name !='' }">
						<dd><span>主题：${s_bean.theme_name}<a class="request_close" href="javascript:void(0);">×</a></span></dd>
					</c:if>
					<c:if test="${s_bean.traffic != null && s_bean.traffic !='' }">
						<dd><span>交通工具：${s_bean.traffic}<a class="request_close" href="javascript:void(0);">×</a></span></dd>
					</c:if>
					<c:if test="${s_bean.time != null && s_bean.time !='' }">
						<dd><span>天数：${s_bean.time}<a class="request_close" href="javascript:void(0);">×</a></span></dd>
					</c:if>
					<c:if test="${(s_bean.min_price != null && s_bean.min_price !='') || (s_bean.max_price != null && s_bean.max_price !='') }">
						<dd><span>价格：
						<c:if test="${s_bean.min_price != null && s_bean.min_price !=''}">
							${s_bean.min_price}
						</c:if>
						<c:if test="${(s_bean.min_price != null && s_bean.min_price !='') && (s_bean.max_price != null && s_bean.max_price !='') }">
							~
						</c:if>
						<c:if test="${s_bean.max_price != null && s_bean.max_price !=''}">
							${s_bean.max_price}
						</c:if>
						<a class="request_close" href="javascript:void(0);">×</a></span></dd>
					</c:if>
					<dd>
						<a class="close_all" href="javascript:void(0);">清除所有条件</a>
					</dd>
				</dl>
				<a class="condition condition_up" href="javascript:void(0);">
					收起筛选条件
					<i class="arrow_up"></i>
				</a>
				<a class="condition condition_down" href="javascript:void(0);">
					更多筛选条件
					<i class="arrow_down"></i>
				</a>
				<div class="clear"></div>
			</div>
		</div>
		<!-- 筛选框【结束】 -->

		<!-- 主体内容【开始】 -->
		<div class="top10">
			<!-- 右侧内容【开始】 -->
			<div class="main_r">
				<!-- 服务保障【开始】 -->
				<div class="common_box box">
					<h4 class="common_title">服务保障</h4>
					<ul>
						<li><i class="icon1"></i><p><b>价格保障</b><span>同类产品，保证低价</span></p></li>
						<li><i class="icon2"></i><p><b>退订保障</b><span>因特殊情况影响出行，保证退订</span></p></li>
						<li><i class="icon2"></i><p><b>退订保障</b><span>因特殊情况影响出行，保证退订</span></p> 
						</li><li><i class="icon3"></i><p><b>救援保障</b><span>旅途中遇以外情况，保证救援</span></p> 
						</li><li><i class="icon4"></i><p><b>7×24小时服务</b><span>快速响应，全年无休</span></p></li>
					</ul>
				</div>
				<!-- 服务保障【结束】 -->

				<!-- 当地旅游首页【开始】 -->
				<div class="local_index box">
					<h4>千岛湖首页</h4>
					<p>
						千岛湖因湖内拥有星罗棋布的1078个岛屿而得名。它也是世界上岛屿最多的湖。景区群山绵延，森林繁茂，湖水晶莹透澈，被赞誉为“天下第一秀水”。而那句家喻户晓的“农夫山泉，有点甜”，它的水就取自于千岛湖。...
						<a href="#">更多</a>
					</p>
					<ul>
						<li><i></i><a href="#">概况</a></li>
						<li><i></i><a href="#">指南</a></li>
						<li><i></i><a href="#">景点</a></li>
						<li><i></i><a href="#">住宿</a></li>
						<li><i></i><a href="#">娱乐</a></li>
						<li><i></i><a href="#">交通</a></li>
						<li><i></i><a href="#">游记</a></li>
					</ul>
				</div>
				<!-- 当地旅游首页【结束】 -->

				<!-- 当地旅游评价【开始】 -->
				<div class="local_comment box">
					<h4 class="common_title">千岛湖点评</h4>
					<div class="score">
						<div class="score_l">
							<span class="score1"><b>4.08</b>分</span>
							<span class="score2"><i style="width:30%"></i></span>
							<p class="comments">
								<a href="#">
									 916条评论
								</a>
							 </p>
						</div>
						<ul class="score_r">
							<li>人气<span><i>4.02</i>分</span></li>
							<li>规模<span><i>3.91</i>分</span></li>
							<li>观光<span><i>3.93</i>分</span></li>
							<li>服务<span><i>3.93</i>分</span></li>
						</ul>
					</div>
				</div>
				<!-- 当地旅游评价【结束】 -->
			</div>
			<!-- 右侧内容【结束】 -->

			<!-- 左侧列表【开始】 -->
			<div class="main_l">
				<!-- 排序筛选【开始】 -->
				<div class="sort">
					<ul>
						<li class="active"><a href="#">去哪玩推荐</a></li>
						<c:choose>
							<c:when test="${s_bean.price_sort == 'desc' }">
								<li class="price" title="按价格由高到低排序"> <a href="javascript:void(0);">价格 </a><i class="sort_down"></i></li>
							</c:when>
							<c:otherwise>
								<li class="price" title="按价格由低到高排序"> <a href="javascript:void(0);">价格 </a><i class="sort_up"></i></li>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${s_bean.good_rate_sort == 'asc' }">
								<li class="comment" title="按好评率由低到高排序"> <a href="javascript:void(0);">好评率 </a><i class="sort_up"></i></li>
							</c:when>
							<c:otherwise>
								<li class="comment" title="按好评率由高到低排序"> <a href="javascript:void(0);">好评率 </a><i class="sort_down"></i></li>
							</c:otherwise>
						</c:choose>
						<li>
							<div>
								
								<form id="searchForm" action="<%=response.encodeURL("search.jhtml")%>" method="post">
								
									<input type="hidden" name="triptype" id="triptype" value="${s_bean.type_name }"/>
									<input type="hidden" name="place" id="place" value="${s_bean.place_name }"/>
									<input type="hidden" name="start_place" id="start_place" value="${s_bean.start_place_name }"/>
									<input type="hidden" name="theme" id="theme" value="${s_bean.theme_name }"/>
									<input type="hidden" name="traffic" id="traffic" value="${s_bean.traffic }"/>
									<input type="hidden" name="time" id="time" value="${s_bean.time }"/>
									<input type="hidden" name="search_key" id="search_key" value="${s_bean.search_key }"/>
									<input type="hidden" name="pageCurrent" id="pageCurrent" value="1"/>
									<input type="hidden" name="price_sort" id="price_sort" value="${s_bean.price_sort }"/>
									<input type="hidden" name="good_rate_sort" id="good_rate_sort" value="${s_bean.good_rate_sort }"/>
									<input type="hidden" name="cur_sort_str" id="cur_sort_str" value="${s_bean.cur_sort_str }"/>
									<input type="text" name="min_price" class="txt" id="min_price" value="${s_bean.min_price }" onkeydown="validNum();"/>
									<b>-</b>
									<input type="text" name="max_price" class="txt" id="max_price" value="${s_bean.max_price }" onkeydown="validNum();"/>
									<a class="submit price_submit" href="javascript:void(0);">确定</a>
								</form>
							</div> 
						</li>
					</ul>
					<div class="page">
						<p class="count_num"><b>${s_bean.pageCurrent }</b>/${s_bean.maxPage }</p>
						<c:choose>
							<c:when test="${s_bean.pageCurrent == 1 }">
								<a class="prev page_no" href="javascript:void(0);"><i class="arrow_p"></i></a>
							</c:when>
							<c:otherwise>
								<a class="prev" href="javascript:submitFormByPage('searchForm',${s_bean.pageCurrent - 1 })"><i class="arrow_p"></i></a>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${s_bean.pageCurrent == s_bean.maxPage }">
								<a class="next page_no" href="javascript:void(0);"><i class="arrow_n"></i></a>
							</c:when>
							<c:otherwise>
								<a class="next" href="javascript:submitFormByPage('searchForm',${s_bean.pageCurrent + 1 })"><i class="arrow_n"></i></a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<!-- 排序筛选【结束】 -->

				<c:forEach items="${t_list }" var="trip" varStatus="n">
					<div class="recommend">
						<div class="detail"> 
							<a class="detail_img" href="tripDetail.jhtml?id=${trip.id}&type=init">
								<img src="image_cache/${trip.main_picname }" width="195" height="130" />
								<c:if test="${fn:trim(trip.type.value) == '自驾游' }">
									<span class="detail_tag detail_tag_blue">自驾游</span>
								</c:if>
								<c:if test="${fn:trim(trip.type.value) == '国内游' }">
									<span class="detail_tag detail_tag_green">国内游</span>
								</c:if>
								<c:if test="${fn:trim(trip.type.value) == '出境游' }">
									<span class="detail_tag detail_tag_yellow">出境游</span>
								</c:if>
							</a> 		
							<div class="price">
								<p class="priceTitle">去哪玩价:</p>
								<p class="priceBox">
									<span>
										¥ <b>${trip.min_price }</b>
									</span>
									<label>起/人</label>
								</p>
								<p class="good_comment"><span>${trip.good_rate }%</span>好评率</p>
							</div>
							<div class="detail">
								<div class="detail_list">
									<p class="detail_title">
										<span class="title_left">${trip.start.name }出发 |</span>
										<a href="tripDetail.jhtml?id=${trip.id}&type=init">
											${fn:trim(trip.title) }
										</a>
									</p>	
									<dl class="detail_text theme_text">
										<dt>主&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题</dt>
										<dd>
											<p>
												<c:forEach items="${trip.themeontrip_list }" var="tot" varStatus="index">
													${tot.theme.name }
													<c:if test="${!index.last }">,</c:if>
												</c:forEach>
											</p>
										</dd>
									</dl>
									<dl class="detail_text theme_text">
										<dt>景&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点</dt>
										<dd>
											<p>
												<c:forEach items="${trip.placeontrip_list }" var="pot" varStatus="index">
													${pot.place.name }
													<c:if test="${!index.last }">,</c:if>
												</c:forEach>
											</p>
										</dd>
									</dl>
								</div>
							</div>
							<div class="clear"></div>
						</div>
						<div class="discribe">
							<ul>
								<li>
									<p><span class="icon icon_traffic"></span>交通</p>
									<p>
										<span>${trip.traffic }</span>
										<span><a href="#"></a></span>
									</p>
								</li>
								<li class="js_tips">
									<p><span class="icon icon_stay"></span>住宿</p>
									<p>
										<span>${trip.hotel }</span>
										<span><a href="#"></a></span>
									</p>
								</li>
								<li class="js_tips">
									<p><span class="icon icon_time"></span>行程天数</p>
									<p>
										<span>${trip.time }天</span>
									</p>
								</li>
								<li class="js_tips">
									<p><span class="icon icon_date"></span>出发日期</p>
									<p>
										<span>
											<c:forEach items="${trip.date_list }" var="date" varStatus="index">
												${date }
												<c:if test="${!index.last }">,</c:if>
											</c:forEach>
										</span>																				
									</p>
								</li>
							</ul>
							<div class="clear"></div>
						</div>
						<c:if test="${n.first && s_bean.pageCurrent == 1}"><div class="tip"></div></c:if>
						<div class="clear"></div>
					</div>
				</c:forEach>
	
			</div>
			<!-- 左侧列表【结束】 -->
			<div class="clear"></div>
		</div>
		<!-- 主体内容【结束】 -->
	</div>
</div>
<script type="text/javascript" src="js/pages/trip/search.js"></script>
<!-- 主体【结束】 -->

<!-- 引入尾部开始 -->
<%@ include file="/common/footer.jsp"%>
</body>
</html>