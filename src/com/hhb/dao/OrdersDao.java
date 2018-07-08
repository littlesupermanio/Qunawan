package com.hhb.dao;

import com.hhb.entity.Orders;
import com.hhb.entity.Sequence;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrdersDao {

        /**
         * 分页获取用户未点评的订单
         * @param userId 用户id
         * @param sq 未点评状态序列
         * @param page 页数
         * @return 订单集合
         */
        List<Orders> getOrdersWithSequenceId(@Param("userId") int userId, @Param("sequenceId") int sequenceId, @Param("offset") int page, @Param("limit")  int max);

        /**
         * 获得当前用户的订单分页列表
         * @param userId 用户id
         * @param page 页数
         * @param maxResult 每页的最大值
         * @return 分页订单集合
         */
        List<Orders> getOrders(@Param("userId") int userId,@Param("offset") int page, @Param("limit") int max);

        /**
         * 获取属于某用户的订单数量
         * @param userId 用户id
         * @return 订单集合总数
         */
        int getOrdersCountByUser(Integer userId);

        /**
         * 获取某用户特有状态的订单数量
         * @param userId 用户id
         * @param sq 状态对象
         * @return 订单集合数量
         */
        int getOrdersByUser(@Param("userId") Integer userId, @Param("sq") Sequence sq);

        /**
         * 获取某用户特有状态的订单数量
         * @param userId 用户id
         * @param sq 状态对象
         * @return 订单集合数量
         */
        int getOrdersCountByUserWithSequence(Integer userId, Sequence sq);

        /**
         * 通过id查找订单
         * @return 订单对象
         */
        Orders getOrderById(int id);


        /**
         * 更新订单-付款
         * @param state 需要更新到的状态
         * @param id 订单id
         */
        void updateOrderState(@Param("state") Sequence sequence, @Param("orderId") Integer id);

        /**
         * 保存订单
         * @param order 订单对象
         * @return 该订单记录的id值
         */
        int saveOrder(Orders order);
}

