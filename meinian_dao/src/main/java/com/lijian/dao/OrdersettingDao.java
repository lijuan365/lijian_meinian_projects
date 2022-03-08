package com.lijian.dao;

import com.lijian.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrdersettingDao {
    /**
     * 添加预约设置的数量
     * @param orderSetting  预约设置
     */
    void add(OrderSetting orderSetting);

    /**
     * 编辑预约设置的数量
     * @param orderSetting  预约设置
     */
    void EditOrderNumberByOrderDate(OrderSetting orderSetting);

    /**
     * 根据传入日期查询当期传入日期下的预约数目
     * @param orderDate   预约时间
     * @return number 当前日期下预约的数目
     */
    Long findCountByOrderDate(Date orderDate);

    List<OrderSetting> getOrderSettingByMonth(Map<String, String> map);

    void editNumberByDate(OrderSetting orderSetting);

    /**
     * 根据预约日期查询预约设置信息
     * @param date 预约的日期
     * @return   预约设置
     */
    OrderSetting findByOrderDate(Date date);

    /**
     * 更新已经预约的人数
     * @param orderSetting
     */
    void editReservationsByOrderDate(OrderSetting orderSetting);
}
