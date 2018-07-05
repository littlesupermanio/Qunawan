package com.hhb.bean;

import com.hhb.entity.PlaceOnTrip;
import com.hhb.entity.ThemeOnTrip;
import com.hhb.entity.Trip;
import com.hhb.form.SearchForm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class SearchBean {
	private String type_name;
	private String place_name;
	private String start_place_name;
	private String theme_name;
	private String traffic;
	private Integer time;
	
	private String search_key;
	
	private int totalResult = 0;
	private Integer min_price;
	private Integer max_price;
	
	private String price_sort;
	private String good_rate_sort;
	private String cur_sort_str;
	
	private int pageCurrent = 1;
	private int maxPage;
	
	private List<String> placeList;
	private List<String> startList;
	private List<String> themeList;
	private List<String> trafficList;
	private List<Integer> timeList;
	
	public SearchBean(String type_name, String place_name, String start_place_name, String theme_name, String traffic,
			Integer time, String search_key, int totalResult, Integer min_price, Integer max_price, String price_sort,
			String good_rate_sort, String cur_sort_str, int pageCurrent, int maxPage, List<String> placeList,
			List<String> startList, List<String> themeList, List<String> trafficList, List<Integer> timeList) {
		this.type_name = type_name;
		this.place_name = place_name;
		this.start_place_name = start_place_name;
		this.theme_name = theme_name;
		this.traffic = traffic;
		this.time = time;
		this.search_key = search_key;
		this.totalResult = totalResult;
		this.min_price = min_price;
		this.max_price = max_price;
		this.price_sort = price_sort;
		this.good_rate_sort = good_rate_sort;
		this.cur_sort_str = cur_sort_str;
		this.pageCurrent = pageCurrent;
		this.maxPage = maxPage;
		this.placeList = placeList;
		this.startList = startList;
		this.themeList = themeList;
		this.trafficList = trafficList;
		this.timeList = timeList;
	}
	
	public SearchBean(SearchForm vo, List<Trip> allTripList){
		vo.setTotalResult(allTripList.size());
		vo.setMaxPage(allTripList.size());
		this.type_name = vo.getType_name(); 
		this.place_name = vo.getPlace_name();
		this.start_place_name = vo.getStart_place_name(); 
		this.theme_name = vo.getTheme_name(); 
		this.traffic = vo.getTraffic(); 
		this.time = vo.getTime();
		this.search_key = vo.getSearch_key(); 
		this.totalResult = vo.getTotalResult(); 
		this.min_price = vo.getMin_price(); 
		this.max_price = vo.getMax_price(); 
		this.price_sort = vo.getPrice_sort(); 
		this.good_rate_sort = vo.getGood_rate_sort(); 
		this.cur_sort_str = vo.getCur_sort_str(); 
		this.pageCurrent = vo.getPageCurrent(); 
		this.maxPage = vo.getMaxPage(); 
		this.placeList = getCurPlaceList(allTripList);
		this.startList = getCurStartList(allTripList); 
		this.themeList = getCurThemeList(allTripList); 
		this.trafficList = getCurTrafficList(allTripList); 
		this.timeList = getCurTimeList(allTripList);
	}
	
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getPlace_name() {
		return place_name;
	}
	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}
	public String getStart_place_name() {
		return start_place_name;
	}
	public void setStart_place_name(String start_place_name) {
		this.start_place_name = start_place_name;
	}
	public String getTheme_name() {
		return theme_name;
	}
	public void setTheme_name(String theme_name) {
		this.theme_name = theme_name;
	}
	public String getTraffic() {
		return traffic;
	}
	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	public String getSearch_key() {
		return search_key;
	}
	public void setSearch_key(String search_key) {
		this.search_key = search_key;
	}
	public int getTotalResult() {
		return totalResult;
	}
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}
	public Integer getMin_price() {
		return min_price;
	}
	public void setMin_price(Integer min_price) {
		this.min_price = min_price;
	}
	public Integer getMax_price() {
		return max_price;
	}
	public void setMax_price(Integer max_price) {
		this.max_price = max_price;
	}
	public String getPrice_sort() {
		return price_sort;
	}
	public void setPrice_sort(String price_sort) {
		this.price_sort = price_sort;
	}
	public String getGood_rate_sort() {
		return good_rate_sort;
	}
	public void setGood_rate_sort(String good_rate_sort) {
		this.good_rate_sort = good_rate_sort;
	}
	public String getCur_sort_str() {
		return cur_sort_str;
	}
	public void setCur_sort_str(String cur_sort_str) {
		this.cur_sort_str = cur_sort_str;
	}
	public int getPageCurrent() {
		return pageCurrent;
	}
	public void setPageCurrent(int pageCurrent) {
		this.pageCurrent = pageCurrent;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public List<String> getPlaceList() {
		return placeList;
	}
	public void setPlaceList(List<String> placeList) {
		this.placeList = placeList;
	}
	public List<String> getStartList() {
		return startList;
	}
	public void setStartList(List<String> startList) {
		this.startList = startList;
	}
	public List<String> getThemeList() {
		return themeList;
	}
	public void setThemeList(List<String> themeList) {
		this.themeList = themeList;
	}
	public List<String> getTrafficList() {
		return trafficList;
	}
	public void setTrafficList(List<String> trafficList) {
		this.trafficList = trafficList;
	}
	public List<Integer> getTimeList() {
		return timeList;
	}
	public void setTimeList(List<Integer> timeList) {
		this.timeList = timeList;
	}
	
	/**
	 * 获取当前行程列表中所包含的出行天数集合
	 * 
	 * @param tripList
	 *            行程集合
	 * @return 一个不含重复数据的出行天数集合
	 */
	private List<Integer> getCurTimeList(List<Trip> tripList) {
		List<Integer> list = new ArrayList<>();
		for (Trip t : tripList)
			if (!list.contains(t.getTime()))
				list.add(t.getTime());
		Collections.sort(list);
		return list;
	}

	/**
	 * 获取当前行程列表中所包含的交通工具名称集合
	 * 
	 * @param tripList
	 *            行程集合
	 * @return 一个不含重复数据的交通工具名称集合
	 */
	private List<String> getCurTrafficList(List<Trip> tripList) {
		List<String> list = new ArrayList<>();
		for (Trip t : tripList) {
			String[] traffics = t.getTraffic().split("\\+");
			for (String traf : traffics) {
				if (!list.contains(traf))
					list.add(traf);
			}
		}
		return list;
	}

	/**
	 * 获取当前行程列表中所包含的主题名称集合
	 * 
	 * @param tripList
	 *            行程集合
	 * @return 一个不含重复数据的主题名称集合
	 */
	private List<String> getCurThemeList(List<Trip> tripList) {
		List<String> list = new ArrayList<>();
		for (Trip t : tripList)
			for (ThemeOnTrip tot : t.getThemeontrip_list())
				if (!list.contains(tot.getTheme().getName()))
					list.add(tot.getTheme().getName());
		return list;
	}

	/**
	 * 获取当前行程列表中所包含的出发地名称集合
	 * 
	 * @param tripList
	 *            行程集合
	 * @return 一个不含重复数据的出发地名称集合
	 */
	private List<String> getCurStartList(List<Trip> tripList) {
		List<String> list = new ArrayList<>();
		for (Trip t : tripList)
			if (!list.contains(t.getStart().getName()))
				list.add(t.getStart().getName());
		return list;
	}

	/**
	 * 获取当前行程列表中所包含的景点名称集合
	 * 
	 * @param tripList
	 *            行程集合
	 * @return 一个不含重复数据的景点名称集合
	 */
	private List<String> getCurPlaceList(List<Trip> tripList) {
		List<String> list = new ArrayList<>();
		for (Trip t : tripList)
			for (PlaceOnTrip pot : t.getPlaceontrip_list())
				if (!list.contains(pot.getPlace().getName()))
					list.add(pot.getPlace().getName());
		return list;
	}
}
