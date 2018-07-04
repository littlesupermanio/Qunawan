package com.hhb.service.impl;

import com.hhb.entity.TripPicture;
import com.hhb.service.TripService;
import com.hhb.utils.Utils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Set;

@Service
public class TripServiceImpl implements TripService {
    @Override
    public void initTripPicture(Set<TripPicture> pictures, String basePath) {
        for (TripPicture tp : pictures) {
            String path = basePath + "image_cache\\" + tp.getName();
            if (!new File(path).exists()) {
                Utils.getFile(tp.getData(), path);
            }
        }
    }
}
