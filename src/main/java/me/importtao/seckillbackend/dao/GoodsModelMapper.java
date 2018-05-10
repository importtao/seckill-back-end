package me.importtao.seckillbackend.dao;

import me.importtao.seckillbackend.model.GoodsModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GoodsModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsModel record);

    int insertSelective(GoodsModel record);

    GoodsModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsModel record);

    int updateByPrimaryKey(GoodsModel record);

    List<GoodsModel> selectByGoodsId(@Param("goodsId") String goodsId);

    int deleteByGoodsId(@Param("goodsId") String goodsId);

    Byte selectMaxModelCode(@Param("goodsId") String goodsId);

}