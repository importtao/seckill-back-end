package me.importtao.seckillbackend.controller;

import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/order")
    public HashMap createOrder(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>(16);
        return map;
    }
    @PostMapping("/pay")
    public HashMap pay(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>(16);
        return map;
    }
    @GetMapping("/userOrder")
    public HashMap getUserOrderList(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>(16);
        return map;
    }
    @GetMapping("/userOrderDetail")
    public HashMap getUserOrderDetail(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>(16);
        return map;
    }
    @GetMapping("/sellerOrderDetail")
    public HashMap getSellerOrderDetail(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>(16);
        return map;
    }
    @GetMapping("/sellerPendingOrder")
    public HashMap getSellerPendingOrder(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>(16);
        return map;
    }
    @GetMapping("/sellerHistoricalOrder")
    public HashMap getSellerHistoricalOrder(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>(16);
        return map;
    }
    @PutMapping("/deliver")
    public HashMap deliver(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>(16);
        return map;
    }

}
