package com.hhb.form;

import com.hhb.entity.Contact;
import com.hhb.entity.Orders;

import java.util.List;


public class OrderDetailForm {

	// 联系人
	private Contact contact_one;
	// 游客
	private List<Contact> contact_many;
	// 当前订单信息
	private Orders order;

	public Contact getContact_one() {
		return contact_one;
	}

	public void setContact_one(Contact contact_one) {
		this.contact_one = contact_one;
	}

	public List<Contact> getContact_many() {
		return contact_many;
	}

	public void setContact_many(List<Contact> contact_many) {
		this.contact_many = contact_many;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "OrderDetailForm [contact_one=" + contact_one + ", contact_many=" + contact_many + ", order=" + order
				+ "]";
	}

}
