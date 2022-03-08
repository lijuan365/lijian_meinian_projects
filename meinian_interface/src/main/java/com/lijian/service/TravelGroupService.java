package com.lijian.service;

import com.lijian.entity.PageResult;
import com.lijian.pojo.TravelGroup;

import java.util.List;

public interface TravelGroupService {
    void add(TravelGroup travelGroup,Integer [] travelItemIds);

    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    TravelGroup findById(Integer id);

    void edit(Integer[] travelItemIds, TravelGroup travelGroup);

    List<Integer> findTravelItemByTravelGroupId(Integer id);
    //查找所有的跟团游数据
    List<TravelGroup> findAll();
}
