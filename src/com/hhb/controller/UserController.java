package com.hhb.controller;

import com.hhb.dao.UserDao;
import com.hhb.entity.User;
import com.hhb.globle.Constants;
import com.hhb.service.UserService;
import com.hhb.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDao userDao;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView model(){

        ModelAndView mv = new ModelAndView();

        mv.setViewName("login");

        return mv;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private String login(@RequestParam(value="name") String condition, @RequestParam(value="password") String password, Model model) {

        User user = userDao.getUserByCondition(condition);
        System.out.println("----in1-----");
        if(user==null||!user.getPassword().equals(Utils.toMD5(password))) {
            System.out.println("----in2-----");
            model.addAttribute(Constants.ERROR, "用户名或密码错误");
            return "/login";
        }


        return "redirect:/index";
    }


}
