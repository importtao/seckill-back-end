package me.importtao.seckillbackend.service;

import me.importtao.seckillbackend.model.Goods;
import me.importtao.seckillbackend.model.GoodsInfo;
import me.importtao.seckillbackend.model.GoodsModel;

import java.util.HashMap;

/**
 * Package me.importtao.seckillbackend.service
 * Class GoodsService
 * Description: TODO
 *
 * @author importtao
 * date 2018/5/7 21:33
 * @version V1.0
 */
public interface GoodsService {
    /**
     * description 添加商品
     * @author importtao
     * @date 2018/5/7 21:36
     * @param token token , goods,  goodsInfo
     * @param goods goods
     * @param goodsInfo goodsInfo
     * @return HashMap
     */
     HashMap addGoods(String token, Goods goods, GoodsInfo goodsInfo);

    /**
     * description 添加商品型号及库存
     * @author importtao
     * @date 2018/5/8 18:00
     * @param token goodsModel
     * @param goodsModel goodsModel
     * @return HashMap
     */
     HashMap addGoodsModel(String token,GoodsModel goodsModel);

    /**
     * description 通过token获取商户商品列表
     * @author importtao
     * @date 2018/5/8 18:29
     * @param token token
     * @return HashMap
     */
     HashMap getGoodsList(String token);

    /**
     * description 通过关键字获取商品
     * @author importtao
     * @date 2018/5/8 20:05
     * @param keyWord keyWord
     * @return HashMap
     */
     HashMap getGoodsByKeyword(String keyWord);

    /**
     * description 删除商品
     * @author importtao
     * @date 2018/5/8 20:14
     * @param token goodsId
     * @param goodsId goodsId
     * @return HashMap
     */
     HashMap deleteGoods(String token,String goodsId);

    /**
     * description 修改商品信息
     * @author importtao
     * @date 2018/5/9 16:38
     * @param token token , goods,  goodsInfo
     * @param goods goods
     * @param goodsInfo goodsInfo
     * @return HashMap
     */
     HashMap updateGoods(String token,Goods goods, GoodsInfo goodsInfo);

    /**
     * description 修改型号
     * @author importtao
     * @date 2018/5/9 16:42
     * @param token  token
     * @param goodsModel  goodsModel
     * @return   HashMap
     */
     HashMap updateModel(String token,GoodsModel goodsModel);

    /**
     * description 获取商品信息
     * @author importtao
     * @date 2018/5/9 22:45
     * @param goodsId goodsId
     * @return
     */
     HashMap getGoods(String goodsId);


}
