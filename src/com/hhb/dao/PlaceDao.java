package com.hhb.dao;

import com.hhb.entity.Place;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlaceDao {
    /**
     * 通过景点地区名称得到Place对象集合
     *
     * @param name
     *            景点地区名称
     * @return Place对象
     */
    List<Place> getPlaceByName(String name);

    /**
     * 通过类型获取景点名称分页列表
     * @param id 行程类型
     * @return 景点名称列表
     */
    List<String> getPagePlacesByType(@Param("typeId") int id , @Param("offset") int offset, @Param("limit") int limit);

    /**
     * 通过景点id获得Place对象
     * @param id 景点id值
     * @return Place对象
     */
    Place getPlaceById(int id);

}
