package com.hhb.controller;

import com.hhb.bean.LoginInfo;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDao userDao;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView model(){

        ModelAndView mv = new ModelAndView();

        mv.setViewName("login");

        return mv;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private String login(@RequestParam(value="name") String condition, @RequestParam(value="password") String password, Model model, HttpServletRequest request) {

        User user = userDao.getUserByCondition(condition);
        if(user==null||!user.getPassword().equals(Utils.toMD5(password))) {
            model.addAttribute(Constants.ERROR, "用户名或密码错误");
            return "/login";
        }

        saveUserInfo(request,user);
        return "redirect:/index";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    private String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/index";
    }

    private void saveUserInfo(HttpServletRequest request, User user) {
        // 重新开启session，方便计算用户登录时间
        request.getSession().invalidate();
        HttpSession session = request.getSession();

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setIp(request.getRemoteAddr());
        loginInfo.setLoginName(user.getName());
        loginInfo.setLoginTime(new Date());
        session.setAttribute("loginInfo", loginInfo);

        // 把用户状态存入session中
        session.setAttribute(Constants.USER_KEY, user);
    }
}
