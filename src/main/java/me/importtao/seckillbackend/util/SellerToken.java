package me.importtao.seckillbackend.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * Package me.importtao.seckillbackend.util
 * Class SellerToken
 * Description: TODO
 *
 * @author importtao
 * date 2018/5/7 13:25
 * @version V1.0
 */
@Transactional(rollbackFor = Exception.class)
@Service
@Scope("singleton")
public class SellerToken {
    @Autowired
    private RedisTemplate redisTemplate;/**
     * description 通过seller userID生成token
     * @author importtao
     * @date 2018/3/12 16:00
     * @param sellerId  入参
     * @return   token
     */
    public synchronized String generatorSellerToken(String sellerId) throws NoSuchAlgorithmException {

        String token = System.currentTimeMillis()+sellerId+"s";
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
    public boolean tokenValidate(String token)throws IOException {
        return redisTemplate.hasKey(token);
    }

    /**
     * description 删除token
     * @author importtao
     * @date 2018/3/12 16:20
     * @param token token
     * @return   boolean token有效性
     */
    public void tokenTimeOut(String token)throws IOException{
        redisTemplate.delete(token);
    }

    /**
     * description 通过token获取用户数据
     * @author importtao
     * @date 2018/3/12 16:26
     * @param token token
     * @return   Object 用户数据
     */
    public HashMap getSellerByToken(String token)throws IOException{
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        return (HashMap) valueOperations.get(token);
    }

}
