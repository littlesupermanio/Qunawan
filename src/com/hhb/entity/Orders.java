package com.hhb.entity;

import java.io.Serializable;
import java.sql.Date;

public class Orders implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String orderNo;
	private int num;
	private Date start_time;
	private String go_point;
	private String go_time;
	private float total_price;
	private Sequence state;
	private String create_time;
	private Trip trip;
	private User user;

	public Orders(){};
	
	public Orders(int orderId) {
		this.id = orderId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getGo_point() {
		return go_point;
	}

	public void setGo_point(String go_point) {
		this.go_point = go_point;
	}

	public String getGo_time() {
		return go_time;
	}

	public void setGo_time(String go_time) {
		this.go_time = go_time;
	}

	public float getTotal_price() {
		return total_price;
	}

	public void setTotal_price(float total_price) {
		this.total_price = total_price;
	}

	public Sequence getState() {
		return state;
	}

	public void setState(Sequence state) {
		this.state = state;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", orderNo=" + orderNo + ", num=" + num + ", start_time=" + start_time
				+ ", go_point=" + go_point + ", go_time=" + go_time + ", total_price=" + total_price + ", state="
				+ state + ", create_time=" + create_time + ", trip=" + trip + "]";
	}

}
