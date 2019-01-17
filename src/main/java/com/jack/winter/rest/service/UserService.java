package com.jack.winter.rest.service;

import com.alibaba.fastjson.JSONObject;
import com.jack.winter.rest.dao.jack.JackUserDao;
import com.jack.winter.rest.dao.tom.TomUserDao;
import com.jack.winter.rest.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    JackUserDao jackUserDao;
    @Autowired
    TomUserDao tomUserDao;

    @Transactional
    public void updateUserById(User user, boolean test){
        Objects.requireNonNull(user.getUserId());
        logger.info("user: {}",JSONObject.toJSONString(user));
        jackUserDao.updateUserById(user);
        tomUserDao.updateUserById(user);
        if(test){
            throw new RuntimeException("测试");
        }
    }

    @Transactional
    public void insertUser(User user, boolean test){
        Objects.requireNonNull(user);
        logger.info("user: {}",JSONObject.toJSONString(user));
        jackUserDao.insertUser(user);
        tomUserDao.insertUser(user);
        if(test){
            throw new RuntimeException("测试");
        }
    }
}
