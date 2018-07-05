package com.hhb.service.impl;

import com.hhb.dao.*;
import com.hhb.entity.*;
import com.hhb.globle.Constants;
import com.hhb.service.OrdersService;
import com.hhb.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {


    @Autowired
    private PriceDao priceDao;

    @Autowired
    private OrdersDao ordersDao;

    @Autowired
    private ContactDao contactDao;

    @Autowired
    private SequenceDao sequenceDao;

    @Autowired
    private OrderContactDao orderContactDao;

    @Override
    public List<Contact> getContactsByOrderId(int orderId) {
        List<Contact> ocs = new ArrayList<Contact>();

        List<OrderContact> orderContacts = orderContactDao.getOrderContacts(orderId, Constants.CONTACT_FOR_PLAY);
        if (orderContacts == null || orderContacts.size() < 1)
            return null;

        for (OrderContact oc : orderContacts) {
            ocs.add(oc.getContact());
        }

        return ocs;
    }
    @Override
    public Contact getContactByOrderId(int orderId) {
        return null;
    }

    @Override
    public Float getTripPrice(Date date, int num, Trip trip) {
        Price price = priceDao.getOneDayPrice(trip.getId(), date);
        Float p = price.getPrice();
        return p * num;
    }

    @Override
    public void putOrder(Orders order, Contact em_contact, List<Contact> clist) {
        /**
         *  1、保存订单
         */
        int oid = ordersDao.saveOrder(order);
        order.setId(oid);

        /**
         *  2、保存紧急联系人
         */
        int em_id = em_contact.getId();
        if(em_id == 0){
            em_id = contactDao.saveContact(em_contact);
        }
        em_contact.setId(em_id);
        OrderContact oc_em = new OrderContact(order, em_contact, Constants.EM_CONTACT);
        orderContactDao.saveOrderContact(oc_em);

        /**
         *  3、保存游玩人
         */
        for(Contact contact : clist){
            int cid = contact.getId();
            if(cid == 0){
                cid = contactDao.saveContact(contact);
            }
            contact.setId(cid);
            OrderContact oc_pl = new OrderContact(order, contact, Constants.PL_CONTACT);
            orderContactDao.saveOrderContact(oc_pl);
        }

    }

    @Override
    public Orders packOrder(int num, java.util.Date time, float total_price, String place, Trip trip, User user) {
        // 封装订单信息
        Orders order = new Orders();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestr = df.format(new Date(System.currentTimeMillis()));
        order.setCreate_time(timestr);

        order.setGo_point(place);
        order.setGo_time(trip.getSchedule().getTraffic().getGo_time());
        order.setNum(num);
        order.setStart_time(new java.sql.Date(time.getTime()));
        order.setTrip(trip);
        order.setUser(user);
        order.setTotal_price(total_price);

        // 订单状态生成
        Sequence sq = sequenceDao.getSeqByKeyAndType(Constants.WAIT_PAY_STATE, Constants.ORDER_TYPE);
        order.setState(sq);

        // 订单编号生成
        String no = Utils.createName();
        order.setOrderNo(no);
        return order;
    }

    @Override
    public Contact packContact(String id, String name, String phone, User user) {
        return packContact(id, name, phone, null, user);
    }

    @Override
    public Contact packContact(String id, String name, String phone, String cardno, User user) {
        Contact em_contact = null;
        if (id !=null && "-1".equals(id)) {
            if(cardno != null)
                em_contact = new Contact(user, name, phone, cardno);
            else
                em_contact = new Contact(user, name, phone);
        } else {
            em_contact = contactDao.getContactById(Integer.parseInt(id));
        }
        return em_contact;
    }
}
