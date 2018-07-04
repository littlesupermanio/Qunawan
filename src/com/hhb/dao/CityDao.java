package com.hhb.dao;

import com.hhb.entity.City;

import java.util.List;

public interface CityDao {
    City getCityById(int id);


    /**
     * 获取所有的省份
     *
     * @return
     */
    List<City> getAllProvinces();

    /**
     * 通过省份级别的id获取下属所有城市
     *
     * @param id
     * @return
     */
    List<City> getAllCityByParentId(int id);

    /**
     * 通过城市名称查找City对象集合
     *
     * @param name
     *            城市名称
     * @return 名称对应的City对象
     */
    List<City> getCityByName(String name);

}
