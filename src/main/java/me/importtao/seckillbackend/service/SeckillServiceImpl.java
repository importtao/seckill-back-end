package me.importtao.seckillbackend.service;

import me.importtao.seckillbackend.dao.SeckillTimeMapper;
import me.importtao.seckillbackend.model.SeckillTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;

/**
 * Package me.importtao.seckillbackend.service
 * Class SeckillServiceImpl
 * Description: TODO
 *
 * @author importtao
 * date 2018/5/15 11:03
 * @version V1.0
 */
@Service
public class SeckillServiceImpl implements SeckillService {
    @Resource
    private SeckillTimeMapper seckillTimeMapper;
    @Resource
    private SeckillTime seckillTime;
    /**
     * description 发布秒杀
     *
     * @param goodsId   商品ID
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return HashMap
     * @author importtao
     * @date 2018/5/15 11:01
     */
    @Override
    public HashMap addSeckill(String goodsId, Date beginTime, Date endTime) {
        HashMap<String,String> map = new HashMap<>(16);
        seckillTime.setGoodsId(goodsId);
        seckillTime.setBeginTime(beginTime);
        seckillTime.setEndTime(endTime);
        int i = seckillTimeMapper.insertSelective(seckillTime);
        if(i>0){
            map.put("status","0");
            map.put("msg","添加成功");
        }else{
            map.put("status","1");
            map.put("msg","添加失败");
        }
        return map;
    }
    /**
     * description 获取商品秒杀时间
     *
     * @param goodsId 商品id
     * @return hashMap
     * @author importtao
     * @date 2018/5/15 11:37
     */
    @Override
    public HashMap getSeckillTime(String goodsId) {
        seckillTime = seckillTimeMapper.selectByGoodsId(goodsId);
        HashMap<String,Object> map = new HashMap<>(16);
        map.put("status","0");
        map.put("seckillTime",seckillTime);
        return map;
    }

    /**
     * description 判断商品是否已存在秒杀
     *
     * @param goodsId 商品Id
     * @return boolean
     * @author importtao
     * @date 2018/5/16 11:20
     */
    @Override
    public boolean checkSeckilll(String goodsId) {
        int count = seckillTimeMapper.selectExitByGoodsId(goodsId);
        if(count > 0){
            return false;
        }else{
            return true;
        }
    }
}
