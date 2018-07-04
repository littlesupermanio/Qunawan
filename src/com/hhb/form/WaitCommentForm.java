package com.hhb.form;

import com.hhb.entity.Orders;

import java.util.Date;



public class WaitCommentForm {
	private int orderid;
	private String orderno;
	private Date time;
	private String content;
	private String image;
	private int totalNum;

	private String good_rate;
	private int pageCount;
	// 待评论总数
	private int waitCount;
	// 已点评总数
	private int finishCount;

	public WaitCommentForm(Orders order, int pageCount, int waitCount, int finishCount, int totalNum) {
		this.totalNum = totalNum;
		this.pageCount = pageCount;
		this.waitCount = waitCount;
		this.finishCount = finishCount;
		
		this.content = order.getTrip().getTitle();
		this.time = order.getStart_time();
		this.orderid = order.getId();
		this.orderno = order.getOrderNo();
		this.good_rate = order.getTrip().getGood_rate() + "";
		this.image = order.getTrip().getMain_picname();
	}

	public WaitCommentForm(int waitCount, int finishCount) {
		this.waitCount = waitCount;
		this.finishCount = finishCount;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getWaitCount() {
		return waitCount;
	}

	public void setWaitCount(int waitCount) {
		this.waitCount = waitCount;
	}

	public int getFinishCount() {
		return finishCount;
	}

	public void setFinishCount(int finishCount) {
		this.finishCount = finishCount;
	}

	public String getGood_rate() {
		return good_rate;
	}

	public void setGood_rate(String good_rate) {
		this.good_rate = good_rate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	@Override
	public String toString() {
		return "FinishedCommentForm [orderid=" + orderid + ", orderno=" + orderno + ", time=" + time + ", content="
				+ content + ", pageCount=" + pageCount + "]";
	}

}
