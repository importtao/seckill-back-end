package me.importtao.seckillbackend.dao;

import me.importtao.seckillbackend.model.SellerInfo;

public interface SellerInfoMapper {
    int deleteByPrimaryKey(String sellerId);

    int insert(SellerInfo record);

    int insertSelective(SellerInfo record);

    SellerInfo selectByPrimaryKey(String sellerId);

    int updateByPrimaryKeySelective(SellerInfo record);

    int updateByPrimaryKey(SellerInfo record);
}