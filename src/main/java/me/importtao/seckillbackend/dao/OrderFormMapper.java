package me.importtao.seckillbackend.dao;


import me.importtao.seckillbackend.model.OrderForm;
import me.importtao.seckillbackend.viewModel.OrderModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderFormMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(OrderForm record);

    int insertSelective(OrderForm record);

    OrderForm selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(OrderForm record);

    int updateByPrimaryKey(OrderForm record);

    List<OrderModel> selectOrderList(@Param("userId") String userId);

    List<OrderModel>selectOrderListBySellerId(@Param("sellerId") String sellerId);

    OrderModel selectOrderDetail(@Param("orderId") String orderId);

    List<OrderModel> selectPendingOrderList(@Param("sellerId") String sellerId);

    List<OrderModel> selectHistoricalOrderList(@Param("sellerId") String sellerId);

}