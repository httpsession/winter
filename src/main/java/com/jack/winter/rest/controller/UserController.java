package com.jack.winter.rest.controller;

import com.jack.winter.rest.domain.User;
import com.jack.winter.rest.response.ResponseObject;
import com.jack.winter.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/winter/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/update")
    public ResponseObject updateUser(User user , boolean test){
        userService.updateUserById(user,test);
        return new ResponseObject().setSuccess(true).setStatus(200).setMsg("Successfully updated");
    }

    @RequestMapping("/insert")
    public ResponseObject insertUser(User user , boolean test){
        userService.insertUser(user,test);
        return new ResponseObject().setSuccess(true).setStatus(200).setMsg("Successfully insert");
    }
}
