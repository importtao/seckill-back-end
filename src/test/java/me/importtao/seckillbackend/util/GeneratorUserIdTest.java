package me.importtao.seckillbackend.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Package me.importtao.usersystem.util
 * Class GeneratorUserIdTest
 * Description: TODO
 *
 * @author importtao
 * date 2018/1/19 14:27
 * @version V1.0
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class GeneratorUserIdTest {
    @Resource
    GeneratorUserId generatorUserId;
    @Test
    public void getUserId() throws Exception {
        System.out.println(generatorUserId.getUserId());

    }

    @Test
    public void getSellerId() throws Exception {
        System.out.println(generatorUserId.getSellerId());
    }

}