package com.hhb.form;


import com.hhb.entity.Orders;

public class PutOrderForm {
	private int id;
	private String orderno;
	private String title;
	private float price;

	public PutOrderForm(Orders order) {
		this.id = order.getId();
		this.orderno = order.getOrderNo();
		this.price = order.getTotal_price();
		this.title = order.getTrip().getTitle();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "PutOrderForm [id=" + id + ", orderno=" + orderno + ", title=" + title + ", price=" + price + "]";
	}

}
