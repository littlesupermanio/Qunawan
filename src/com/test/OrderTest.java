package com.test;


import com.hhb.dao.OrdersDao;
import com.hhb.dao.SequenceDao;
import com.hhb.entity.Orders;
import com.hhb.entity.Sequence;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class OrderTest extends BaseTest {
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private SequenceDao sequenceDao;
    @Test
    public void TestOrderDao() {
        Sequence sequenceById = sequenceDao.getSequenceById(7);
        ordersDao.updateOrderState(sequenceById,1);
    }

    @Test
    public void SaveOrder() {
        Orders order = ordersDao.getOrderById(1);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestr = df.format(new Date(System.currentTimeMillis()));
        order.setCreate_time(timestr);
        int ordernum = ordersDao.saveOrder(order);
        System.out.println(order.getId());

    }

}
