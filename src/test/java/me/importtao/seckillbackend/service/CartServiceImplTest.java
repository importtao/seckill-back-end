package me.importtao.seckillbackend.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Package me.importtao.seckillbackend.service
 * Class CartServiceImplTest
 * Description: TODO
 *
 * @author importtao
 * date 2018/5/19 16:36
 * @version V1.0
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CartServiceImplTest {
    @Resource
    private CartService cartService;

    @Test
    public void getCartList() throws Exception{
        System.out.println(cartService.getCartList("201805030000000003"));
    }
    @Test
    public void addCartList() throws Exception{
        System.out.println(cartService.addToCartList("201805162000000001","201805030000000003",(byte)1));
    }
    @Test
    public void deleteCartList() throws Exception{
        System.out.println(cartService.deleteCartList("201805030000000003","201805162000000001"));
    }

}