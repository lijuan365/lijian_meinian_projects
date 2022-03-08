package com.lijian.service;

import com.lijian.entity.PageResult;
import com.lijian.entity.QueryPageBean;
import com.lijian.pojo.Address;

import java.util.List;

public interface AddressService {
    List<Address> findAllMaps();

    void deleteById(Integer id);

    void addAddress(Address address);

    PageResult findPage(QueryPageBean queryPageBean);
}
