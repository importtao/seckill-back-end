package me.importtao.seckillbackend.dao;

import me.importtao.seckillbackend.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int passwordCheck(@Param("parameter") String parameter, @Param("password")String password);

    int phoneCheck(@Param("parameter") Long parameter);

    int userNameCheck(@Param("parameter") String parameter);

    User selectByParameter(@Param("parameter") String parameter);
}