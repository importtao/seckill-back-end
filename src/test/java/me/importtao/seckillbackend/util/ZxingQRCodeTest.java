package me.importtao.seckillbackend.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Package me.importtao.seckillbackend.util
 * Class ZxingQRCodeTest
 * Description: TODO
 *
 * @author importtao
 * date 2018/5/17 16:24
 * @version V1.0
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ZxingQRCodeTest {
    @Resource
    private ZxingQRCode zxingQRCode;
    @Test
    public void generatorQRcode() throws Exception {
        /*System.out.println(zxingQRCode.createZxing("http://www.baidu.com"));*/
        try {
            zxingQRCode.get2CodeImage("http://www.baidu.com/asdasdasdasdasd");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}