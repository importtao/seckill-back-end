package me.importtao.seckillbackend.service;

import me.importtao.seckillbackend.dao.*;
import me.importtao.seckillbackend.model.GoodsInfo;
import me.importtao.seckillbackend.model.OrderForm;
import me.importtao.seckillbackend.model.OrderInfo;
import me.importtao.seckillbackend.util.GeneratorId;
import me.importtao.seckillbackend.util.ZxingQRCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;

/**
 * Package me.importtao.seckillbackend.service
 * Class OrderserviceImpl
 * Description: TODO
 *
 * @author importtao
 * date 2018/5/17 15:43
 * @version V1.0
 */
@Service
public class OrderserviceImpl implements OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderserviceImpl.class);
    @Resource
    private SeckillTimeMapper seckillTimeMapper;
    @Resource
    private GoodsModelMapper goodsModelMapper;
    @Resource
    private OrderFormMapper orderFormMapper;
    @Resource
    private OrderInfoMapper orderInfoMapper;
    @Resource
    private GeneratorId generatorId;
    @Resource
    private OrderForm orderForm;
    @Resource
    private OrderInfo orderInfo;
    @Resource
    private GoodsInfo goodsInfo;
    @Resource
    private GoodsInfoMapper goodsInfoMapper;
    @Resource
    private ZxingQRCode zxingQRCode;
    @Value("${payUrl}")
    private String payUrl;
    /**
     * description 秒杀创建订单
     *
     * @param userId    用户id
     * @param goodsId   商品id
     * @param modelCode 型号
     * @param addrId  收货地址
     * @return HashMap
     * @author importtao
     * @date 2018/5/21 10:01
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public HashMap createOrder(String userId, String goodsId, Byte modelCode,Long addrId) {
        HashMap<String,Object> map = new HashMap<>(16);
        Date now = new Date();
        HashMap param = new HashMap(16);
        param.put("goodsId",goodsId);
        param.put("dateTime",now);
        int i = seckillTimeMapper.selectIsSeckillByDate(param);
        if(i > 0){
            int inventry = goodsModelMapper.selectInventry(goodsId,modelCode);
            if(inventry>0){
                inventry -=1;
                int result = goodsModelMapper.updateInventry(goodsId,modelCode,inventry);
                if(result == 1){
                    logger.info("秒杀成功！");
                    String orderId;
                    try {
                        orderId = generatorId.getOrderId();
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error("生成订单号失败！");
                        map.put("status","1");
                        map.put("msg","生成订单号失败！");
                        return map;
                    }
                    orderForm.setOrderId(orderId);
                    orderForm.setUserId(userId);
                    orderForm.setGoodsId(goodsId);
                    orderForm.setModel(modelCode);
                    orderForm.setCreateTime(now);
                    orderForm.setNumber(1);
                    int r = orderFormMapper.insertSelective(orderForm);
                    if(r==1){
                        logger.info("订单创建成功");
                        goodsInfo = goodsInfoMapper.selectByPrimaryKey(goodsId);
                        String content = payUrl+"?orderId="+orderId;
                        try {
                            String payImg = zxingQRCode.get2CodeImage(content);
                            orderInfo.setPaycode(payImg);
                        } catch (Exception e) {
                            e.printStackTrace();
                            logger.error("生成支付二维码失败！");
                        }
                        orderInfo.setOrderId(orderId);
                        orderInfo.setAddr(addrId);
                        orderInfo.setState((byte)0);
                        orderInfo.setMoney(goodsInfo.getPrice());
                        result = orderInfoMapper.insertSelective(orderInfo);
                        if(result == 1){
                            map.put("status","0");
                            map.put("orderId",orderId);
                            map.put("msg","秒杀成功！");
                            return map;
                        }else{
                            map.put("status","1");
                            map.put("msg","订单创建失败！");
                            return map;
                        }
                    }else {
                        map.put("status","1");
                        map.put("msg","订单创建失败！");
                        return map;
                    }
                }else {
                    map.put("status","1");
                    map.put("msg","秒杀失败！");
                    return map;
                }
            }else{
                map.put("status","1");
                map.put("msg","已售罄，请选择其他型号或其他商品！");
                return map;
            }
        }else{
            map.put("status","1");
            map.put("msg","当前时间无效，请留意秒杀时间！");
            return map;
        }
    }

    /**
     * description 获取用户订单
     *
     * @param userId 用户Id
     * @return HashMap
     * @author importtao
     * @date 2018/5/21 19:52
     */
    @Override
    public HashMap getOrderListByUserId(String userId) {
        HashMap<String,Object> map = new HashMap<>(16);
        map.put("orderList",orderFormMapper.selectOrderList(userId));
        map.put("status","0");
        return map;
    }
    /**
     * description 获取订单详情
     *
     * @param orderId 订单号
     * @return HashMap
     * @author importtao
     * @date 2018/5/21 20:19
     */
    @Override
    public HashMap getOrderDetail(String orderId) {
        HashMap<String,Object> map = new HashMap<>(16);
        map.put("orderDetail",orderFormMapper.selectOrderDetail(orderId));
        map.put("status","0");
        return map;
    }

    /**
     * description 获取商户所属户订单
     *
     * @param sellerId 用户Id
     * @return HashMap
     * @author importtao
     * @date 2018/5/21 19:52
     */
    @Override
    public HashMap getOrderListBySellerId(String sellerId) {
        HashMap<String,Object> map = new HashMap<>(16);
        map.put("orderList",orderFormMapper.selectOrderListBySellerId(sellerId));
        map.put("status","0");
        return map;
    }

    /**
     * description 获取待处理订单
     *
     * @param sellerId 用户Id
     * @return HashMap
     * @author importtao
     * @date 2018/5/21 19:52
     */
    @Override
    public HashMap getPendingOrderListBySellerId(String sellerId) {
        HashMap<String,Object> map = new HashMap<>(16);
        map.put("orderList",orderFormMapper.selectPendingOrderList(sellerId));
        map.put("status","0");
        return map;
    }

    /**
     * description
     *
     * @param sellerId 用户Id
     * @return
     * @author importtao
     * @date 2018/5/21 20:38
     */
    @Override
    public HashMap getHistoricalOrderListBySellerId(String sellerId) {
        HashMap<String,Object> map = new HashMap<>(16);
        map.put("orderList",orderFormMapper.selectHistoricalOrderList(sellerId));
        map.put("status","0");
        return map;
    }

    /**
     * description 修改订单状态
     *
     * @param state   状态
     * @param orderId 订单号
     * @return
     * @author importtao
     * @date 2018/5/21 20:40
     */
    @Override
    public HashMap updateOrderState(Byte state, String orderId) {
        HashMap<String,Object> map = new HashMap<>(16);
        int result = orderInfoMapper.updateState(orderId,state);
        if(result >0){
            map.put("status","0");
            map.put("msg","操作成功");
        }else{
            map.put("status","1");
            map.put("msg","操作失败");
        }
        return map;
    }
}
