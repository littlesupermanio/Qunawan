package com.test;

import com.hhb.dao.CityDao;
import com.hhb.dao.CommentDao;
import com.hhb.dao.TripDao;
import com.hhb.entity.City;
import com.hhb.entity.ThemeOnTrip;
import com.hhb.entity.Trip;
import com.hhb.form.SearchForm;
import com.hhb.service.TripService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TripTest extends BaseTest {
    @Autowired
    private TripDao tripDao;

    @Autowired
    private CityDao cityDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    TripService tripService;

    @Test
    public void TripDaoTest() {
        int id = 1;
        Trip tripById = tripDao.getTripById(id);
        System.out.println(tripById.getTraffic_score());
//        for(ThemeOnTrip t:tripById.getThemeontrip_list()) {
//            System.out.println(t);
//        }
    }

    @Test
    public void SearchFormTest() {
        /* 获取页面条件筛选参数 */
        String type = "自驾游";
        String place = "";
        String start_place = "";
        String theme = "";
        String traffic = "";
        String time_str ="";
        String min_price_str = "";
        String max_price_str = "";

        String price_sort = "";
        String good_rate_sort = "";
        String cur_sort_str = "";

        String search_key = "";

        String pageCurrent_str = "";
        /* 行程天数和价格区间进行类型转换 */
        Integer time;
        Integer min_price;
        Integer max_price;
        Integer pageCurrent;
        try {
            time = Integer.parseInt(time_str);
        } catch (Exception e) {
            time = null;
        }
        try {
            min_price = Integer.parseInt(min_price_str);
        } catch (Exception e) {
            min_price = null;
        }
        try {
            max_price = Integer.parseInt(max_price_str);
        } catch (Exception e) {
            max_price = null;
        }
        try {
            pageCurrent = Integer.parseInt(pageCurrent_str);
        } catch (Exception e) {
            pageCurrent = 1;
        }

        SearchForm vo = new SearchForm(type, place, start_place, theme, traffic, time, min_price, max_price,
                pageCurrent, price_sort, good_rate_sort, cur_sort_str, search_key);

        // 获取搜索页面显示的行程列表
        List<Trip> list = tripService.getSearchTripsByVo(vo);
        System.out.println(list);
    }
}
