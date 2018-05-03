package me.importtao.seckillbackend.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Package me.importtao.usersystem.util
 * Class GeneratorToken
 * Description: tocken工具类
 *
 * @author importtao
 * date 2018/3/11 22:34
 * @version V1.0
 */
@Transactional(rollbackFor = Exception.class)
@Service
@Scope("singleton")
public class Token {
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * description 通过userID生成token
     * @author importtao
     * @date 2018/3/12 16:00
     * @param userId  入参
     * @return   token
     */
    public synchronized String generatorToken(String userId) throws NoSuchAlgorithmException{

        String token = System.currentTimeMillis()+userId;
        try {
            token = Encode.encrypt(token,"BASE64");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw e;
        }
        return token;
    }

    /**
     * description 验证token有效性
     * @author importtao
     * @date 2018/3/12 16:20
     * @param token token
     * @return   boolean token有效性
     */
    public boolean tokenValidate(String token)throws IOException{
        byte[] bytes;
        try {
            bytes = Encode.decipheringByBase64(token);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        return redisTemplate.hasKey(new String(bytes));
    }

    /**
     * description 通过token获取用户数据
     * @author importtao
     * @date 2018/3/12 16:26
     * @param token token
     * @return   Object 用户数据
     */
    public Object getUserByToken(String token)throws IOException{
        byte[] bytes;
        try {
            bytes = Encode.decipheringByBase64(token);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        String key = new String(bytes);
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public static void main(String[] args) {
        System.out.println("hello");
    }
}
