package com.lijian.service;

import com.lijian.entity.PageResult;
import com.lijian.pojo.TravelItem;

import java.util.List;

public interface TravelItemService {
    //资源自由行模块
    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    //鼠标放到接口名，Ctel+Alt+b   跳转到实现类
    void add(TravelItem travelItem);

    void delete(Integer id);

    TravelItem findById(Integer id);

    void edit(TravelItem travelItem);

    //跟团游模块
    List<TravelItem> findAll();
}
