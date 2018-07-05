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
import com.hhb.service.UserService;
import com.hhb.utils.Utils;
import com.sun.istack.internal.Nullable;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.Date;
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

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView personalInfo(HttpServletRequest request){

        ModelAndView mv = new ModelAndView();

        List<City> provinces = cityDao.getAllProvinces();
        request.getSession().setAttribute("provinces", provinces);
        mv.setViewName("personal/personal_information");
        return mv;
    }

    @RequestMapping(value = "/changePass", method = RequestMethod.GET)
    public ModelAndView getChangePass(HttpServletRequest request){

        ModelAndView mv = new ModelAndView();
        mv.setViewName("personal/personal_passwordChange");

        return mv;
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ModelAndView updateUser(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        User user = (User)request.getSession().getAttribute("user");
        String name = request.getParameter("name");
        String sexStr = request.getParameter("sex");
        String phone = request.getParameter("mobile");
        String email = request.getParameter("email");
        String real_name = request.getParameter("realname");
        String year = request.getParameter("sel_year");
        String month = request.getParameter("sel_month");
        String day = request.getParameter("sel_day");
        String cityStr = request.getParameter("city");
        if (!validateData(email, phone, sexStr)) {
            // 设置错误信息
            mv.addObject(Constants.ERROR, "基本信息不完整,更新失败");
            mv.setViewName("personal/personal_information");
            return mv;
        }

        // 对提交的信息进行手机和邮箱唯一性验证
        if (!userService.checkPhoneAndEmail(phone, email, user)) {
            mv.addObject(Constants.ERROR, "邮箱或者手机已存在");
            mv.setViewName("personal/personal_information");
            return mv;
        }

        // 对表单提交的数据进行封装处理
        boolean sex = "0".equals(sexStr) ? false : true;
        Date birthday;
        City city;
        if (year == null || "".equals(year) || "0".equals(year)) {
            birthday = null;
        } else {
            if (Integer.parseInt(month) < 10)
                month = "0" + month;

            if (Integer.parseInt(day) < 10)
                day = "0" + day;
            birthday = Utils.stringToDate(year + month + day);
        }
        if (cityStr == null || "".equals(cityStr)) {
            city = null;
        } else {
            city = cityDao.getCityById(Integer.parseInt(cityStr));
        }
        // 将数据封装到user对象
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setReal_name(real_name);
        user.setSex(sex);
        user.setCity(city);
        user.setBirthday(birthday);

        userDao.update(user);
        // 更新后的数据保存进session
        request.getSession().setAttribute(Constants.USER_KEY, user);

        mv.addObject(Constants.ERROR,"更新成功");
        mv.setViewName("personal/personal_information");

        return mv;
    }

    @RequestMapping(value = "/changePass", method = RequestMethod.POST)
    private String changePassword(@RequestParam("oldPassWord") String oldPassword, @RequestParam("newPassWord") String newPassword,Model model,HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Constants.USER_KEY);

        if(!checkPwd(oldPassword,user)) {
            model.addAttribute(Constants.ERROR,"密码不正确");
            return "personal/personal_passwordChange";
        }

        user.setPassword(Utils.toMD5(newPassword));
        userDao.update(user);

        return "redirect:/auth/login";
    }


    @RequestMapping(value = "/setAvatar", method = RequestMethod.POST)
    private ModelAndView setAvatar(@RequestParam(value="image",required=false) MultipartFile file, HttpServletRequest request) throws IOException {
        User user = (User) request.getSession().getAttribute("user");

        if(!file.isEmpty()){
            String file_name = Utils.createName()+".jpg";
            //获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType=file.getContentType();
            System.out.println(file_name);
            file.transferTo(new File(request.getSession().getServletContext().getRealPath("\\user_img\\"+file_name)));
            user.setImg_path(file_name);
        }
        ModelAndView mv = new ModelAndView();

        // 保存用户对象
        userDao.update(user);
        request.getSession().setAttribute(Constants.USER_KEY,user);
        mv.setViewName("redirect:/user");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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


    /**
     * 验证密码是否正确
     */
    private boolean checkPwd(String oldPassword, User user) {
        if (Utils.toMD5(oldPassword).equals(user.getPassword()))
            return true;
        else
            return false;
    }



    /**
     * 对提交的空字符进行验证
     */
    private boolean validateData(String email, String phone, String sex) {
        if ("".equals(email) || "".equals(phone) || "".equals(sex))
            return false;

        if (email == null || phone == null || sex == null)
            return false;

        return true;
    }


}
