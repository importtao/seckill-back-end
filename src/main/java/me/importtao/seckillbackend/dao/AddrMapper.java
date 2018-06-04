package me.importtao.seckillbackend.dao;

import me.importtao.seckillbackend.model.Addr;
import me.importtao.seckillbackend.viewModel.SimpleAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddrMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Addr record);

    int insertSelective(Addr record);

    Addr selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Addr record);

    int updateByPrimaryKey(Addr record);

    List<Addr> selectByUserId(@Param("userId") String userId);

    List<SimpleAddress> selectSimpleAddress(@Param("userId") String userId);
}