package com.lijian.dao;

import com.lijian.pojo.Order;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    /**
     * 根据条件查询订单
     * @param order 订单
     * @return
     */
     List<Order> findByCondition(Order order);

    /**
     * 添加订单
     * @param order  订单的信息
     */
    void add(Order order);

    /**
     * 根据id查询订单的详细信息
     * @param id
     * @return
     */
    Map findById4Detail(Integer id);
}
