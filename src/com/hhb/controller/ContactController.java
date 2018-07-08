package com.hhb.controller;


import com.hhb.dao.ContactDao;
import com.hhb.dao.OrderContactDao;
import com.hhb.dto.Result;
import com.hhb.entity.Contact;
import com.hhb.entity.User;
import com.hhb.form.ContactForm;
import com.hhb.form.OrderForm;
import com.hhb.form.WaitCommentForm;
import com.hhb.globle.Constants;
import com.hhb.utils.Utils;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ContactController  {

    @Autowired
    ContactDao contactDao;
    @Autowired
    OrderContactDao orderContactDao;

    private HttpServletRequest request;

    @RequestMapping(value = "/user/contacts", method = {RequestMethod.GET,RequestMethod.POST})
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

    @RequestMapping(value = "/user/contacts/save", method = RequestMethod.POST,produces = {
            "application/json; charset=utf-8" })
    @ResponseBody
    public Result saveContact(HttpServletRequest request){
        this.request = request;
        User user = (User) request.getSession().getAttribute(Constants.USER_KEY);
        Contact contact = new Contact();
        contact = getContactFromRequest(contact);
        contactDao.saveContactWithUserId(contact, user.getId());
        return new Result(true,Constants.OP_SUCCESS);
    }

    @RequestMapping(value = "/user/contacts/update", method = RequestMethod.POST,produces = {
            "application/json; charset=utf-8" })
    @ResponseBody
    public Result updateContact(HttpServletRequest request){
        this.request = request;
        String contactId = request.getParameter("c_id");
        if (contactId == null || "".equals(contactId))
            return null;
        int id = Integer.parseInt(contactId);
        Contact contact = contactDao.getContactById(id);

        getContactFromRequest(contact);
        contactDao.updateContact(contact);
        return new Result(true,Constants.OP_SUCCESS);
    }

    @RequestMapping(value = "/user/contacts/del", method = RequestMethod.POST,produces = {
            "application/json; charset=utf-8" })
    @ResponseBody
    public Result deleteContact(HttpServletRequest request){
        this.request = request;
        String c_contactid = request.getParameter("c_id");
        if (c_contactid == null)
            return new Result(false,Constants.OP_FAIL);
        int id = Integer.parseInt(c_contactid);
        // 直接删除，由于外键关联会报错
        int count = orderContactDao.getOrderContactsCount(id);
        if (count == 0) {
            System.out.println("yes");
            contactDao.delete(id);
            return new Result(true,Constants.OP_SUCCESS);
        } else{
            System.out.println("no");
            return new Result(false,Constants.OP_FAIL);
        }
    }

    /**
     * 对提交的空字符进行验证
     */
    private boolean validateData(String name, String phone, String real_name) {
        if ("".equals(name) || "".equals(phone) || "".equals(real_name))
            return false;
        if (name == null || phone == null || real_name == null)
            return false;

        return true;
    }

    private Contact getContactFromRequest(Contact contact)  {

        String c_name = request.getParameter("c_name");
        String c_sexStr = request.getParameter("c_sex");
        String c_phone = request.getParameter("c_mobile");
        String c_email = request.getParameter("c_email");
        String c_year = request.getParameter("c_sel_year");
        String c_month = request.getParameter("c_sel_month");
        String c_day = request.getParameter("c_sel_day");
        String c_cardno = request.getParameter("c_cardno");
        boolean c_sex = "1".equals(c_sexStr) ? true : false;

        Date birthday;
        if (c_year == null || "".equals(c_year) || "0".equals(c_year)) {
            birthday = null;
        } else {
            if (Integer.parseInt(c_month) < 10)
                c_month = "0" + c_month;

            if (Integer.parseInt(c_day) < 10)
                c_day = "0" + c_day;
            birthday = Utils.stringToDate(c_year + c_month + c_day);
        }
        if (!validateData(c_name, c_phone, c_cardno)) {
            return null;
        }

        contact.setBirthday(birthday);
        contact.setName(c_name);
        contact.setPhone(c_phone);
        contact.setSex(c_sex);
        contact.setCardno(c_cardno);
        contact.setEmail(c_email);

        return contact;
    }


}
