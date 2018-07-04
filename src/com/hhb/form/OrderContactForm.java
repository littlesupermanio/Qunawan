package com.hhb.form;

public class OrderContactForm {
	private int id;
	private String name;
	private String phone;
	private String cardno;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	@Override
	public String toString() {
		return "OrderContactForm [id=" + id + ", name=" + name + ", phone=" + phone + ", cardno=" + cardno + "]";
	}

}
