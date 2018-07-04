package com.test;

import com.hhb.dao.CityDao;
import com.hhb.dao.CommentDao;
import com.hhb.dao.TripDao;
import com.hhb.entity.City;
import com.hhb.entity.ThemeOnTrip;
import com.hhb.entity.Trip;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TripTest extends BaseTest {
    @Autowired
    private TripDao tripDao;

    @Autowired
    private CityDao cityDao;

    @Autowired
    private CommentDao commentDao;

    @Test
    public void TripDaoTest() {
        int id = 1;
        Trip tripById = tripDao.getTripById(id);
        System.out.println(tripById.getTraffic_score());
//        for(ThemeOnTrip t:tripById.getThemeontrip_list()) {
//            System.out.println(t);
//        }
    }
}
