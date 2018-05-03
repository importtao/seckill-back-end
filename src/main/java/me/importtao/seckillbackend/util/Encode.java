package me.importtao.seckillbackend.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Package me.importtao.seckillbackend.util
 * Class EncodeMD5
 * Description: ¼ÓÃÜËã·¨¹¤¾ßÀà
 *
 * @author importtao
 * date 2018/2/14 14:04
 * @version V1.0
 */
public class Encode {

    /**
     * description ×Ö·û´®¼ÓÃÜ
     * @author importtao
     * @date 2018/2/14 14:14
     * @param param ´ý¼ÓÃÜ×Ö·û´®
     * @param arithmetic ¼ÓÃÜËã·¨ MD5 SHA base HMAC
     * @return   ¼ÓÃÜ×Ö·û´®
     */
    public static String encrypt(String param,String arithmetic) throws NoSuchAlgorithmException{
        String result = null;
        switch(arithmetic){
            case  "MD5":
                try {
                    result = Encode.encodeByMD5(param);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                    throw e;
                }
                break;
            case "BASE64":
                result = Encode.encodeByBase64(param);
                break;
            case "SHA":
                result = Encode.encodeBySHA(param);
                break;
            default:
                try {
                    result = Encode.encodeByMD5(param);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                break;
        }
        return result;
    }

    /**
     * description
     * @author importtao
     * @date 2018/2/14 14:30
     * @param parameter ´ý¼ÓÃÜ×Ö·û´®
     * @return   MD5¼ÓÃÜºóµÄ×Ö·û´®
     */
    private static String encodeByMD5(String parameter) throws NoSuchAlgorithmException{
        MessageDigest messageDigest;
        byte[] parameterByte = parameter.getBytes();
        BigInteger bigInteger;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(parameterByte);
            bigInteger = new BigInteger(messageDigest.digest());
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            noSuchAlgorithmException.printStackTrace();
            throw noSuchAlgorithmException;
        }
        return bigInteger.toString();
    }

    /**
     * description
     * @author importtao
     * @date 2018/3/10 15:11
     * @param parameter ´ý¼ÓÃÜ×Ö·û´®
     * @return   SHA¼ÓÃÜºóµÄ×Ö·û´®
     */
    private static String encodeBySHA(String parameter) throws NoSuchAlgorithmException{
        byte[] inputData = parameter.getBytes();
        MessageDigest messageDigest = MessageDigest.getInstance("SHA");
        messageDigest.update(inputData);
        BigInteger sha = new BigInteger(messageDigest.digest());
        return sha.toString(32);
    }

    /**
     * description
     * @author importtao
     * @date 2018/3/10 15:27
     * @param parameter ´ý¼ÓÃÜ×Ö·û´®
     * @return   base64¼ÓÃÜºóµÄ×Ö·û´®
     */
    private static String encodeByBase64(String parameter){
        byte[] data = parameter.getBytes();
        return (new BASE64Encoder()).encodeBuffer(data).replaceAll("[\\s*\t\n\r]", "");
    }
    /**
     * description
     * @author importtao
     * @date 2018/3/10 15:45
     * @param key ´ý½âÃÜ×Ö·û´®
     * @return   base64½âÃÜºóµÄ×Ö·û´®
     */
    public static byte[] decipheringByBase64(String key) throws IOException{
        return (new BASE64Decoder()).decodeBuffer(key);
    }
}
