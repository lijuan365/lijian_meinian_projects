package com.lijian.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lijian.dao.UserDao;
import com.lijian.pojo.User;
import com.lijian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {

    /**
     * 根据用户名查询用户的信息
     * @param username  用户名
     * @return  用户的信息
     */
    @Autowired
    private UserDao userDao;
    @Override
    public User findUserByUsername(String username) {

        return  userDao.findUserByUsername(username);
    }
}
