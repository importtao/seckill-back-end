package me.importtao.seckillbackend.dao;

import me.importtao.seckillbackend.model.SeckillRepertory;

public interface SeckillRepertoryMapper {
    int deleteByPrimaryKey(Long seckillId);

    int insert(SeckillRepertory record);

    int insertSelective(SeckillRepertory record);

    SeckillRepertory selectByPrimaryKey(Long seckillId);

    int updateByPrimaryKeySelective(SeckillRepertory record);

    int updateByPrimaryKey(SeckillRepertory record);
}