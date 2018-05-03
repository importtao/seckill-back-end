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

        System.out.println("MD5:"+Encode.encrypt("����","MD5"));
        System.out.println("BASE64:"+Encode.encrypt("asdgfdgdhfgsdfsdf1232131654981164981165496","BASE64"));
        System.out.println("BASE64����:"+new String(Encode.decipheringByBase64("YXNkZ2ZkZ2RoZmdzZGZzZGYxMjMyMTMxNjU0OTgxMTY0OTgxMTY1NDk2")));
        System.out.println("SHA:"+Encode.encrypt("����","SHA"));
        Assert.assertEquals(99162322,"hello".hashCode());
        Assert.assertEquals("-145316996085989278241632226927698442967",Encode.encrypt("����","MD5"));
        Assert.assertEquals("1cXMzg==",Encode.encrypt("����","BASE64"));
        Assert.assertEquals("����",new String(Encode.decipheringByBase64("1cXMzg==")));
        Assert.assertEquals("-dsq1mrbu5auaf1eo07vmd5tvhn7oj92q",Encode.encrypt("����","SHA"));
        System.out.println("hello".hashCode());
    }

}