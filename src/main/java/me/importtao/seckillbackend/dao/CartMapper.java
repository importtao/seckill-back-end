package me.importtao.seckillbackend.dao;

import me.importtao.seckillbackend.model.Cart;
import me.importtao.seckillbackend.viewModel.WishListModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    List<WishListModel> selectByUserId(@Param("userId")String userId);

    int deleteByUserGoodsId(@Param("userId") String userId,@Param("goodsId") String goodsId);
}