package com.lijian.service;

import com.lijian.entity.PageResult;
import com.lijian.pojo.Setmeal;

import java.util.List;

public interface SetmealService {
    //添加套餐游
    void add(Integer[] travelGroupIds, Setmeal setmeal);
    //数据分页
    PageResult findPage(String queryString, Integer pageSize, Integer currentPage);
    //移动端页面中找到所有的旅游套餐
    List<Setmeal> findAll();
    //根据id查找指定的套餐游
    Setmeal findById(int id);
}
