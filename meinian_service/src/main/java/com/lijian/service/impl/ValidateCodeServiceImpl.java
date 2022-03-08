package com.lijian.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lijian.dao.ValidateCodeDao;
import com.lijian.entity.Result;
import com.lijian.service.ValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import redis.clients.jedis.JedisPool;

@Service(interfaceClass = ValidateCodeService.class)
@Transactional
public class ValidateCodeServiceImpl implements ValidateCodeService {
    @Autowired
    private ValidateCodeDao validateCodeDao;
    @Autowired
    private JedisPool jedispool;



}
