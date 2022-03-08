package com.lijian.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lijian.constant.MessageConstant;
import com.lijian.constant.RedisConstant;
import com.lijian.entity.PageResult;
import com.lijian.entity.QueryPageBean;
import com.lijian.entity.Result;
import com.lijian.pojo.Setmeal;
import com.lijian.service.SetmealService;
import com.lijian.util.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping(value = "/setmeal")
public class SetmealController {
    @Reference
    private SetmealService setmealService;
    @Autowired
    private JedisPool jedisPool;

    // imgFile:需要跟页面el-upload里面的name保持一致
    @RequestMapping("/upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile) {
        try {
            //获取原始文件名
            String originalFilename = imgFile.getOriginalFilename();
            // 找到.最后出现的位置
            int lastIndexOf = originalFilename.lastIndexOf(".");
            //获取文件后缀
            String suffix = originalFilename.substring(lastIndexOf);
            //使用UUID随机产生文件名称，防止同名文件覆盖
            String fileName = UUID.randomUUID().toString() + suffix;
            QiniuUtils.upload2Qiniu(imgFile.getBytes(), fileName);
            //图片上传成功
            Result result = new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, fileName);
            //将上传图片的名称保存到redis数据库中，基于Redis的set集合存储
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES, fileName);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

    //添加套餐游
    @RequestMapping("/add")
    public Result add(Integer[] travelGroupIds, @RequestBody Setmeal setmeal) {
        try {
            setmealService.add(travelGroupIds, setmeal);
        } catch (Exception e) {
            e.printStackTrace();
            //新增套餐失败
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }
        //新增套餐成功
        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    //套餐游的分页
    @RequestMapping("/findPage")
    public PageResult add(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = setmealService.findPage(queryPageBean.getQueryString(), queryPageBean.getPageSize(), queryPageBean.getCurrentPage());
        return pageResult;
    }
}
