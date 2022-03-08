package com.lijian.service;

import com.lijian.entity.Result;

import java.util.Map;

public interface OrderService {

    Result order(Map map) throws Exception;

    //根据id查询预约信息，包括人信息、套餐信息
    Map findById4Detail(Integer id ) throws Exception;

}
