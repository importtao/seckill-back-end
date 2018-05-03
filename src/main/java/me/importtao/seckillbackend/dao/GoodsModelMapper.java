package me.importtao.seckillbackend.dao;

import me.importtao.seckillbackend.model.GoodsModel;

public interface GoodsModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsModel record);

    int insertSelective(GoodsModel record);

    GoodsModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsModel record);

    int updateByPrimaryKey(GoodsModel record);
}