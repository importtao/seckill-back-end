package me.importtao.seckillbackend.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Package me.importtao.usersystem.util
 * Class EncodeTest
 * Description: TODO
 *
 * @author importtao
 * date 2018/2/14 14:35
 * @version V1.0
 */
public class EncodeTest {
    @Test
    public void encodeByMD5() throws Exception {
        System.out.println("MD5:"+Encode.encrypt("qwer","MD5"));
        System.out.println("MD5:"+Encode.encrypt("123","MD5"));
        System.out.println("MD5:"+Encode.encrypt("qwer123","MD5"));
        System.out.println("MD5:"+Encode.encrypt("中文测试","MD5"));
        System.out.println("MD5:"+Encode.encrypt("中文测试qwer123","MD5"));
        Assert.assertEquals("-140731632442770638484247700266385826553",Encode.encrypt("qwer","MD5"));
        Assert.assertEquals("42767516990368493138776584305024125808",Encode.encrypt("123","MD5"));
        Assert.assertEquals("113308975079317057579975726255731876629",Encode.encrypt("qwer123","MD5"));
        Assert.assertEquals("11440115975963652654390692151783669333",Encode.encrypt("中文测试","MD5"));
        Assert.assertEquals("-88412277724950702919680077528133712806",Encode.encrypt("中文测试qwer123","MD5"));
    }
    @Test
    public void encodeBySHA() throws Exception {
        System.out.println("SHA:"+Encode.encrypt("qwer","SHA"));
        System.out.println("SHA:"+Encode.encrypt("123","SHA"));
        System.out.println("SHA:"+Encode.encrypt("qwer123","SHA"));
        System.out.println("SHA:"+Encode.encrypt("中文测试","SHA"));
        System.out.println("SHA:"+Encode.encrypt("中文测试qwer123","SHA"));
        Assert.assertEquals("25gudvujcdtj0aisqt07ca1qff8vo86j",Encode.encrypt("qwer","SHA"));
        Assert.assertEquals("82ug05b311fs6kb56afa3vqsbr5tnfnf",Encode.encrypt("123","SHA"));
        Assert.assertEquals("klqr067k6ppj3e5n1i94cokqa4gqp6",Encode.encrypt("qwer123","SHA"));
        Assert.assertEquals("-c0bko0j6hbv2vk06rqp563ib2um38oiu",Encode.encrypt("中文测试","SHA"));
        Assert.assertEquals("-1m99vb3epsv0j3gaijadfd7c6esf3j1f",Encode.encrypt("中文测试qwer123","SHA"));
    }

    @Test
    public void encodeByBase64() throws Exception {
        System.out.println("BASE64:"+Encode.encrypt("qwer","BASE64"));
        System.out.println("BASE64:"+Encode.encrypt("123","BASE64"));
        System.out.println("BASE64:"+Encode.encrypt("qwer123","BASE64"));
        System.out.println("BASE64:"+Encode.encrypt("中文测试","BASE64"));
        System.out.println("BASE64:"+Encode.encrypt("中文测试qwer123","BASE64"));
        Assert.assertEquals("cXdlcg==",Encode.encrypt("qwer","BASE64"));
        Assert.assertEquals("MTIz",Encode.encrypt("123","BASE64"));
        Assert.assertEquals("cXdlcjEyMw==",Encode.encrypt("qwer123","BASE64"));
        Assert.assertEquals("5Lit5paH5rWL6K+V",Encode.encrypt("中文测试","BASE64"));
        Assert.assertEquals("5Lit5paH5rWL6K+VcXdlcjEyMw==",Encode.encrypt("中文测试qwer123","BASE64"));
    }

    @Test
    public void decodeByBase64() throws Exception {
        System.out.println("BASE64解密:"+new String(Encode.decipheringByBase64("cXdlcg==")));
        System.out.println("BASE64解密:"+new String(Encode.decipheringByBase64("MTIz")));
        System.out.println("BASE64解密:"+new String(Encode.decipheringByBase64("cXdlcjEyMw==")));
        System.out.println("BASE64解密:"+new String(Encode.decipheringByBase64("5Lit5paH5rWL6K+V")));
        System.out.println("BASE64解密:"+new String(Encode.decipheringByBase64("5Lit5paH5rWL6K+VcXdlcjEyMw==")));
        Assert.assertEquals("qwer",new String(Encode.decipheringByBase64("cXdlcg==")));
        Assert.assertEquals("123",new String(Encode.decipheringByBase64("MTIz")));
        Assert.assertEquals("qwer123",new String(Encode.decipheringByBase64("cXdlcjEyMw==")));
        Assert.assertEquals("中文测试",new String(Encode.decipheringByBase64("5Lit5paH5rWL6K+V")));
        Assert.assertEquals("中文测试qwer123",new String(Encode.decipheringByBase64("5Lit5paH5rWL6K+VcXdlcjEyMw==")));
    }

}