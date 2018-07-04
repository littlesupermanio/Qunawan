package com.hhb.service;

import com.hhb.entity.TripPicture;

import java.util.Set;

public interface TripService {
    /**
     * 初始化图片缓存
     * @return
     */
    void initTripPicture(Set<TripPicture> pictures, String basePath);

}
