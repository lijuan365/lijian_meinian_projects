package com.lijian.dao;

import com.github.pagehelper.Page;
import com.lijian.pojo.TravelItem;

import java.util.List;

public interface TravelItemDao {
    void add(TravelItem travelItem);

    Page findPage(String queryString);

    void delete(Integer id);

    TravelItem findById(Integer id);

    void edit(TravelItem travelItem);

    List<TravelItem> findAll();

    List<TravelItem> findTravelItemListById(Integer id);
}
