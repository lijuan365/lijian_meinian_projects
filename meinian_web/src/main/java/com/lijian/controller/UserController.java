package com.lijian.controller;

import com.lijian.constant.MessageConstant;
import com.lijian.entity.Result;
import com.lijian.service.UserService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    /**
     *
     */
    @RequestMapping("/getUserName")
    public Result getUsername() {
        try {
            // 从SpringSecurity中获取认证用户的信息,这里的User要的是Spring框架提供的User类否则结汇报错
            User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return new Result(true, MessageConstant.GET_USERNAME_SUCCESS, user);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_USERNAME_FAIL);
        }
    }
}
