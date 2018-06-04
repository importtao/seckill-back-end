package me.importtao.seckillbackend.controller;

import me.importtao.seckillbackend.model.Seller;
import me.importtao.seckillbackend.model.User;
import me.importtao.seckillbackend.service.OrderService;
import me.importtao.seckillbackend.util.SellerToken;
import me.importtao.seckillbackend.util.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Package me.importtao.seckillbackend.controller
 * Class OrderController
 * Description: TODO
 *
 * @author importtao
 * date 2018/5/14 16:10
 * @version V1.0
 */
@RestController
@CrossOrigin
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Resource
    private Token token;
    @Resource
    private OrderService orderService;
    @Resource
    private SellerToken sellerToken;

    @PostMapping("/order")
    public HashMap createOrder(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>(16);
        String userToken = request.getParameter("token");
        String goodsId = request.getParameter("goodsId");
        String modelCode = request.getParameter("modelCode");
        String addrStr = request.getParameter("addr");
        if(userToken == null||"".equals(userToken)){
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
            return map;
        }
        boolean tokenValidate = token.tokenValidate(userToken);
        if(!tokenValidate){
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
            return map;
        }
        if(goodsId == null||"".equals(goodsId)){
            map.put("status","1");
            map.put("msg","请选择有效商品");
            return map;
        }
        if(modelCode == null||"".equals(modelCode)){
            map.put("status","1");
            map.put("msg","请选择有效商品型号");
            return map;
        }
        if(addrStr == null||"".equals(addrStr)){
            map.put("status","1");
            map.put("msg","请选择收货地址");
            return map;
        }
        HashMap userDate = token.getUserByToken(userToken);
        User user = (User)userDate.get("user");
        logger.info(user.getUserId()+"---"+goodsId+"---"+Byte.valueOf(modelCode)+"---"+Long.valueOf(addrStr));
        map = orderService.createOrder(user.getUserId(),goodsId,Byte.valueOf(modelCode),Long.valueOf(addrStr));
        return map;
    }
    @PostMapping("/pay")
    public HashMap pay(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>(16);
        String userToken = request.getParameter("token");
        String orderId = request.getParameter("orderId");
        if(orderId == null||"".equals(orderId)){
            map.put("status","1");
            map.put("msg","请选择有效订单");
            return map;
        }
        if(userToken == null||"".equals(userToken)){
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
            return map;
        }
        boolean tokenValidate = token.tokenValidate(userToken);
        if(!tokenValidate){
            Byte s = 1;
            orderService.updateOrderState(s,orderId);
            return map;
        }
        Byte state = 1;
        map = orderService.updateOrderState(state,orderId);
        return map;
    }
    @GetMapping("/userOrder")
    public HashMap getUserOrderList(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>(16);
        String userToken = request.getParameter("token");
        if(userToken == null||"".equals(userToken)){
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
            return map;
        }
        boolean tokenValidate = token.tokenValidate(userToken);
        if(!tokenValidate){
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
            return map;
        }
        HashMap userData = token.getUserByToken(userToken);
        User user = (User)userData.get("user");
        map = orderService.getOrderListByUserId(user.getUserId());
        return map;
    }
    @GetMapping("/userOrderDetail")
    public HashMap getUserOrderDetail(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>(16);
        String userToken = request.getParameter("token");
        String orderId = request.getParameter("orderId");
        if(userToken == null||"".equals(userToken)){
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
            return map;
        }
        boolean tokenValidate = token.tokenValidate(userToken);
        if(!tokenValidate){
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
            return map;
        }
        if(orderId == null||"".equals(orderId)){
            map.put("status","1");
            map.put("msg","请选择有效订单");
            return map;
        }
        map = orderService.getOrderDetail(orderId);
        return map;
    }
    @GetMapping("/sellerOrderDetail")
    public HashMap getSellerOrderDetail(HttpServletRequest request){
        String token = request.getParameter("sellerToken");
        String orderId = request.getParameter("orderId");
        HashMap<String,Object> map = new HashMap(16);
        if(token == null||"".equals(token)){
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
            return map;
        }
        if(orderId == null||"".equals(orderId)){
            map.put("status","1");
            map.put("msg","请选择有效订单");
            return map;
        }
        boolean tokenValidate = sellerToken.tokenValidate(token);
        if(tokenValidate){
            map = orderService.getOrderDetail(orderId);
        }else{
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
        }
        return map;
    }
    @GetMapping("/sellerPendingOrder")
    public HashMap getSellerPendingOrder(HttpServletRequest request){
        String token = request.getParameter("sellerToken");
        HashMap<String,Object> map = new HashMap(16);
        if(token == null||"".equals(token)){
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
            return map;
        }

        boolean tokenValidate = sellerToken.tokenValidate(token);
        if(tokenValidate){
            HashMap sellerData = sellerToken.getSellerByToken(token);
            Seller seller = (Seller) sellerData.get("seller");
            map = orderService.getPendingOrderListBySellerId(seller.getSellerId());
        }else{
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
        }
        return map;
    }
    @GetMapping("/sellerHistoricalOrder")
    public HashMap getSellerHistoricalOrder(HttpServletRequest request){
        String token = request.getParameter("sellerToken");
        HashMap<String,Object> map = new HashMap(16);
        if(token == null||"".equals(token)){
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
            return map;
        }
        boolean tokenValidate = sellerToken.tokenValidate(token);
        if(tokenValidate){
            HashMap sellerData = sellerToken.getSellerByToken(token);
            Seller seller = (Seller) sellerData.get("seller");
            map = orderService.getHistoricalOrderListBySellerId(seller.getSellerId());
        }else{
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
        }
        return map;
    }
    @PutMapping("/deliver")
    public HashMap deliver(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap(16);
        String token = request.getParameter("sellerToken");
        String orderId = request.getParameter("orderId");
        if(orderId == null||"".equals(orderId)){
            map.put("status","1");
            map.put("msg","请选择有效订单");
            return map;
        }
        if(token == null||"".equals(token)){
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
            return map;
        }

        boolean tokenValidate = sellerToken.tokenValidate(token);
        if(tokenValidate){
            Byte s = 2;
            map = orderService.updateOrderState(s,orderId);
        }else{
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
        }
        return map;
    }
    @PutMapping("/signFor")
    public HashMap signFor(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>(16);
        String userToken = request.getParameter("token");
        String orderId = request.getParameter("orderId");
        if(orderId == null||"".equals(orderId)){
            map.put("status","1");
            map.put("msg","请选择有效订单");
            return map;
        }
        if(userToken == null||"".equals(userToken)){
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
            return map;
        }
        boolean tokenValidate = token.tokenValidate(userToken);
        if(!tokenValidate){
            Byte s = 3;
            orderService.updateOrderState(s,orderId);
            return map;
        }
        HashMap userData = token.getUserByToken(userToken);
        User user = (User)userData.get("user");
        map = orderService.getOrderListByUserId(user.getUserId());
        return map;
    }

}
