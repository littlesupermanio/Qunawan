package com.hhb.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Set;

public class Comment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private int place;
	private int hotel;
	private int service;
	private int traffic;
	private String content;
	private Timestamp time;
	private String timeStr;
	private int usefull;
	private int useless;
	private User user;
	private Trip trip;
	private Orders orders;

	private Set<CommentPicture> pictures;

	public Comment(Serializable id) {
		this.id = (int) id;
	}

	public Comment() {
	}

	public Set<CommentPicture> getPictures() {
		return pictures;
	}

	public void setPictures(Set<CommentPicture> pictures) {
		this.pictures = pictures;
	}

	public String getTimeStr() {
		return timeStr;
	}

	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public int getHotel() {
		return hotel;
	}

	public void setHotel(int hotel) {
		this.hotel = hotel;
	}

	public int getService() {
		return service;
	}

	public void setService(int service) {
		this.service = service;
	}

	public int getTraffic() {
		return traffic;
	}

	public void setTraffic(int traffic) {
		this.traffic = traffic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		timeStr = df.format(time);
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
		if(orders != null){
			this.trip = orders.getTrip();
			this.user = orders.getUser();
		}
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", place=" + place + ", hotel=" + hotel + ", service=" + service + ", traffic="
				+ traffic + ", content=" + content + ", time=" + time + ", usefull=" + usefull + ", useless=" + useless
				+ ", user=" + user + ", trip=" + trip + ", orders=" + orders + "]";
	}

}
