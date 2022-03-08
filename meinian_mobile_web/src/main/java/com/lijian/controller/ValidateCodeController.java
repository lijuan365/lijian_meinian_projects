package com.lijian.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lijian.constant.MessageConstant;
import com.lijian.constant.RedisConstant;
import com.lijian.constant.RedisMessageConstant;
import com.lijian.entity.Result;
import com.lijian.service.ValidateCodeService;
import com.lijian.util.SMSUtils;
import com.lijian.util.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {
    @Reference
    private ValidateCodeService validateCodeService;
    @Autowired
    private JedisPool jedisPool;

    /**
     * 用户填写订单时发送验证码
     * @param telephone   用户的手机号码
     * @return   查询的结果
     */
    @RequestMapping("/send4Order")
    public Result send4Order(@RequestParam String telephone){
        //生成四位数的验证码
        Integer code  = ValidateCodeUtils.generateValidateCode(4);
        try {
            //发送短信
            SMSUtils.sendShortMessage(telephone,code.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        //发送的手机验证码为
        System.out.println("发送的手机验证码为:"+code);
        //将生成的验证码缓存到数据库中
        jedisPool.getResource().setex(telephone+ RedisMessageConstant.SENDTYPE_ORDER,5*60,code.toString());
        //返回成功的消息
        return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }

    /**
     * 用户登录时发送验证码
     * @param telephone   用户提交的手机号码
     * @return
     */
    @RequestMapping("/send4Login")
    public Result send4Login(@RequestParam String telephone){
        //产生四位数的验证码
        Integer code  = ValidateCodeUtils.generateValidateCode(4);
        //向客户端发送短信
        try {
            SMSUtils.sendShortMessage(telephone,code.toString());

        } catch (Exception e) {
            e.printStackTrace();
            //如果验证码发送失败
            return new Result(false,MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        System.out.println("发送的手机验证码为:"+code);
        //将新生成的验证码存放到Redis中
        jedisPool.getResource().setex(telephone+RedisMessageConstant.SENDTYPE_LOGIN,5*60,code.toString());
        //返回成功的结果
        return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }



}
