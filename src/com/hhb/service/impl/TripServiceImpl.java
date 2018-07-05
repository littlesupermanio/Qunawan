package com.hhb.service.impl;

import com.hhb.bean.SearchBean;
import com.hhb.dao.*;
import com.hhb.entity.*;
import com.hhb.form.SearchForm;
import com.hhb.service.TripService;
import com.hhb.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TripServiceImpl implements TripService {
    @Autowired
    private CityDao cityDao;

    @Autowired
    private PlaceDao placeDao;

    @Autowired
    private ThemeDao themeDao;

    @Autowired
    private TripDao tripDao;

    @Autowired
    private SequenceDao sequenceDao;

    private List<Trip> allTripList = new ArrayList<>();

    @Override
    public void initTripPicture(Set<TripPicture> pictures, String basePath) {
        for (TripPicture tp : pictures) {
            String path = basePath + "image_cache\\" + tp.getName();
            if (!new File(path).exists()) {
                Utils.getFile(tp.getData(), path);
            }
        }
    }

    @Override
    public List<Trip> getSearchTripsByVo(SearchForm vo) {
        //处理表单数据
        packageForm(vo);
        allTripList = tripDao.getAllTripByCondition(vo);
        return tripDao.getPageTripByCondition(vo,vo.getFistResult(), vo.getMaxResult());
    }

    @Override
    public SearchBean getSearchBean(SearchForm vo) {
        SearchBean bean = new SearchBean(vo, allTripList);
        return bean;
    }


    /**
     * 表单信息处理<br>
     * 把相应的名称改为对应对象的id
     * @param vo 待处理的表单
     */
    private void packageForm(SearchForm vo) {
        // 如果行程类型字符串不为空，获取该行程类型的id
        if (vo.getType_name() != null) {
            String typename = vo.getType_name();
            Sequence s = sequenceDao.getSeqByValue(typename);
            if (s == null) {
                typename = Utils.isoToUtf(typename);
                vo.setType_name(typename);
                s = sequenceDao.getSeqByValue(typename);
            }
            vo.setType_id(s.getId());
        }
        // 如果出发地字符串不为空，获取该出发地的id
        if (vo.getStart_place_name() != null) {
            List<City> citys = cityDao.getCityByName(vo.getStart_place_name());
            List<Integer> cidList = new ArrayList<>();
            if (citys != null) {
                for(City city : citys){
                    cidList.add(city.getId());
                }
            }
            vo.setStart_place_id_list(cidList);
        }
        // 如果景点地区字符串不为空，获取该景点地区的id
        if (vo.getPlace_name() != null) {
            List<Place> pList = placeDao.getPlaceByName(vo.getPlace_name());
            List<Integer> pidList = new ArrayList<>();
            if (pList != null && !pList.isEmpty()) {
                for (Place p : pList) {
                    pidList.add(p.getId());
                }
            }
            vo.setPlace_id_list(pidList);
        }
        // 如果主题字符串不为空，获取该主题的id
        if (vo.getTheme_name() != null) {
            List<Theme> tList = themeDao.getThemeByName(vo.getTheme_name());
            List<Integer> tidList = new ArrayList<>();
            if (tList != null && !tList.isEmpty()) {
                for (Theme t : tList) {
                    tidList.add(t.getId());
                }
            }
            vo.setTheme_id_list(tidList);
        }
    }
}
