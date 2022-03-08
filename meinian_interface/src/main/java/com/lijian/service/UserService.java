package com.lijian.service;

import com.lijian.pojo.User;

public interface UserService {
    /**
     * 根据用户名查询用户的信息
     * @param username  用户名
     * @return  用户的信息
     */
     User findUserByUsername(String username);
}
