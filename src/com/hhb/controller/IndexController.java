package com.hhb.controller;


import com.hhb.dao.PlaceDao;
import com.hhb.dao.SequenceDao;
import com.hhb.dao.ThemeDao;
import com.hhb.dao.TripDao;
import com.hhb.entity.Trip;
import com.hhb.globle.Constants;
import com.hhb.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    // 行程类型字符串数组，依次为 {自驾游、国内游和出境游}
    private String[] tripTypes = Constants.TRIP_TYPE;


    // 页面显示的景点列表
    Map<String, List<String>> placeMap = new HashMap<>();
    // 显示的行程列表
    Map<String, List<Trip>> tripMap = new HashMap<>();
    // 页面显示的主题列表
    Map<String, List<String>> themeMap = new HashMap<>();

    @Autowired
    SequenceDao sequenceDao;
    @Autowired
    PlaceDao placeDao;
    @Autowired
    TripDao tripDao;
    @Autowired
    ThemeDao themeDao;
    @Autowired
    TripService tripService;


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView model(HttpServletRequest request){
        // 使用'自驾游'、'国内游'和'出境游'为键，存放各自要显示的列表集合
        ServletContext context = request.getSession().getServletContext();

        for (int i = 0; i < 3; i++) {
            int typeId = sequenceDao.getSeqByValue(tripTypes[i]).getId();
            List<Trip> items = tripDao.getPageTripsByType(typeId, 0, 6);
            tripMap.put(tripTypes[i], items);
            for (Trip trip : items){
                tripService.initTripPicture(trip.getPic_list(),
                        context.getRealPath("/"));
            }

            placeMap.put(tripTypes[i], placeDao.getPagePlacesByType(typeId, 0, 10));
            themeMap.put(tripTypes[i], themeDao.getPageThemesByType(typeId, 0, 10));
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject(Constants.INDEX_TRIP_MAP, tripMap);
        mv.addObject(Constants.INDEX_PLACE_MAP,placeMap);
        mv.addObject(Constants.INDEX_THEME_MAP,themeMap);

        mv.setViewName("index");

        return mv;
    }
}
