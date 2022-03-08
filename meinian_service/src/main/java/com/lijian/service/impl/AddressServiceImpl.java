package com.lijian.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lijian.dao.AddressDao;
import com.lijian.entity.PageResult;
import com.lijian.entity.QueryPageBean;
import com.lijian.pojo.Address;
import com.lijian.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass= AddressService.class)
@Transactional
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressDao addressDao;

    /**
     * 获取所有分店的地址
     * @return   返回所有分店地址集合
     */
    @Override
    public List<Address> findAllMaps() {

        return addressDao.findAllMaps();
    }

    /**
     * 根据id删除分店的地址
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        addressDao.deleteById(id);

    }

    /**
     * 添加分店的地址
     * @param address  分店的地址
     */
    @Override
    public void addAddress(Address address) {
         addressDao.addAddress(address);
    }


    /**
     * 查询分页的信息
     * @param queryPageBean  分页起始位置 每页的记录数 分页的条件
     * @return   返回分页的结果
     */
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //使用mybatis提供的分页插件开始进行分页
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<Address>page =  addressDao.findPage(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(),page.getResult());
    }
}
