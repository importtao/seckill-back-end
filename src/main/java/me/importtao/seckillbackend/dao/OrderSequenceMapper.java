package me.importtao.seckillbackend.dao;

import java.util.Date;
import me.importtao.seckillbackend.model.OrderSequence;

public interface OrderSequenceMapper {
    int deleteByPrimaryKey(Date todayDate);

    int insert(OrderSequence record);

    int insertSelective(OrderSequence record);

    OrderSequence selectByPrimaryKey(Date todayDate);

    int updateByPrimaryKeySelective(OrderSequence record);

    int updateByPrimaryKey(OrderSequence record);
}