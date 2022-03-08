package com.lijian.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lijian.entity.PageResult;
import com.lijian.entity.QueryPageBean;
import com.lijian.entity.Result;
import com.lijian.pojo.Address;
import com.lijian.service.AddressService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/address")
public class AddressController {

    @Reference
    private AddressService addressService;

    @RequestMapping("/findAllMaps")
    public Map findAllMaps() {
        Map map = new HashMap();
        //从后台查询所有的地址信息
        List<Address> addressList = addressService.findAllMaps();
        //定义分店坐标的结合
        List<Map> gridnMaps = new ArrayList<>();
        //定义分店名称的集合
        List<Map> nameMaps = new ArrayList<>();
        for (Address adr : addressList) {
            Map gridnMap = new HashMap();
            gridnMap.put("lng", adr.getLng());
            gridnMap.put("lat", adr.getLat());
            gridnMaps.add(gridnMap);

            HashMap nameMap = new HashMap<>();
            nameMap.put("addressName", adr.getAddressName());
            nameMaps.add(nameMap);
        }
        //存放经纬度
        map.put("gridnMaps", gridnMaps);
        //存放地址名称
        map.put("nameMaps", nameMaps);
        return map;
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = null;
        try {
            pageResult = addressService.findPage(queryPageBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageResult;
    }

    @RequestMapping("/addAddress")
    public Result addAddress(@RequestBody Address address) {
        //System.out.println(address.toString());
        addressService.addAddress(address);
        return new Result(true, "地址保存成功");
    }

    @RequestMapping("/deleteById")
    public Result deleteById(Integer id) {
        addressService.deleteById(id);
        return new Result(true, "已删除地址");
    }

}
