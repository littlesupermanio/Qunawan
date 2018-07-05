package com.hhb.service;

import com.hhb.entity.Contact;
import com.hhb.entity.Orders;
import com.hhb.entity.Trip;
import com.hhb.entity.User;

import java.sql.Date;
import java.util.List;

public interface OrdersService {
    /**
     * 获取订单中对应的游玩人
     */
    List<Contact> getContactsByOrderId(int orderId);

    /**
     * 获取订单中对应的紧急联系人
     */
    Contact getContactByOrderId(int orderId);

    /**
     * 获取订单总价
     * @param date 订单日期
     * @param num 人数
     * @param trip 所属行程
     * @return 订单总价
     */
    Float getTripPrice(Date date, int num, Trip trip);

    /**
     * 下单操作
     * @param order 订单对象
     * @param em_contact 紧急联系人
     * @param clist 游玩人列表
     */
    void putOrder(Orders order, Contact em_contact, List<Contact> clist);

    /**
     * 封装订单
     * @param num 人数
     * @param time 下单时间
     * @param total_price 订单总价
     * @param trip 包含的行程
     * @param user 订单购买人
     * @return Orders对象
     */
    Orders packOrder(int num, java.util.Date time, float total_price, String place, Trip trip, User user);

    /**
     * 封装游玩人对象
     * @param id 游玩人id
     * @param name 名字
     * @param phone 手机号
     * @param user 所属用户
     * @return Contact对象
     */
    Contact packContact(String id, String name, String phone, User user);

    /**
     * 封装紧急联系人对象
     * @param id 紧急联系人id
     * @param name 名字
     * @param phone 手机号
     * @param cardno 身份证
     * @param user Contact对象
     * @return Contact对象
     */
    Contact packContact(String id, String name, String phone, String cardno, User user);
}
