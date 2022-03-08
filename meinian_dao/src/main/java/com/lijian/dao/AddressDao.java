package com.lijian.dao;

import com.github.pagehelper.Page;
import com.lijian.pojo.Address;

import java.util.List;

public interface AddressDao {
    List<Address> findAllMaps();

    void deleteById(Integer id);

    void addAddress(Address address);

    Page <Address> findPage(String queryString);
}
