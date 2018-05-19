package me.importtao.seckillbackend.dao;


import me.importtao.seckillbackend.model.OrderForm;

public interface OrderFormMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(OrderForm record);

    int insertSelective(OrderForm record);

    OrderForm selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(OrderForm record);

    int updateByPrimaryKey(OrderForm record);
}