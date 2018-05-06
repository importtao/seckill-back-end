package me.importtao.seckillbackend.service;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
import java.util.Random;

/**
 * Package me.importtao.usersystem.service
 * Class UserServiceTest
 * Description: TODO
 *
 * @author importtao
 * date 2018/2/13 16:39
 * @version V1.0
 */

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void register() throws Exception {
        for(int i=0;i<1;i++){
            char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
            Random r = new Random();
            int len = ch.length, index;
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < 12; j++) {
                index = r.nextInt(len);
                sb.append(ch[index]);
            }
            MockHttpServletRequest request = new MockHttpServletRequest();
            request.setParameter("phone","18342962398");
            //request.setParameter("password",sb.toString());
            request.setParameter("password","test");
            request.setParameter("organization","0001");
            Map map = userService.register(request);
            System.out.println(JSON.toJSONString(map));
        }
    }

    @Test
    public void changePassword() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("userId","201802140001018262");
        request.setParameter("password","test");
        request.setParameter("rePassword","test");
        Map map = userService.changePassword(request);
        System.out.println(JSON.toJSONString(map));
    }

    @Test
    public void passwordCheck() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("parameter","201802140001018262");
        request.setParameter("password","test1");
        Map map = userService.passwordCheck(request);
        System.out.println(JSON.toJSONString(map));
    }

    @Test
    public void phoneCheck(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("phone","18342962399");

        Map map = userService.phoneCheck(request);
        System.out.println(JSON.toJSONString(map));
    }

    @Test
    public void login(){
        /*MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCharacterEncoding("utf-8");
        *//*request.setParameter("parameter","");*//*
        request.setParameter("password","");
        System.out.println(request.getParameter("password")+"--------");
        System.out.println(request.getParameter("passwords")+"--------");
        Map map = userService.login(request);
        System.out.println(JSON.toJSONString(map));*/

    }

}