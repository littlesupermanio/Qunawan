package com.test;

import com.hhb.dao.CityDao;
import com.hhb.dao.PlaceDao;
import com.hhb.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PlaceTest extends BaseTest {
    @Autowired
    private PlaceDao placeDao;


    @Test
    public void PlaceDaoTest() {
        int type = 1;
        List<String> placesByType = placeDao.getPagePlacesByType(1, 0, 10);
        System.out.println(placesByType);
    }
}
