package me.importtao.seckillbackend.service;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Package me.importtao.seckillbackend.service
 * Class SeckillServiceImplTest
 * Description: TODO
 *
 * @author importtao
 * date 2018/5/16 20:19
 * @version V1.0
 */

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SeckillServiceImplTest {
    @Resource
    private SeckillService seckillService;
    @Test
    public void addSeckill() throws Exception {
    }

    @Test
    public void getSeckillTime() throws Exception {
        System.out.println(JSON.toJSON(seckillService.getSeckillTime("201805102000000001")));
    }

    @Test
    public void checkSeckilll() throws Exception {
    }

}