package com.hhb.controller;

import com.hhb.dao.CommentDao;
import com.hhb.dao.TripDao;
import com.hhb.dto.Result;
import com.hhb.entity.Comment;
import com.hhb.entity.CommentPicture;
import com.hhb.entity.Trip;
import com.hhb.entity.User;
import com.hhb.globle.Constants;
import com.hhb.globle.Globle;
import com.hhb.service.CommentService;
import com.hhb.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/trip")
public class TripController {

    @Autowired
    private TripDao tripDao;

    @Autowired
    private TripService tripService;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/{tripId}", method = RequestMethod.GET)
    public ModelAndView model(@PathVariable("tripId") int tripId,HttpServletRequest request){
        ModelAndView mv = new ModelAndView();

        String type = request.getParameter("type");
        ServletContext context = request.getSession().getServletContext();

            // 通过产品id获得该产品对象
            Trip trip = tripDao.getTripById(tripId);
            // 初始化产品画廊的图片并加载到缓存
            tripService.initTripPicture(trip.getPic_list(),
                    context.getRealPath("/"));

            HttpSession session = request.getSession();
            // 把产品对象存到session中
            session.setAttribute("detail", trip);
            // 把产品评论总数存到session中
            session.setAttribute("num", commentDao.getCommentCount(tripId));

            // 如果当前用户已登录，则记录访问旅游详情页的时间和次数
            if (request.getSession().getAttribute(Constants.USER_KEY) != null) {
                // 获取用户id
                User user = (User) request.getSession().getAttribute(Constants.USER_KEY);
                // 添加详情页访问记录
                Globle.addAccessRecord(user.getId(), Constants.TRIP_DETAIL_PAGE);
        }
        if (tripId > 0) {
            mv.setViewName("trip_detail");
        }

        return mv;
    }

    @RequestMapping(value = "/{tripId}/comments", method = RequestMethod.POST, produces = {
            "application/json; charset=utf-8" })
    @ResponseBody
    public Result<List<Comment>> comments(@PathVariable("tripId") int tripId, @RequestParam("page") int page, HttpServletRequest request){
        List<Comment> comments = commentDao.getCommentsByTripId(tripId, page, Constants.COMMENT_DETAIL_MAX);
        for (Comment c : comments) {
            Set<CommentPicture> pictures = c.getPictures();
            commentService.initCommentPicture(pictures, request.getServletContext()
                    .getRealPath("/"));
        }

        return new Result<>(true, comments);
    }

}
