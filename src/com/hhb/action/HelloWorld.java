package com.hhb.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/helloworld")
public class HelloWorld {



    @RequestMapping("/hello")
    public String hello(){
        System.out.println("Hello WOrld!!!");
        return "success";
    }

    @RequestMapping("/hellodata")
    public ModelAndView model(){
        System.out.println("Hello WOrld!!!");

        ModelAndView mv = new ModelAndView();
        mv.addObject("name", "Hello world again!!!!");

        mv.setViewName("success");

        return mv;
    }

}
