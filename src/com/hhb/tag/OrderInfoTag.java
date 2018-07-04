package com.hhb.tag;

import com.hhb.entity.Trip;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;



/**
 * 订单确认页面显示订单信息的标签；其中方法返回值的含义如下：
 * 
 * EVAL_BODY_INCLUDE：把Body读入存在的输出流中，doStartTag()函数可用 EVAL_PAGE：继续处理页面，doEndTag()函数可用 SKIP_BODY：忽略对Body的处理，doStartTag()和doAfterBody()函数可用
 * SKIP_PAGE：忽略对余下页面的处理，doEndTag()函数可用 EVAL_BODY_TAG：已经废止，由EVAL_BODY_BUFFERED取代
 * 
 * EVAL_BODY_BUFFERED：申请缓冲区，由setBodyContent()函数得到的BodyContent对象来处理tag的body， 如果类实现了BodyTag，那么doStartTag()可用，否则非法
 */
public class OrderInfoTag extends BodyTagSupport {

	private static final long serialVersionUID = 3295700202417719334L;

	// 游玩人数
	private int num = 0;
	// 旅游产品对象
	private Trip trip = null;
	// 游玩时间
	private Date time = null;

	// ----标签体执行完后调用此方法----
	public int doAfterBody() {
		StringBuilder sb = new StringBuilder();
		sb.append("	<div class='order_box border_3 position_r'>");
		sb.append("		<h1 class='order_name'>");
		sb.append("			<span>" + trip.getTitle() + "</span>");
		sb.append("		</h1>");
		sb.append("		<dl class='order_line'>");
		sb.append("			<dt>国内游</dt>");
		sb.append("			<dd>");
		sb.append("				<table class='pro_table'>");
		sb.append("					<tbody>");
		sb.append("						<tr>");
		sb.append("							<th width='70%' style='text-align: left'>产品信息</th>");
		sb.append("							<th width='20%' style='text-align: left'>游玩时间</th>");
		sb.append("							<th width='10%'>人数</th>");
		sb.append("						</tr>");
		sb.append("						<tr>");
		sb.append("							<td>" + trip.getS_title() + "</td>");
		sb.append("							<td>" + new SimpleDateFormat("yyyy-MM-dd").format(time) + "</td>");
		sb.append("							<td style='text-align: center'>" + num + "</td>");
		sb.append("						</tr>");
		sb.append("					</tbody>");
		sb.append("				</table>");
		sb.append("			</dd>");
		sb.append("		</dl>");
		sb.append("		<div class='clear'></div>");
		sb.append("	</div>");

		try {
			JspWriter out = pageContext.getOut();
			out.print(sb.toString().trim());
		} catch (Exception e) {
			System.out.println(e);
		}
		return SKIP_BODY;
	}

	// ----标签结束时调用此方法-------
	public int doEndTag() {
		try {
			// ----将前面标签的语句输出到页面-------
			bodyContent.writeOut(bodyContent.getEnclosingWriter());
		} catch (Exception e) {
			System.out.println(e);
		}
		return EVAL_PAGE;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getCountNum() {
		return num;
	}

	public void setCountNum(int countNum) {
		this.num = countNum;
	}
}
