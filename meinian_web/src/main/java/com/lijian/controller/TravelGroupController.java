package com.lijian.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lijian.constant.MessageConstant;
import com.lijian.entity.PageResult;
import com.lijian.entity.QueryPageBean;
import com.lijian.entity.Result;
import com.lijian.pojo.TravelGroup;
import com.lijian.service.TravelGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/travelGroup")
@RestController
public class TravelGroupController {

    @Reference
    private TravelGroupService travelGroupService;

    @RequestMapping("/add")
    public Result add(Integer[] travelItemIds, @RequestBody TravelGroup travelGroup) {

        try {
            travelGroupService.add(travelGroup, travelItemIds);
            return new Result(true, MessageConstant.ADD_TRAVELGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_TRAVELGROUP_FAIL);
        }
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = travelGroupService.findPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize(), queryPageBean.getQueryString());
        return pageResult;
    }

    //编辑报团游
    @RequestMapping("/findById")
    public Result FindById(Integer id) {
        TravelGroup travelGroup = travelGroupService.findById(id);
        return new Result(true, MessageConstant.QUERY_TRAVELGROUP_SUCCESS, travelGroup);
    }

    //编辑跟团游，（返回 public Result(boolean flag,String message)
    @RequestMapping("/edit")
    public Result edit(Integer[] travelItemIds, @RequestBody TravelGroup travelGroup) {
        travelGroupService.edit(travelItemIds, travelGroup);
        return new Result(true, MessageConstant.EDIT_TRAVELGROUP_SUCCESS);
    }

    //使用自由行id,查询报团游和自由行的中间表，获取自由行的集合，存放id的值
    @RequestMapping("/findTravelItemByTravelGroupId")
    public List<Integer> findTravelItemByTravelGroupId(Integer id) {
        List<Integer> list = travelGroupService.findTravelItemByTravelGroupId(id);
        return list;
    }

    @RequestMapping("/findAll")
    public Result findAll(){
        List<TravelGroup> list = travelGroupService.findAll();
        //查找可能的结果有可能是空的
        if(list !=null && list.size()>0){
            return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,list);
        }
        return new Result(false,MessageConstant.QUERY_SETMEAL_FAIL);
    }

}
