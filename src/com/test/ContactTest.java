package com.test;

import com.hhb.dao.ContactDao;
import com.hhb.dao.OrderContactDao;
import com.hhb.entity.Contact;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ContactTest extends BaseTest {
    @Autowired
    ContactDao contactDao;
    @Autowired
    OrderContactDao orderContactDao;
    @Test
    public void update() {
        Contact c = contactDao.getContactById(100);
        c.setSex(true);
        contactDao.updateContact(c);
        System.out.println(c);
    }
    @Test
    public void delete() {
        int count = orderContactDao.getOrderContactsCount(84);
        if (count == 0) {
            System.out.println(1111);
            contactDao.delete(84);
        }else{
            System.out.println(2222);
        }
    }
}
