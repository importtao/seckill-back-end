package me.importtao.seckillbackend.service;

import java.util.HashMap;

/**
 * Package me.importtao.seckillbackend.service
 * Class CartService
 * Description: 购物车Service
 *
 * @author importtao
 * date 2018/5/19 15:11
 * @version V1.0
 */
public interface CartService {

    /**
     * description 添加到购物车
     * @author importtao
     * @date 2018/5/19 15:14
     * @param goodsId 商品id
     * @param userId 用户Id
     * @param model 型号
     * @return   HashMap
     */
    public HashMap addToCartList(String goodsId,String userId,Byte model);

    /**
     * description 获取用户购物车列表
     * @author importtao
     * @date 2018/5/19 15:17
     * @param userId  用户ID
     * @return  HashMap
     */
    public HashMap getCartList(String userId);

    /**
     * description 从购物车删除
     * @author importtao
     * @date 2018/5/19 15:19
     * @param userId 用户ID
     * @param goodsId 商品Id
     * @return HashMap
     */
    public HashMap deleteCartList(String userId,String goodsId);


}
