package me.importtao.seckillbackend.controller;

import me.importtao.seckillbackend.model.User;
import me.importtao.seckillbackend.service.CartService;
import me.importtao.seckillbackend.util.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Package me.importtao.seckillbackend.controller
 * Class CartController
 * Description: 购物车controller
 *
 * @author importtao
 * date 2018/5/19 15:06
 * @version V1.0
 */
@RestController
@CrossOrigin
public class CartController {
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);
    @Resource
    private Token token;
    @Resource
    private CartService cartService;

    @PostMapping("/cart")
    public HashMap addToCart(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>(16);
        String userToken = request.getParameter("token");
        String goodsId = request.getParameter("goodsId");
        String modelCode = request.getParameter("modelCode");
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
        HashMap userDate = token.getUserByToken(userToken);
        User user = (User)userDate.get("user");
        map = cartService.addToCartList(goodsId,user.getUserId(),Byte.valueOf(modelCode));
        return map;
    }
    @GetMapping("/wishList")
    public HashMap getCart(HttpServletRequest request){
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
        HashMap userDate = token.getUserByToken(userToken);
        User user = (User)userDate.get("user");
        map = cartService.getCartList(user.getUserId());
        return map;
    }
    @DeleteMapping("/cart")
    public HashMap deleteCart(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>(16);
        String userToken = request.getParameter("token");
        String goodsId = request.getParameter("goodsId");
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
        HashMap userDate = token.getUserByToken(userToken);
        User user = (User)userDate.get("user");
        map = cartService.deleteCartList(user.getUserId(),goodsId);
        return map;
    }
}
