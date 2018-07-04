package com.hhb.form;

import com.hhb.entity.Contact;

import java.sql.Date;


public class ContactForm {
	private int id;
	private String name;
	private String phone;
	private String email;
	private String cardno;
	private Date birthday;
	private boolean sex;
	private int pageCount;

	public ContactForm(Contact contact, int pageCount) {
		this.birthday = contact.getBirthday();
		this.cardno = contact.getCardno();
		this.email = contact.getEmail();
		this.id = contact.getId();
		this.name = contact.getName();
		this.phone = contact.getPhone();
		this.sex = contact.isSex();
		this.pageCount = pageCount;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	@Override
	public String toString() {
		return "ContactForm [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", cardno="
				+ cardno + ", birthday=" + birthday + ", sex=" + sex + "]";
	}

}
