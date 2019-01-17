package com.jack.winter.rest.dao.jack;

import com.jack.winter.rest.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JackUserDao {
     User selectUserById(int userId);

     int updateUserById(User user);

     int insertUser(User user);
}
