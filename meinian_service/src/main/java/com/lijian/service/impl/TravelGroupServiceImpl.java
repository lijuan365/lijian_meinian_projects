package com.lijian.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lijian.dao.TravelGroupDao;
import com.lijian.entity.PageResult;
import com.lijian.pojo.TravelGroup;
import com.lijian.service.TravelGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = TravelGroupService.class)
@Transactional
public class TravelGroupServiceImpl implements TravelGroupService {


    @Autowired
    private TravelGroupDao travelGroupDao;

    @Override
    public void add(TravelGroup travelGroup, Integer[] travelItemIds) {
        // 1 新增跟团游，想t_travelgroup中添加数据，新增后返回新增的id
        travelGroupDao.add(travelGroup);
        // 2 新增跟团游和自由行中间表t_travelgroup_travelitem新增数据(新增几条，由travelItemIds决定)
        setTravelGroupAndTravelItem(travelGroup.getId(), travelItemIds);
    }

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        //使用分页插件进行分页
        PageHelper.startPage(currentPage, pageSize);
        //使用查询条件从数据库中进行查找
        Page<TravelGroup> page = travelGroupDao.findPage(queryString);

        return new PageResult(page.getTotal(), page.getResult());
    }


    private void setTravelGroupAndTravelItem(Integer travelGroupId, Integer[] travelItemIds) {
        // 新增几条数据，由travelItemIds的长度决定
        if (travelItemIds != null && travelItemIds.length > 0) {
            for (Integer travelItemId : travelItemIds) {
                // 如果有多个参数使用map
                Map<String, Integer> map = new HashMap<>();
                map.put("travelGroup", travelGroupId);
                map.put("travelItem", travelItemId);
                travelGroupDao.setTravelGroupAndTravelItem(map);
            }
        }
    }

    @Override
    public TravelGroup findById(Integer id) {
        TravelGroup travelGroup =  travelGroupDao.findById(id);
        return travelGroup;
    }


    @Override
    public void edit(Integer[] travelItemIds, TravelGroup travelGroup) {
        //修改跟团游的基本信息
        travelGroupDao.edit(travelGroup);
        /**
         * 2:修改跟团游和自由行的中间表（先删除，后创建）
         * 之前的数据
         * 在新增页面选中的数据
         */
        travelGroupDao.deleteTravelGroupAndTravelItemByTravelGroupId(travelGroup.getId());
        //在新增页面中徐那种的数据
        setTravelGroupAndTravelItem(travelGroup.getId(),travelItemIds);
    }

    @Override
    public List<Integer> findTravelItemByTravelGroupId(Integer id) {
        List<Integer> list  =  travelGroupDao.findTravelItemByTravelGroupId(id);
        return list;
    }

    @Override
    public List<TravelGroup> findAll() {
        List<TravelGroup> list = travelGroupDao.findAll();
        return list;
    }
}


