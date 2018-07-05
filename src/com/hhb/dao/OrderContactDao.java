package com.hhb.dao;

import com.hhb.entity.OrderContact;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderContactDao {
    /**
     * 通过联系人的类型以及订单编号获取订单与联系人之间的关系
     *
     * @param orderId
     *            订单编号
     * @param type
     *            联系人的类型
     * @return 订单联系人关系集合
     */
    List<OrderContact> getOrderContacts(@Param("orderId") int orderId, @Param("typeId") int type);

    /**
     * 获取单个联系人的所有订单联系人关系
     *
     * @param contactId
     *            联系人id
     * @return 返回订单联系人关系集合
     */
    int getOrderContactsCount(int contactId);

    /**
     * 添加订单联系人
     * @param con 用于事务管理的连接对象
     * @param oc 订单联系人对象
     */
    void saveOrderContact(OrderContact oc);

}
