package com.lijian.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.lijian.constant.MessageConstant;
import com.lijian.entity.PageResult;
import com.lijian.entity.QueryPageBean;
import com.lijian.entity.Result;
import com.lijian.pojo.TravelItem;
import com.lijian.service.TravelItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController   //相当于@controller+ @RequestBody(添加在方法名称的上面)
@RequestMapping(value = "/travelItem")
public class TravelItemController {
    //远程调用实现的服务
    @Reference//远程调用服务
    private TravelItemService travelItemService;

    @RequestMapping("/findAll")
    public Result findAll() {
        List<TravelItem> list = travelItemService.findAll();
        return new Result(true, MessageConstant.QUERY_TRAVELGROUP_SUCCESS, list);
    }

    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('TRAVELITEM_EDIT')")//权限校验
    public Result edit(@RequestBody TravelItem travelItem) {
        travelItemService.edit(travelItem);
        return new Result(true, MessageConstant.EDIT_TRAVELITEM_SUCCESS);
    }

    //表单数据的回显
    @RequestMapping("/findById")
    public Result findById(Integer id) {
        TravelItem travelItem = travelItemService.findById(id);
        return new Result(true, MessageConstant.QUERY_TRAVELITEM_SUCCESS, travelItem);
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('TRAVELITEM_DELETE')")//权限校验，使用TRAVELITEM_DELETE123测试
    public Result delete(Integer id) {  //如果参数名一致，则不用添加注解，如果参数的名不一致，则需要加上注解@RequestParam("id")
        try {
            travelItemService.delete(id);
            return new Result(true, MessageConstant.DELETE_TRAVELITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_TRAVELITEM_FAIL);
        }
    }

    @RequestMapping("/findPage")
    @PreAuthorize("hasAuthority('TRAVELITEM_QUERY')")//权限校验
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = travelItemService.findPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize(),
                queryPageBean.getQueryString());
        return pageResult;
    }

    //表单项参数名称必须和实体对象的属性名称一致，提供set方法，框架创建对象并封装数据
    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('TRAVELITEM_ADD')")//权限校验
    public Result add(@RequestBody TravelItem travelItem) { //@RequestBody注解是将数据从请求体中取出
        try {
            travelItemService.add(travelItem);
            //返回添加成功的消息
            return new Result(true, MessageConstant.ADD_TRAVELITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            //如果存在异常的，就返回失败的消息
            return new Result(false, MessageConstant.ADD_TRAVELITEM_FAIL);
        }
    }
}
