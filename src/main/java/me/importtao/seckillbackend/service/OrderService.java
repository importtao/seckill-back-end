package me.importtao.seckillbackend.service;

import java.util.HashMap;

/**
 * Package me.importtao.seckillbackend.service
 * Class OrderService
 * Description: 订单service
 *
 * @author importtao
 * date 2018/5/17 15:43
 * @version V1.0
 */
public interface OrderService {
    /**
     * description 秒杀创建订单
     * @author importtao
     * @date 2018/5/21 10:01
     * @param userId  用户id
     * @param goodsId 商品id
     * @param modelCode 型号
     * @param addrId  收货地址
     * @return   HashMap
     */
    HashMap createOrder(String userId,String goodsId,Byte modelCode,Long addrId);

    /**
     * description 获取用户订单
     * @author importtao
     * @date 2018/5/21 19:52
     * @param userId 用户Id
     * @return  HashMap
     */
    HashMap getOrderListByUserId(String userId);

    /**
     * description 获取订单详情
     * @author importtao
     * @date 2018/5/21 20:19
     * @param orderId 订单号
     * @return   HashMap
     */
    HashMap getOrderDetail(String orderId);

    /**
     * description 获取商户所属户订单
     * @author importtao
     * @date 2018/5/21 19:52
     * @param sellerId 用户Id
     * @return  HashMap
     */
    HashMap getOrderListBySellerId(String sellerId);

    /**
     * description 获取待处理订单
     * @author importtao
     * @date 2018/5/21 19:52
     * @param sellerId 用户Id
     * @return  HashMap
     */
    HashMap getPendingOrderListBySellerId(String sellerId);

    /**
     * description
     * @author importtao
     * @date 2018/5/21 20:38
     * @param sellerId  用户Id
     * @return
     */
    HashMap getHistoricalOrderListBySellerId(String sellerId);

    /**
     * description 修改订单状态
     * @author importtao
     * @date 2018/5/21 20:40
     * @param state 状态
     * @param orderId 订单号
     * @return
     */
    HashMap updateOrderState(Byte state,String orderId);
}
