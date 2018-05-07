package me.importtao.seckillbackend.dao;

import me.importtao.seckillbackend.model.Seller;
import me.importtao.seckillbackend.model.User;
import org.apache.ibatis.annotations.Param;

public interface SellerMapper {
    int deleteByPrimaryKey(String sellerId);

    int insert(Seller record);

    int insertSelective(Seller record);

    Seller selectByPrimaryKey(String sellerId);

    int updateByPrimaryKeySelective(Seller record);

    int updateByPrimaryKey(Seller record);

    int passwordCheck(@Param("parameter") String parameter, @Param("password")String password);

    int phoneCheck(@Param("parameter") String parameter);

    int userNameCheck(@Param("parameter") String parameter);

    Seller selectByParameter(@Param("parameter") String parameter);
}