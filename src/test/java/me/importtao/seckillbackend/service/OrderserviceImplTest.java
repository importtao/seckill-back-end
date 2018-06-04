package me.importtao.seckillbackend.service;

import me.importtao.seckillbackend.controller.OrderController;
import me.importtao.seckillbackend.dao.OrderFormMapper;
import me.importtao.seckillbackend.dao.SeckillTimeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Package me.importtao.seckillbackend.service
 * Class OrderserviceImplTest
 * Description: TODO
 *
 * @author importtao
 * date 2018/5/23 12:32
 * @version V1.0
 */

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderserviceImplTest {
    @Resource
    private OrderFormMapper orderFormMapper;
    @Resource
    private OrderService orderService;
    @Resource
    private OrderController orderController;
    @Resource
    private SeckillTimeMapper seckillTimeMapper;
    @Test
    public void createOrder() throws Exception {
        Byte b = 3;

        orderService.createOrder("201805030000000003","201805092000000001",b,52L);
        seckillTimeMapper.selectByGoodsId("201805092000000001");
        HashMap map = new HashMap(16);
        map.put("goodsId","201805092000000001");
        map.put("dateTime",new Date());
        seckillTimeMapper.selectTest(map);
        seckillTimeMapper.selectIsSeckillByDate(map);
        /*MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("token","MTUyNzg0OTA0ODUyMzIwMTgwNTAzMDAwMDAwMDAwMw==");
        request.setParameter("goodsId","201805092000000001");
        request.setParameter("modelCode","3");
        request.setParameter("addr","52");
        System.out.println(orderController.createOrder(request));*/
    }

    @Test
    public void getOrderListByUserId() throws Exception {
        System.out.println(orderFormMapper.selectOrderList("201805030000000003"));
    }

    @Test
    public void getOrderDetail() throws Exception {
        System.out.println(orderFormMapper.selectOrderDetail("123"));
    }

    @Test
    public void getOrderListBySellerId() throws Exception {
    }

    @Test
    public void getPendingOrderListBySellerId() throws Exception {
    }

    @Test
    public void getHistoricalOrderListBySellerId() throws Exception {
    }

    @Test
    public void updateOrderState() throws Exception {
    }

}