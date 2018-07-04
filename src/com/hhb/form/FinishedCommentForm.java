package com.hhb.form;

import com.hhb.entity.Comment;
import com.hhb.entity.CommentPicture;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;



public class FinishedCommentForm {
	
	private int orderid;
	private String orderno;
	private String orderContent;
	private String commentTime;
	private String commentContent;
	private int siteScore;
	private int hotelScore;
	private int serviceScore;
	private int trafficScore;
	private int starLevel;
	private int usefull;
	private int useless;
	private int pageCount;
	private int count;
	private List<String> imgs = new ArrayList<String>();

	public FinishedCommentForm(Comment comment, int pageCount, int count) {
		this.orderid = comment.getOrders().getId();
		this.orderno = comment.getOrders().getOrderNo();
		this.commentContent = comment.getContent();
		this.commentTime = comment.getTimeStr();
		this.hotelScore = comment.getHotel();
		this.siteScore = comment.getPlace();
		this.serviceScore = comment.getService();
		this.trafficScore = comment.getTraffic();
		this.usefull = comment.getUsefull();
		this.useless = comment.getUseless();
		this.orderContent = comment.getTrip().getTitle();

		this.pageCount = pageCount;	// 页数
		this.count = count; 				// 总数
		this.starLevel = Math.round((trafficScore+serviceScore+hotelScore+serviceScore)/(float)4);

		Set<CommentPicture> pictures = comment.getPictures();
		if(pictures == null || pictures.size()==0)
			return;
		for (Iterator<CommentPicture> picture = pictures.iterator(); picture.hasNext();) {
			imgs.add(picture.next().getName());
		}
		
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

	public String getOrderContent() {
		return orderContent;
	}

	public void setOrderContent(String orderContent) {
		this.orderContent = orderContent;
	}

	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String date) {
		this.commentTime = date;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public int getSiteScore() {
		return siteScore;
	}

	public void setSiteScore(int siteScore) {
		this.siteScore = siteScore;
	}

	public int getHotelScore() {
		return hotelScore;
	}

	public void setHotelScore(int hotelScore) {
		this.hotelScore = hotelScore;
	}

	public int getServiceScore() {
		return serviceScore;
	}

	public void setServiceScore(int serviceScore) {
		this.serviceScore = serviceScore;
	}

	public int getTrafficScore() {
		return trafficScore;
	}

	public void setTrafficScore(int trafficScore) {
		this.trafficScore = trafficScore;
	}

	public int getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(int starLevel) {
		this.starLevel = starLevel;
	}

	public int getUsefull() {
		return usefull;
	}

	public void setUsefull(int usefull) {
		this.usefull = usefull;
	}

	public int getUseless() {
		return useless;
	}

	public void setUseless(int useless) {
		this.useless = useless;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List<String> getImgs() {
		return imgs;
	}

	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "FinishedCommentForm [orderid=" + orderid + ", orderno=" + orderno + ", orderContent=" + orderContent
				+ ", commentTime=" + commentTime + ", commentContent=" + commentContent + ", siteScore=" + siteScore
				+ ", hotelScore=" + hotelScore + ", serviceScore=" + serviceScore + ", trafficScore=" + trafficScore
				+ ", starLevel=" + starLevel + ", usefull=" + usefull + ", useless=" + useless + ", pageCount="
				+ pageCount + ", imgs=" + imgs + "]";
	}

}
