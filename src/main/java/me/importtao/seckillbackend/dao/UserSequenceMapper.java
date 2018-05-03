package me.importtao.seckillbackend.dao;

import java.util.Date;
import me.importtao.seckillbackend.model.UserSequence;
import org.apache.ibatis.annotations.Param;

public interface UserSequenceMapper {
    int deleteByPrimaryKey(Date todayDate);

    int insert(UserSequence record);

    int insertSelective(UserSequence record);

    UserSequence selectByPrimaryKey(@Param("id")String id);

    int updateByPrimaryKeySelective(UserSequence record);

    int updateByPrimaryKey(UserSequence record);
}