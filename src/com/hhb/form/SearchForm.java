package com.hhb.form;

import com.hhb.entity.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class SearchForm {

	private Integer type_id; // 行程类型id（Dao使用）
	private String type_name; // 行程类型名称（Service使用）
	private String place_name; // 行程所属景点名称（Service使用）
	private String start_place_name;// 行程出发地名称（Service使用）
	private List<Integer> place_id_list; // 行程所属景点id（Dao使用）
	private List<Integer> start_place_id_list; // 行程出发地id（Dao使用）
	private List<Integer> theme_id_list; // 行程所属主题id（Dao使用）
	private String theme_name; // 行程所属主题名称（Service使用）
	private String traffic; // 行程交通工具
	private Integer time; // 行程天数
	private Integer min_price; // 最低价格
	private Integer max_price; // 最高价格

	private String price_sort; // 按照价格排序的顺序说明
	private String good_rate_sort; // 按照好评排序的顺序说明
	private String cur_sort_str; // 当前选择的主排序规则

	private String search_key; // 页面头部的输入框关键字

	private int maxResult = 10; // 每页最大显示的行程数（默认为10）
	private int fistResult = 0; // 每页的第一条行程编号（默认为0）
	private int pageCurrent = 1; // 当前页码（默认为1）
	private int maxPage; // 总页码数

	private int totalResult = 0; // 检索出的总记录数

	private List<Trip> tripList; // 用于界面显示的行程列表

	private List<String> placeList; // 界面顶部筛选栏的景点列表
	private List<String> startList; // 界面顶部筛选栏的出发地列表
	private List<String> themeList; // 界面顶部筛选栏的主题列表
	private List<String> trafficList; // 界面顶部筛选栏的交通工具列表
	private List<Integer> timeList; // 界面顶部筛选栏的出行天数列表

	public SearchForm() {
	}

	public SearchForm(String type_name, String place_name, String start_place_name, String theme_name, String traffic,
			Integer time, Integer min_price, Integer max_price, int pageCurrent, String price_sort,
			String good_rate_sort, String cur_sort_str, String search_key) {
		super();
		this.type_name = type_name;
		this.place_name = place_name;
		this.start_place_name = start_place_name;
		this.theme_name = theme_name;
		this.traffic = traffic;
		this.time = time;
		this.min_price = min_price;
		this.max_price = max_price;
		this.pageCurrent = pageCurrent;
		this.price_sort = price_sort;
		this.good_rate_sort = good_rate_sort;
		this.cur_sort_str = cur_sort_str;
		this.search_key = search_key;
		// 通过当前页码设置第一条记录的索引
		setFistResult(pageCurrent);
	}

	public List<Integer> getPlace_id_list() {
		return place_id_list;
	}

	public void setPlace_id_list(List<Integer> place_id_list) {
		this.place_id_list = place_id_list;
	}

	public String getPlace_name() {
		return place_name;
	}

	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}

	public List<Integer> getTheme_id_list() {
		return theme_id_list;
	}

	public void setTheme_id_list(List<Integer> theme_id_list) {
		this.theme_id_list = theme_id_list;
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

	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public List<Integer> getStart_place_id_list() {
		return start_place_id_list;
	}

	public void setStart_place_id_list(List<Integer> start_place_id_list) {
		this.start_place_id_list = start_place_id_list;
	}

	public String getStart_place_name() {
		return start_place_name;
	}

	public void setStart_place_name(String start_place_name) {
		this.start_place_name = start_place_name;
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

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

	public int getFistResult() {
		return fistResult;
	}

	public void setFistResult(int pageCurrent) {
		this.fistResult = (pageCurrent - 1) * getMaxResult();
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

	public void setMaxPage(int totalResult) {
		this.maxPage = totalResult % getMaxResult() == 0 ? totalResult / getMaxResult()
				: totalResult / getMaxResult() + 1;
	}

	public int getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
		setMaxPage(totalResult);
	}

	public List<Trip> getTripList() {
		return tripList;
	}

	public void setTripList(List<Trip> tripList) {
		ArrayList<Trip> new_trips = new ArrayList<Trip>();
		for (Trip t : tripList) {
			Trip trip = new Trip();
			Sequence sequence = new Sequence();
			// 详情页trim函数场景测试用
			sequence.setValue("  " + t.getType().getValue());
			trip.setType(sequence);

			City start = new City();
			start.setName(t.getStart().getName());
			trip.setStart(start);

			List<ThemeOnTrip> tot_list = new ArrayList<>();
			for (ThemeOnTrip tot : t.getThemeontrip_list()) {
				ThemeOnTrip new_tot = new ThemeOnTrip();
				Theme th = new Theme();
				th.setName(tot.getTheme().getName());
				new_tot.setTheme(th);
				tot_list.add(new_tot);
			}
			trip.setThemeontrip_list(tot_list);

			List<PlaceOnTrip> pot_list = new ArrayList<>();
			for (PlaceOnTrip pot : t.getPlaceontrip_list()) {
				PlaceOnTrip new_pot = new PlaceOnTrip();
				Place p = new Place();
				p.setName(pot.getPlace().getName());
				new_pot.setPlace(p);
				pot_list.add(new_pot);
			}
			trip.setPlaceontrip_list(pot_list);

			Set<TripPicture> pic_list = new HashSet<>();
			for (TripPicture tp : t.getPic_list()) {
				TripPicture new_tp = new TripPicture();
				new_tp.setName(tp.getName());
				new_tp.setData(tp.getData());
				pic_list.add(new_tp);
			}
			trip.setPic_list(pic_list);

			List<Price> p_list = new ArrayList<>();
			for (Price p : t.getPrice_list()) {
				Price new_p = new Price();
				new_p.setPrice(p.getPrice());
				new_p.setDate(p.getDate());
				p_list.add(new_p);
			}
			trip.setPrice_list(p_list);

			trip.setId(t.getId());
			trip.setMain_picname(t.getMain_picname());
			trip.setMin_price(t.getMin_price());
			trip.setGood_rate(t.getGood_rate());
			trip.setTitle(t.getTitle());
			trip.setTraffic(t.getTraffic());
			trip.setHotel(t.getHotel());
			trip.setTime(t.getTime());
			new_trips.add(trip);
		}
		this.tripList = new_trips;
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

	public String getSearch_key() {
		return search_key;
	}

	public void setSearch_key(String search_key) {
		this.search_key = search_key;
	}

	public String getCur_sort_str() {
		return cur_sort_str;
	}

	public void setCur_sort_str(String cur_sort_str) {
		this.cur_sort_str = cur_sort_str;
	}

}
