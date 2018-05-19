package me.importtao.seckillbackend.controller;

import me.importtao.seckillbackend.service.SeckillService;
import me.importtao.seckillbackend.util.SellerToken;
import me.importtao.seckillbackend.util.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

/**
 * Package me.importtao.seckillbackend.controller
 * Class SeckillController
 * Description: TODO
 *
 * @author importtao
 * date 2018/5/14 16:10
 * @version V1.0
 */
@RestController
@CrossOrigin
public class SeckillController {
    private static final Logger logger = LoggerFactory.getLogger(SeckillController.class);
    @Resource
    private Token token;
    @Resource
    private SellerToken sellerToken;
    @Resource
    private SeckillService seckillService;

    @PostMapping("/seckill")
    public HashMap addSeckill(HttpServletRequest request){
        HashMap map = new HashMap(16);
        String sellerTokenString = request.getParameter("sellerToken");
        String goodsId = request.getParameter("goodsId");
        String beginTime = request.getParameter("beginTime");
        String endTime = request.getParameter("endTime");
        if(sellerTokenString == null||"".equals(sellerTokenString)){
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
            return map;
        }
        if(goodsId == null||"".equals(goodsId)){
            map.put("status","1");
            map.put("msg","请选择商品");
            return map;
        }
        boolean exit = seckillService.checkSeckilll(goodsId);
        if(!exit){
            map.put("status","1");
            map.put("msg","该商品已存在秒杀");
            return map;
        }
        if(beginTime == null||"".equals(beginTime)){
            map.put("status","1");
            map.put("msg","请设置秒杀开始时间！");
            return map;
        }
        if(endTime == null||"".equals(endTime)){
            map.put("status","1");
            map.put("msg","请设置秒杀结束时间！");
            return map;
        }
        boolean tokenValidate = sellerToken.tokenValidate(sellerTokenString);
        if(tokenValidate){
            Date beginDate = new Date(beginTime);
            Date endDate = new Date(endTime);
            map = seckillService.addSeckill(goodsId,beginDate,endDate);
        }else{
            map.put("status","2");
            map.put("msg","未登录或登录超时请重新登录！");
        }
        return map;
    }
    @GetMapping("/seckillTime")
    public HashMap getSeckillTime(HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>(16);
        String goodsId = request.getParameter("goodsId");
        if(goodsId == null||"".equals(goodsId)){
            map.put("status","1");
            map.put("msg","请选择商品");
            return map;
        }
        boolean exit = seckillService.checkSeckilll(goodsId);
        if(exit){
            map.put("status","1");
            map.put("msg","该商品未开始秒杀活动，敬请期待！");
            return map;
        }else{
            Date now = new Date();
            map = seckillService.getSeckillTime(goodsId);
            map.put("nowTime",now);
            return map;
        }
    }
}
