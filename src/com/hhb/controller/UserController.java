package com.hhb.controller;


import com.hhb.dao.CityDao;
import com.hhb.dao.OrdersDao;
import com.hhb.dao.UserDao;
import com.hhb.dto.Result;
import com.hhb.entity.City;
import com.hhb.entity.Comment;
import com.hhb.entity.Orders;
import com.hhb.entity.User;
import com.hhb.form.OrderForm;
import com.hhb.globle.Constants;
import com.hhb.utils.Utils;
import com.sun.istack.internal.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private CityDao cityDao;

    @Autowired
    private OrdersDao ordersDao;

    private HttpServletRequest request;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ModelAndView personalInfo(@PathVariable("userId") int userId, HttpServletRequest request){

        ModelAndView mv = new ModelAndView();

        User user = (User) request.getSession().getAttribute(Constants.USER_KEY);

        if(user==null){
            mv.setViewName("redirect:/index");
            return mv;
        }

        List<City> provinces = cityDao.getAllProvinces();
        request.getSession().setAttribute("provinces", provinces);
        mv.setViewName("personal/personal_information");
        return mv;
    }




    @RequestMapping(value = "/getCity", method = RequestMethod.GET,produces = {
            "application/json; charset=utf-8" })
    @ResponseBody
    public Result<List<City>> getCity(HttpServletRequest request){
        String idStr = request.getParameter("province");
        if (idStr == null || "".equals(idStr))
            return null;
        int id = Integer.parseInt(idStr.trim());
        List<City> cityList = cityDao.getAllCityByParentId(id);
        return new Result<>(true, cityList);
    }




}
