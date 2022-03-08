package com.lijian.dao;

import com.github.pagehelper.Page;
import com.lijian.pojo.TravelGroup;

import java.util.List;
import java.util.Map;

public interface TravelGroupDao {
    void add(TravelGroup travelGroup);

    void setTravelGroupAndTravelItem(Map<String, Integer> map);

    Page<TravelGroup> findPage(String queryString);

    TravelGroup findById(Integer id);

    void edit(TravelGroup travelGroup);

    void deleteTravelGroupAndTravelItemByTravelGroupId(Integer id);

    List<Integer> findTravelItemByTravelGroupId(Integer id);

    List<TravelGroup> findAll();

    /**
     * 根据id查询多个跟团游组的信息
     * @param id
     * @return
     */
    List<TravelGroup> findTravelGroupListById(Integer id);
}
