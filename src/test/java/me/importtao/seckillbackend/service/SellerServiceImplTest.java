package me.importtao.seckillbackend.service;

import com.alibaba.fastjson.JSON;
import me.importtao.seckillbackend.util.SellerToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import static org.junit.Assert.*;

/**
 * Package me.importtao.seckillbackend.service
 * Class SellerServiceImplTest
 * Description: TODO
 *
 * @author importtao
 * date 2018/5/7 15:42
 * @version V1.0
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SellerServiceImplTest {
    @Resource
    private SellerService sellerService;
    @Resource
    private SellerToken sellerToken;
    @Test
    public void register() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("phone","18342962398");
        request.setParameter("password","18342962398");
        request.setParameter("corporation","18342962398");
        request.setParameter("license","18342962398");
        request.setParameter("name","test");
        request.setParameter("detail","test");
        request.setParameter("logoImg","img.png");
        request.setParameter("idImg","img.png");
        System.out.println(JSON.toJSON(sellerService.register(request)));
    }

    @Test
    public void login() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("parameter","18342962398");
        request.setParameter("password","18342962398");
        System.out.println(JSON.toJSON(sellerService.login(request)));
    }

    @Test
    public void userNameCheck() throws Exception {
    }

    @Test
    public void changePassword() throws Exception {
    }

    @Test
    public void passwordCheck() throws Exception {
        System.out.println(JSON.toJSONString(sellerToken.tokenValidate("MTUyNTY5MjQyMTM2ODIwMTgwNTA3MTAwMDAwMDAwMXM=")));
    }

    @Test
    public void phoneCheck() throws Exception {
    }

}