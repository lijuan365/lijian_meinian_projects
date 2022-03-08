package com.lijian.dao;

import com.github.pagehelper.Page;
import com.lijian.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealDao {

    Setmeal findById(int id);

    void add(Setmeal setmeal);

    void addTravelGroupAndSetmeal(Map<String, Integer> map);

    Page findPage(String queryString);

    List<Setmeal> findAll();
}
