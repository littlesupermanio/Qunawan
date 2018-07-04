package com.hhb.tag;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 购买人确认信息显示标签 通过自定义动态标签实现 JSP1.X实现的是TagSupport类中的doEndTag()方法
 * 而JSP2.X只实现SimpleTag接口中唯一的doTag()方法，并且该方法没有返回值
 * 
 * @author milo
 *
 */
public class OrderOwnTag extends SimpleTagSupport implements DynamicAttributes {

	// 保存数据的显示名称
	ArrayList<String> keys = new ArrayList<String>();
	// 保存数据的显示值
	ArrayList<Object> values = new ArrayList<Object>();

	/**
	 * 输出属性值
	 */
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		// 遍历集合，并输出
		for (int i = 0; i < keys.size(); i++) {
			String key = (String) keys.get(i);
			Object value = values.get(i);
			// 遍历输出对应的key值和value值
			out.println("<dl class='user_dl'><dt><span class='red'>*</span>" + key
					+ "</dt><dd class='error_show'>"
					+ "<span class='su_info'><label>" + value + "</label></span>"
					+ "<span class='error_text'><i class='tip-icon tip-icon-error'></i><label></label></span></dd></dl>");
		}
	}

	/**
	 * 设置动态属性
	 * localName 接收标签中等号左边的数据
	 * value 接收等号右边的数据
	 */
	@Override
	public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
		// 判断当前接收到的数据是否是姓名
		if ("real_name".equals(localName)) {
			keys.add("姓名：");
		}
		// 判断当前接收到的数据是否是电话
		else if ("phone".equals(localName)) {
			keys.add("手机号码：");
		}
		// 判断当前接收到的数据是否是邮箱地址
		else if ("email".equals(localName)) {
			keys.add("邮箱地址：");
		}
		// 接收到的value值空字符判断
		if (value == null || "".equals(value)) {
			values.add("未设置");
		} else {
			values.add(value);
		}
	}

}
