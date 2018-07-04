package com.hhb.interceptor;

import com.hhb.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //获取请求的url
        String url = request.getRequestURI();
        //判断url是否公开地址（实际使用时将公开地址配置到配置文件中）
        //这里假设公开地址是否登陆提交的地址
        if(url.indexOf("login") > 0) {
            //如果进行登陆提交，放行
            return true;
        }

        //判断session
        HttpSession session = request.getSession();
        //从session中取出用户身份信息
        User user = (User) session.getAttribute("user");
        if(user != null) {
            return true;
        }

        //执行到这里表示用户身份需要验证，跳转到登陆页面
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
