package com.hhb.form;


import com.hhb.entity.Sequence;

public class OrderForm {
	private int orderid;
	private String orderno;
	private String create_time;
	private String content;
	private float price;
	private int person_num;
	private Sequence state;
	private String start_time;
	private int pageCount;
	private int totalDays;

	public int getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
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

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getPerson_num() {
		return person_num;
	}

	public void setPerson_num(int person_num) {
		this.person_num = person_num;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public Sequence getState() {
		return state;
	}

	public void setState(Sequence state) {
		this.state = state;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	@Override
	public String toString() {
		return "OrdersForm [orderid=" + orderid + ", orderno=" + orderno + ", create_time=" + create_time + ", content="
				+ content + ", price=" + price + ", person_num=" + person_num + ", start_time=" + start_time + "]";
	}

}
