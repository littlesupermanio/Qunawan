package com.hhb.entity;

import java.io.Serializable;

public class OrderContact implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private int type;
	private Contact contact;
	private Orders order;

	public OrderContact(){}
	
	public OrderContact(Orders order, Contact contact, int type){
		this.order = order;
		this.contact = contact;
		this.type = type;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "OrderContact [id=" + id + ", type=" + type + ", contact=" + contact + ", order=" + order + "]";
	}

}
