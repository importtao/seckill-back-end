package me.importtao.seckillbackend.dao;

import me.importtao.seckillbackend.model.GoodsInfo;

public interface GoodsInfoMapper {
    int deleteByPrimaryKey(String goodsId);

    int insert(GoodsInfo record);

    int insertSelective(GoodsInfo record);

    GoodsInfo selectByPrimaryKey(String goodsId);

    int updateByPrimaryKeySelective(GoodsInfo record);

    int updateByPrimaryKey(GoodsInfo record);
}