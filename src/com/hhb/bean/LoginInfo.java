package com.hhb.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 保存当前登录用户的附加信息
 */
public class LoginInfo {

	// 登录用户的id
	private String loginName;
	// 登录的ip地址
	private String ip;
	// 登录的时间
	private Date loginTime;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(loginTime);
		return "<td>" + loginName + "</td><td>" + ip + "</td><td>" + time + "</td>";
	}

}
