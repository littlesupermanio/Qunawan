package com.hhb.controller;

import com.hhb.dao.ContactDao;
import com.hhb.dao.OrdersDao;
import com.hhb.dao.SequenceDao;
import com.hhb.dao.TripDao;
import com.hhb.entity.*;
import com.hhb.form.OrderDetailForm;
import com.hhb.form.OrderForm;
import com.hhb.form.PutOrderForm;
import com.hhb.globle.Constants;
import com.hhb.service.OrdersService;
import com.hhb.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    private HttpServletRequest request;
    String pageId;

    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private TripDao tripDao;
    @Autowired
    private ContactDao contactDao;
    @Autowired
    private SequenceDao sequenceDao;
    @Autowired
    private OrdersService ordersService;


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

    @RequestMapping(value = "/{orderId}/detail", method = RequestMethod.GET)
    public ModelAndView getOrders(@PathVariable("orderId") int order_id, HttpServletRequest request){
        ModelAndView mv = new ModelAndView();

        OrderDetailForm orderDetailForm = new OrderDetailForm();

        orderDetailForm.setContact_many(ordersService.getContactsByOrderId(order_id));
        orderDetailForm.setContact_one(ordersService.getContactByOrderId(order_id));
        orderDetailForm.setOrder(ordersDao.getOrderById(order_id));

        request.getSession().setAttribute("orderDetailForm", orderDetailForm);

        mv.setViewName("order_detail");
        return mv;
    }

    @RequestMapping(value = "/{orderId}/pay", method = RequestMethod.GET)
    public ModelAndView payOrder(@PathVariable("orderId") int order_id, HttpServletRequest request){
        ModelAndView mv = new ModelAndView();

        Orders order = ordersDao.getOrderById(order_id);
        Sequence sq = sequenceDao.getSeqByKeyAndType(Constants.WAIT_COMMENT_ORDER_STATE, Constants.ORDER_TYPE);
        order.setState(sq);

        ordersDao.updateOrderState(sq, order_id);

        // 对页面输出信息
        PutOrderForm pof = new PutOrderForm(order);
        mv.addObject("vo", pof);

        mv.setViewName("order_success");
        return mv;
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public ModelAndView confirmOrder(@RequestParam("trip_id") String putOrder_trip_id,@RequestParam("num") String putOrder_numStr,
                                     @RequestParam("date") String putOrder_go_time,
                                     HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getId();
        ModelAndView mv = new ModelAndView();
        // 获取行程对象
        int tid = Integer.parseInt(putOrder_trip_id);
        Trip trip = tripDao.getTripById(tid);
        // 人数参数类型转换
        int num = Integer.parseInt(putOrder_numStr);

        // 日期参数类型转换
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date time = formatter.parse(putOrder_go_time, pos);
        java.sql.Date date = new java.sql.Date(time.getTime());

        // 通过出行日期和人数以及产品，查询总价格
        float price = ordersService.getTripPrice(date, num, trip);
        List<Contact> contacts = contactDao.getAllContactByUser(userId);

        // 订单保存进行确认处理
        request.getSession().setAttribute("put_order_trip", trip);
        request.getSession().setAttribute("put_order_num", num);
        request.getSession().setAttribute("put_order_price", price);
        request.getSession().setAttribute("put_order_time", time);
        request.getSession().setAttribute("put_order_contactlist", contacts);

        mv.setViewName("order");
        return mv;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView putOrder(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        ModelAndView mv = new ModelAndView();

        // 获取紧急联系人信息
        String em_id = request.getParameter("em_id");
        String em_name = request.getParameter("em_name");
        String em_phone = request.getParameter("em_phone");

        // 获取订单的详细信息
        Trip trip = (Trip) request.getSession().getAttribute("put_order_trip");
        float price = (float) request.getSession().getAttribute("put_order_price");
        int num = (int) request.getSession().getAttribute("put_order_num");
        Date time = (Date) request.getSession().getAttribute("put_order_time");

        // 遍历获取游玩人的信息，保存在数组之中
        String[] id = request.getParameterValues("w_id");
        String[] name = request.getParameterValues("w_name");
        String[] phone = request.getParameterValues("w_phone");
        String[] cardno = request.getParameterValues("w_cardno");
        // 获取选择上车的地点信息
        String place = request.getParameter("place");

//        System.out.println("num"+num);
//        System.out.println("price" + price);

        // 封装订单信息
        Orders order = ordersService.packOrder(num, time, price, place, trip, user);
        // 生成紧急联系人
        Contact em_contact = ordersService.packContact(em_id, em_name, em_phone, user);
        // 生成游客
        List<Contact> contacts = new ArrayList<Contact>();
        for (int i = 0; i < num; i++) {
            String cid = "";
            if(id != null && i<id.length)
                cid = id[i];
            Contact contact = ordersService.packContact(cid, name[i], phone[i], cardno[i], user);
            contacts.add(contact);
        }

        ordersService.putOrder(order, em_contact, contacts);

        // 数据封装并响应
        PutOrderForm pof = new PutOrderForm(order);
        mv.addObject("vo", pof);

        mv.setViewName("order_pay");
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
