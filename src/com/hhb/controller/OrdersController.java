package com.hhb.controller;

import com.hhb.dao.OrdersDao;
import com.hhb.entity.Orders;
import com.hhb.form.OrderDetailForm;
import com.hhb.form.OrderForm;
import com.hhb.globle.Constants;
import com.hhb.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    private HttpServletRequest request;
    String pageId;

    @Autowired
    OrdersDao ordersDao;

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public ModelAndView getOrders(@PathVariable("userId") int userId, @RequestParam(value = "page", required=false) String pageId, HttpServletRequest request){
        this.pageId = pageId;
        ModelAndView mv = new ModelAndView();
        List<OrderForm> orderForms = getOrders(userId);
        mv.addObject("orderFormList", orderForms);
        mv.addObject("pageCount", orderForms .size() != 0 ? orderForms.get(0).getPageCount() : 1);
        mv.addObject("cur", Utils.getPageNum(request.getParameter("page")));

        mv.setViewName("personal/personal_myOrder");
        return mv;
    }

    @RequestMapping(value = "/detail/{orderId}", method = RequestMethod.GET)
    public ModelAndView getOrders(@PathVariable("orderId") int orderId, HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        OrderDetailForm orderDetailForm = new OrderDetailForm();


        mv.setViewName("personal/personal_myOrder");
        return mv;
    }


    /**
     * 获取用户的所有订单
     * @param userId 用户的id
     */
    private List<OrderForm> getOrders(Integer userId) {
        // 接收要查询的订单页码
        String pageStr = pageId;
        // 类型转换
        int page = Utils.getPageNum(pageStr);
        // 获取订单的总数目
        int orderCount = ordersDao.getOrdersCountByUser(userId);
        // 计算列表的总页码
        int pageCount = (orderCount % Constants.ORDER_MAX == 0) ? (orderCount / Constants.ORDER_MAX)
                : (orderCount / Constants.ORDER_MAX + 1);
        // 获取查询页的订单集合
        List<Orders> orders = ordersDao.getOrders(userId, page, Constants.ORDER_MAX);
        // 将查询的订单集合进行重新封装处理
        List<OrderForm> orderForms = new ArrayList<OrderForm>();
        for (Orders order : orders) {
            OrderForm orderForm = new OrderForm();
            orderForm.setOrderid(order.getId());
            orderForm.setContent(order.getTrip().getTitle());
            orderForm.setCreate_time(order.getCreate_time().toString());
            orderForm.setOrderno(order.getOrderNo());
            orderForm.setPerson_num(order.getNum());
            orderForm.setPrice(order.getTotal_price());
            orderForm.setStart_time(order.getStart_time().toString());
            orderForm.setState(order.getState());
            orderForm.setPageCount(pageCount);
            orderForm.setTotalDays(order.getTrip().getTime());
            orderForms.add(orderForm);
        }
        return orderForms;
    }

}
