package me.importtao.seckillbackend.dao;

import me.importtao.seckillbackend.model.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
    int deleteByPrimaryKey(String goodsId);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(String goodsId);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> selectBySellerId(@Param("sellerId") String sellerId);

    List<Goods> selectByKeyWord(@Param("keyWord") String keyWord);
}