package com.hhb.controller;

import com.hhb.dao.CommentDao;
import com.hhb.dao.OrdersDao;
import com.hhb.dao.SequenceDao;
import com.hhb.dto.Result;
import com.hhb.entity.Comment;
import com.hhb.entity.Orders;
import com.hhb.entity.Sequence;
import com.hhb.entity.User;
import com.hhb.form.FinishedCommentForm;
import com.hhb.form.WaitCommentForm;
import com.hhb.globle.Constants;
import com.hhb.globle.Globle;
import com.hhb.service.CommentService;
import com.hhb.service.TripService;
import com.hhb.utils.Utils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private SequenceDao sequenceDao;
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private CommentService commentService;
    @Autowired
    private TripService tripService;

    private int userId;
    private int orderId;
    private HttpServletRequest request;

    @RequestMapping(value = "/user/comments", method = RequestMethod.GET)
    public ModelAndView getComments(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("personal/personal_myComments");
        return mv;
    }

    @RequestMapping(value = "/user/comments/finished", method = RequestMethod.POST,produces = {
            "application/json; charset=utf-8" })
    @ResponseBody
    public Result<List<FinishedCommentForm>> getFinishedComments(@RequestParam("page") int page, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        Sequence sq_commented = sequenceDao.getSeqByKeyAndType(Constants.COMMENTED_ORDER_STATE, Constants.ORDER_TYPE);

        int finishCount = commentDao.getCommentedCountByUser(user.getId());
        // 页码总数
        int pageCount = (int) Math.ceil((double)finishCount/Constants.COMMENT_MAX);
        // 获取当前用户已经评的列表
        List<Comment> comments = commentDao.getCommentsPerPage(user.getId(), sq_commented.getId(), page, Constants.COMMENT_MAX);

        List<FinishedCommentForm> results = new ArrayList<FinishedCommentForm>();
        // 把每个评论对象的属性封装到用于页面显示的表单集合中
        for (Comment comment : comments) {
//            System.out.println("comment:" + comment+" pageCount" + pageCount + " , " + "finishCount: " + finishCount);
            FinishedCommentForm fcf = new FinishedCommentForm(comment, pageCount, finishCount);

            // 更新缓存的评论图片
            if (comment.getPictures() != null)
                commentService.initCommentPicture(comment.getPictures(), request.getServletContext()
                        .getRealPath("/"));

            results.add(fcf);
        }

        return new Result<>(true, results);
    }

    @RequestMapping(value = "/user/comments/wait", method = RequestMethod.POST,produces = {
            "application/json; charset=utf-8" })
    @ResponseBody
    public Result<List<WaitCommentForm>> getWaitComments(@RequestParam("page") int page, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        Sequence sq_to_comment = sequenceDao.getSeqByKeyAndType(Constants.WAIT_COMMENT_ORDER_STATE,
                Constants.ORDER_TYPE);

        int waitCount = ordersDao.getOrdersByUser(user.getId(), sq_to_comment);
        int finishCount = commentDao.getCommentedCountByUser(user.getId());
        int pageCount = (int) Math.ceil((double) waitCount / Constants.COMMENT_MAX);

        // 获取该用户待评价的订单集合
        List<Orders> orderList = ordersDao.getOrdersWithSequenceId(user.getId(), sq_to_comment.getId(), page, Constants.COMMENT_MAX);

        List<WaitCommentForm> formList = new ArrayList<WaitCommentForm>();
        // 把每个订单对象的属性封装到用于页面显示的表单集合中
        for (Orders order : orderList) {
            WaitCommentForm wco = new WaitCommentForm(order, pageCount, waitCount, finishCount,
                    commentDao.getCommentedCountByTrip(order.getTrip().getId()));

            tripService.initTripPicture(order.getTrip().getPic_list(), request.getServletContext()
                    .getRealPath("/"));
            formList.add(wco);
        }
        if (orderList.size() == 0) {
            WaitCommentForm wco = new WaitCommentForm(waitCount, finishCount);
            formList.add(wco);
        }

        return new Result<>(true, formList);
    }

    @RequestMapping(value = "/user/comments/new", method = RequestMethod.POST)
    public ModelAndView submitComment(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        userId = user.getId();
        this.request = request;
        ModelAndView mv = new ModelAndView();


        Comment comment = analysisComment();

        // 四项评分，一项评论内容，一个图片集
        if (comment == null) {
            mv.addObject(Constants.COMMENT_INFO, "评论失败");
            mv.setViewName("personal/personal_myComments");
            return mv;
        }

        List<byte[]> byteArray = null;
        byteArray = Globle.getPics(userId);
        Sequence sq_commented = sequenceDao.getSeqByKeyAndType(Constants.COMMENTED_ORDER_STATE, Constants.ORDER_TYPE);

        // 保存comment + commentpic
        commentService.saveComment(comment, byteArray, sq_commented);

        mv.addObject(Constants.COMMENT_INFO, "评论成功");
        mv.setViewName("personal/personal_myComments");

        // 清除Globle中缓存
        Globle.clear(userId);
        return mv;


    }

    /**
     * 解析request请求，获取表单域数据
     */
    private Comment analysisComment() {
        Comment comment = new Comment();

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        upload.setHeaderEncoding("UTF-8");
        List<FileItem> items = null;

        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        if (items == null)
            return null;

        String commentText = null;
        // 解析request请求
        Iterator<FileItem> iter = items.iterator();
        while (iter.hasNext()) {
            FileItem item = (FileItem) iter.next();

            // 如果是表单域 ，就是非文件上传元素
            if (item.isFormField()) {

                // 获取name属性的值
                String name = item.getFieldName();

                // 获取value属性的值
                String value = item.getString();

                if ("orderid".equals(name)) {
                    orderId = Integer.parseInt(value);
                }
                if ("siteScore".equals(name)) {
                    String siteScoreStr = ("".equals(value) || null == value) ? "1" : value;
                    int siteScore = Integer.parseInt(siteScoreStr);
                    comment.setPlace(siteScore);
                }
                if ("hotelScore".equals(name)) {
                    String hotelScoreStr = ("".equals(value) || null == value) ? "1" : value;
                    int hotelScore = Integer.parseInt(hotelScoreStr);
                    comment.setHotel(hotelScore);
                }
                if ("serviceScore".equals(name)) {
                    String serviceScoreStr = ("".equals(value) || null == value) ? "1" : value;
                    int serviceScore = Integer.parseInt(serviceScoreStr);
                    comment.setService(serviceScore);
                }
                if ("trafficScore".equals(name)) {
                    String trafficScoreStr = ("".equals(value) || null == value) ? "1" : value;
                    int trafficScore = Integer.parseInt(trafficScoreStr);
                    comment.setTraffic(trafficScore);
                }
                if ("content".equals(name)) {
                    commentText = ("".equals(value) || null == value) ? "没有任何评论" : value;
                }
            }
        }

        try {
            if (commentText != null && !"".equals(commentText)) {
                commentText = new String(commentText.getBytes("ISO8859_1"), "utf-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        // 获取待评论的订单对象
        Orders order = ordersDao.getOrderById(orderId);
        System.out.println(order);
        comment.setOrders(order);
        comment.setContent(commentText);
        comment.setTime(new Timestamp(new java.util.Date().getTime()));
        comment.setUser(new User(userId));
        comment.setTrip(order.getTrip());
        return comment;
    }
}
