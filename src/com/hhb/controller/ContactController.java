package com.hhb.controller;


import com.hhb.dao.ContactDao;
import com.hhb.entity.Contact;
import com.hhb.entity.User;
import com.hhb.form.ContactForm;
import com.hhb.form.OrderForm;
import com.hhb.globle.Constants;
import com.hhb.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ContactController  {

    @Autowired
    ContactDao contactDao;

    @RequestMapping(value = "/user/contacts", method = RequestMethod.GET)
    public ModelAndView getOrders(@RequestParam(value = "page", required=false) String pageId, HttpServletRequest request){

        ModelAndView mv = new ModelAndView();
        User user = (User) request.getSession().getAttribute(Constants.USER_KEY);

        int page = Utils.getPageNum(pageId);
        int contactNum = contactDao.getAllContactByUser(user.getId()).size();
        int pageCount = (int) Math.ceil((float) contactNum / Constants.CONTACT_MAX);
        if (pageCount == 0)
            pageCount = 1;
        List<Contact> contacts = contactDao.getAllContactPerPage(user.getId(), page, Constants.CONTACT_MAX);
        List<ContactForm> formList = new ArrayList<ContactForm>();
        for (Contact contact : contacts) {
            ContactForm contactForm = new ContactForm(contact, pageCount);
            formList.add(contactForm);
        }
        if (formList.size() != 0)
            mv.addObject("pageCount", formList.get(0).getPageCount());
        mv.addObject("formList", formList);
        mv.addObject("cur", Utils.getPageNum(request.getParameter("page")));

        mv.setViewName("personal/personal_myContact");
        return mv;
    }
}
