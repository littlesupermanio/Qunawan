package com.hhb.service.impl;

import com.hhb.dao.CommentDao;
import com.hhb.dao.CommentPicDao;
import com.hhb.dao.OrdersDao;
import com.hhb.dao.TripDao;
import com.hhb.entity.Comment;
import com.hhb.entity.CommentPicture;
import com.hhb.entity.Sequence;
import com.hhb.service.CommentService;
import com.hhb.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import static com.hhb.utils.Utils.createName;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private TripDao tripDao;
    @Autowired
    private CommentPicDao commentPicDao;
    @Autowired
    private OrdersDao ordersDao;

    @Override
    public void saveComment(Comment comment, List<byte[]> byteArray, Sequence sq_commented) {

        /**
         * 1、存储评论对象，获取对应id
         */
        int com_id = commentDao.save(comment);

        /**
         * 2、存储评论图片集
         */
        if(byteArray != null){
            for (byte[] bs : byteArray) {
                CommentPicture comPic = new CommentPicture();
                comPic.setComment(new Comment(com_id));
                comPic.setData(bs);
                comPic.setName(createName() + ".jpg");
                commentPicDao.save(comPic);
            }
        }

        /**
         * 3、更新行程评分
         */
        //评论对应行程id
        int trip_id = comment.getOrders().getTrip().getId();
        //五星评论数（好评数）
        float good_count=0;
        //获取该产品的各星级对应的评论数列表
        List<Float[]> data = commentDao.getCountByAvg(trip_id);
        //总评论数，如果没有则设置为1，防止分母为0的异常
        float total_count=data.size()==0?1:data.size();
        for(Float[] avg_score : data){
            //取星级近似值
            int star = Math.round(avg_score[0]);
            if(star == 5)
                good_count += avg_score[1];
        }
        //计算好评率
        float good_rate = (float)good_count/total_count*100;

        float place_score = commentDao.getAvg_Score("place", trip_id);
        float hotel_score = commentDao.getAvg_Score("hotel", trip_id);
        float service_score = commentDao.getAvg_Score("service", trip_id);
        float traffic_score = commentDao.getAvg_Score("traffic", trip_id);

        comment.getTrip().setGood_rate(good_rate);
        comment.getTrip().setPlace_score(place_score);
        comment.getTrip().setService_score(service_score);
        comment.getTrip().setHotel_score(hotel_score);
        comment.getTrip().setTraffic_score(traffic_score);
        tripDao.updateScore(comment.getTrip());

        /**
         * 4、更新订单状态
         */
        ordersDao.updateOrderState(sq_commented, comment.getOrders().getId());
    }

    @Override
    public void initCommentPicture(Set<CommentPicture> pictures, String basePath) {
        if (pictures == null || pictures.size() == 0) {
            return;
        }
        for (CommentPicture cp : pictures) {
            String path = basePath + "image_cache\\" + cp.getName();
            if (!new File(path).exists()) {
                Utils.getFile(cp.getData(), path);
            }
        }
    }
}
