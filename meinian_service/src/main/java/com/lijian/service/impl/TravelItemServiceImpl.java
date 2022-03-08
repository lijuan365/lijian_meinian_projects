package com.lijian.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lijian.dao.TravelItemDao;
import com.lijian.entity.PageResult;
import com.lijian.pojo.TravelItem;
import com.lijian.service.TravelItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = TravelItemService.class)   //将该服务发布到注册中心
@Transactional  //声明式事务，将所有的方法都提添加上事务
public class TravelItemServiceImpl implements TravelItemService {

    @Autowired
    private TravelItemDao travelItemDao;

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        //使用分页插件进行查询,开启分页功能
        //limit(currentPage-1)*pageSize,pageSize
        PageHelper.startPage(currentPage, pageSize);   //底层limit ?,?  两个占位符分别是开始的索引，和每页的条数

        Page page = travelItemDao.findPage(queryString);  //返回当前页的数据

        return new PageResult(page.getTotal(), page.getResult());  //总记录数和分页的集合
    }

    //Ctrl + i  实现接口的方法
    @Override
    public void add(TravelItem travelItem) {
        travelItemDao.add(travelItem);
    }

    @Override
    public void delete(Integer id) {
        travelItemDao.delete(id);
    }

    @Override
    public TravelItem findById(Integer id) {
        return travelItemDao.findById(id);
    }

    @Override
    public void edit(TravelItem travelItem) {
        travelItemDao.edit(travelItem);
    }

    @Override
    public List<TravelItem> findAll() {
        return travelItemDao.findAll();
    }
}
