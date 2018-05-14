package me.importtao.seckillbackend.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Package me.importtao.seckillbackend.util
 * Class TokenTest
 * Description: TODO
 *
 * @author importtao
 * date 2018/5/7 19:29
 * @version V1.0
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TokenTest {
    @Resource
    private Token token;
    @Test
    public void generatorToken() throws Exception {
    }

    @Test
    public void tokenValidate() throws Exception {
        System.out.println(token.tokenValidate("MTUyNjIyMDM1OTIyNDIwMTgwNTAzMDAwMDAwMDAwMw=="));
    }

    @Test
    public void tokenTimeOut() throws Exception {
    }

    @Test
    public void getUserByToken() throws Exception {
    }

}