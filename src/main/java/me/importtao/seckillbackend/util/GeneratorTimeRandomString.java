package me.importtao.seckillbackend.util;

import java.util.Random;

/**
 * Package me.importtao.seckillbackend.util
 * Class GeneratorTimeRandomString
 * Description: 生成时间戳加随机串字符串
 *
 * @author importtao
 * date 2018/5/5 16:15
 * @version V1.0
 */
public class GeneratorTimeRandomString {
    public static String getTimeRandomString(){
        String time = String.valueOf(System.currentTimeMillis());
        String sources = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        char [] arry = sources.toCharArray();
        Random rand = new Random();
        StringBuffer result = new StringBuffer();
        result.append(time);
        for (int j = 0; j < 12; j++)
        {
            result.append(arry[rand.nextInt(arry.length-1)]);
        }
        return result.toString();
    }
}
