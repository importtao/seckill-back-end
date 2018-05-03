package me.importtao.seckillbackend.dao;

import me.importtao.seckillbackend.model.Addr;

public interface AddrMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Addr record);

    int insertSelective(Addr record);

    Addr selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Addr record);

    int updateByPrimaryKey(Addr record);
}