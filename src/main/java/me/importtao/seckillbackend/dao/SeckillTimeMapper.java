package me.importtao.seckillbackend.dao;

import me.importtao.seckillbackend.model.SeckillTime;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeckillTimeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SeckillTime record);

    int insertSelective(SeckillTime record);

    SeckillTime selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SeckillTime record);

    int updateByPrimaryKey(SeckillTime record);

    SeckillTime selectByGoodsId(@Param("goodsId") String goodsId);

    Integer selectExitByGoodsId(@Param("goodsId") String goodsId);
}