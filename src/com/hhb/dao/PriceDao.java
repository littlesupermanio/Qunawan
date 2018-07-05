package com.hhb.dao;

import com.hhb.entity.Price;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

public interface PriceDao {
    /**
     * 根据行程和日期获取当天的价格
     * @param tripId 行程id
     * @param time 日期
     * @return 当天的价格对象
     */
    Price getOneDayPrice(@Param("tripId") Integer tripId, @Param("date") Date time);

    /**
     * 根据行程id获取价格集
     */
    List<Price> getPricesByTripId(Integer tripId);
}
