package me.importtao.seckillbackend.dao;

import me.importtao.seckillbackend.model.SeckillTime;

public interface SeckillTimeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SeckillTime record);

    int insertSelective(SeckillTime record);

    SeckillTime selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SeckillTime record);

    int updateByPrimaryKey(SeckillTime record);
}