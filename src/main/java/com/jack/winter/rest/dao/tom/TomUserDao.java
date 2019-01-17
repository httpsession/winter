package com.jack.winter.rest.dao.tom;

import com.jack.winter.rest.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TomUserDao {
     User selectUserById(int userId);

     int updateUserById(User user);

     int insertUser(User user);
}
