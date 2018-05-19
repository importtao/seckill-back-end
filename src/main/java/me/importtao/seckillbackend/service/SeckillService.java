package me.importtao.seckillbackend.service;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

/**
 * Package me.importtao.seckillbackend.service
 * Class SeckillService
 * Description: 秒杀service
 *
 * @author importtao
 * date 2018/5/15 10:59
 * @version V1.0
 */
public interface SeckillService {
    /**
     * description 发布秒杀
     * @author importtao
     * @date 2018/5/15 11:01
     * @param goodsId  商品ID
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @return  HashMap
     */
    HashMap addSeckill(String goodsId, Date beginTime,Date endTime);

    /**
     * description 获取商品秒杀时间
     * @author importtao
     * @date 2018/5/15 11:37
     * @param goodsId 商品id
     * @return   hashMap
     */
    HashMap getSeckillTime(String goodsId);

    /**
     * description 判断商品是否已存在秒杀
     * @author importtao
     * @date 2018/5/16 11:20
     * @param goodsId 商品Id
     * @return boolean
     */
    boolean checkSeckilll(String goodsId);
}
