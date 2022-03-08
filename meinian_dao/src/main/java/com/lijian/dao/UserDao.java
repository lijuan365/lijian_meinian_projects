package com.lijian.dao;

import com.lijian.pojo.User;

public interface UserDao {
    User findUserByUsername(String username);
}
