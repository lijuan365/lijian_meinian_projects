package com.lijian.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lijian.constant.MessageConstant;
import com.lijian.entity.Result;
import com.lijian.service.MemberService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Reference
    private MemberService memberService;

    /**
     * 会员数量统计
     * @return
     */
    @RequestMapping("/getMemberReport")
    public Result getMemberReport() {
        // 获取日历对象
        Calendar calendar = Calendar.getInstance();
        //根据当前时间，获取前12个月的日历(当前日历2020-02，12个月前，日历时间2019-03)
        //第一个参数，日历字段
        //第二个参数，要添加到字段中的日期或时间
        calendar.add(Calendar.MONTH,-36);

        List<String> list = new ArrayList<String>();
        for(int i=0;i<12;i++){
            //第一个参数是月份 2018-7
            //第二个参数是月份+1个月
            calendar.add(Calendar.MONTH,1);
            list.add(new SimpleDateFormat("yyyy-MM").format(calendar.getTime()));
        }

        Map<String,Object> map = new HashMap<String,Object>();
        // 把过去12个月的日期存储到map里面
        map.put("months",list);
        // 查询所有的会员
        List<Integer> memberCount = memberService.findMemberCountByMonth(list);
        map.put("memberCount",memberCount);

        return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS,map);
    }
}
