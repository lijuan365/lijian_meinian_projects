package com.lijian.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lijian.dao.OrdersettingDao;
import com.lijian.pojo.OrderSetting;
import com.lijian.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    private OrdersettingDao orderSettingDao;

    @Override
    public void add(List<OrderSetting> orderSettings) {
        //遍历orderSettings,取出其中的数据
        for (OrderSetting orderSetting : orderSettings) {
            //判断当前的日期之前是否已经被设置过预约日期，使用当前时间作为条件查询数量
            Long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
            //如果设置过预约的日期，那就更新预约的数量
            if (count > 0) {
                orderSettingDao.EditOrderNumberByOrderDate(orderSetting);
            } else {
                orderSettingDao.add(orderSetting);
            }
        }
    }

    /**
     * 传递的参数
     * date（格式：2019-8）
     * 构建的数据List<Map>
     * map.put("date",1);
     * map.put("number",120);
     * map.put("reservations",10);
     * <p>
     * 查询方案：SELECT * FROM t_ordersetting WHERE orderDate LIKE '2019-08-%'
     * 查询方案：SELECT * FROM t_ordersetting WHERE orderDate BETWEEN '2019-9-1' AND '2019-9-31'
     */
    //根据日期查询预约设置数据
    public List<Map> getOrderSettingByMonth(String date) {//2019-3
        //1.查询条件从1-31号查询数据OrderSetting
        String dateBegin = date + "-" + "1";
        String dateEnd = date + "-" + "31";
        Map<String, String> map = new HashMap<>();
        map.put("dateBegin", dateBegin);
        map.put("dateEnd", dateEnd);
        //查询当月份的预约设置
        List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(map);
        // 将获取到的OrderSetting集合封装成Map返回
        List<Map> data = new ArrayList<>();
        for (OrderSetting orderSetting : list) {
            Map<String, Object> orderSettingMap = new HashMap<String, Object>();
            orderSettingMap.put("date", orderSetting.getOrderDate().getDate());   //日期
            orderSettingMap.put("number", orderSetting.getNumber());
            orderSettingMap.put("reservations", orderSetting.getReservations());
            data.add(orderSettingMap);
        }
        return data;
    }

    /**
     * 根据日期修改可以预约人数
     *
     * @param orderSetting 预约设置
     */
    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
        if (count > 0) {
            //当前的日期已经进行了预约设置
            orderSettingDao.editNumberByDate(orderSetting);
        } else {
            //当前的日期还没有进行预约设置，进行添加操作
            orderSettingDao.add(orderSetting);
        }
    }
}



