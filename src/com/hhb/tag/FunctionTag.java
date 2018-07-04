package com.hhb.tag;

/**
 * 自定义JSP功能函数，主要提供将ArrayList<Price>转换成可供产品详情页使用的<价格>-<日历>格式的数据
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FunctionTag {

	/**
	 * 将ArrayList<Price>转换成可供产品详情页使用的<价格>-<日历>格式的数据
	 * 
	 * [{"date":"2015-12-24","id":0,"price":98,"trip":null}, {"date":"2015-12-25","id":0,"price":560,"trip":null},
	 * {"date":"2015-12-26","id":0,"price":520,"trip":null}, {"date":"2015-12-27","id":0,"price":520,"trip":null}]
	 * 
	 * @throws ParseException
	 */
	public static String getPriceData(String prices_data) throws ParseException {
		System.out.println("in the function");
		JSONArray prices = JSONArray.fromObject(prices_data);
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < prices.size(); i++) {
			JSONObject price = prices.getJSONObject(i);
			Date time = new SimpleDateFormat("yyyy-MM-dd").parse((String) price.get("date"));
			if (time.compareTo(new Date()) >= 0)
				continue;

			sb.append("{\"Date\":\"" + price.get("date") + "\",\"Price\":\"" + price.get("price") + "\"},");
		}
		if (sb.charAt(sb.length() - 1) == '[')
			return "";
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		System.out.println(sb.toString());
		return sb.toString();
	}
}
