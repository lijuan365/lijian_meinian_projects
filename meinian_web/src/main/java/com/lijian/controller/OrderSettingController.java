package com.lijian.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.lijian.constant.MessageConstant;
import com.lijian.entity.Result;
import com.lijian.pojo.OrderSetting;
import com.lijian.service.OrderSettingService;
import com.lijian.util.POIUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {
    @Reference    //远程调
    private OrderSettingService orderSettingService;

    /**
     * 上传Excel文件的的控制器
     *
     * @param excelFile
     * @return
     */
    @RequestMapping("/upload")
    public Result upload(@RequestParam MultipartFile excelFile) {
        try {
            //使用POI工具对上传的文件进行解析,获取其中的内容
            List<String[]> strings = POIUtils.readExcel(excelFile);
            //将List<String[]> 数据转换成List<OrderSetting>数据
            List<OrderSetting> orderSettings = new ArrayList<>();
            //迭代其中的数据，取出其中的每一行然后，然后封装到集合里面
            for (String[] value : strings) {
                //创建OrderSetting实例，并加入到集合中
                OrderSetting orderSetting = new OrderSetting(new Date(Date.parse(value[0])), Integer.parseInt(value[1]));
                orderSettings.add(orderSetting);
            }
            //将数据保存到后台中
            orderSettingService.add(orderSettings);
            return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }

    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(@RequestParam String date) {  //参数的格式 2019-03
        try {
            List<Map> list = orderSettingService.getOrderSettingByMonth(date);
            return new Result(true, MessageConstant.GET_ORDERSETTING_SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }

    /**
     * 按照日期表编辑预约设置
     * @param orderSetting  要跟新的预约设置
     * @return
     */
    @RequestMapping("/editNumberByDate")
    public Result editOrderSettingByDate(@RequestBody OrderSetting orderSetting){

        try {
            orderSettingService.editNumberByDate(orderSetting);
            //预约设置成功
            return new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            //预约设置失败
            return new Result(false,MessageConstant.ORDERSETTING_FAIL);
        }
    }
}
