package com.hhb.dao;

import com.hhb.entity.Theme;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ThemeDao {

    /**
     * 通过主题名称得到Theme对象集合
     *
     * @param name
     *            主题名称
     * @return Theme对象
     */
    List<Theme> getThemeByName(String name);

    /**
     * 根据行程类型获取主题名称列表
     * @param id 行程类型id
     * @param start 分页开始索引
     * @param maxCount 每页最大值
     * @return 分页主题名称列表
     */
    List<String> getPageThemesByType(@Param("tripType") int id, @Param("offset") int start, @Param("limit") int maxCount);

    /**
     * 通过主题id得到Theme对象
     * @param id 主题id值
     * @return Theme对象
     */
    Theme getThemeById(int id);

}
