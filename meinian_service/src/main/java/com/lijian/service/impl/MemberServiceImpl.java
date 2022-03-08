package com.lijian.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.lijian.dao.MemberDao;
import com.lijian.pojo.Member;
import com.lijian.service.MemberService;
import com.lijian.util.DateUtils;
import com.lijian.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import sun.security.provider.MD5;

import java.util.ArrayList;
import java.util.List;

@Service(interfaceClass  = MemberService.class)
@Transactional
public class MemberServiceImpl  implements MemberService {
    @Autowired
    private MemberDao memberDao;

    //Ctrl + U是回退到上一个页面
    @Override
    public Member findByTelephone(String telephone) {
        Member member = memberDao.findByTelephone(telephone);
        return member;
    }

    /**
     * 完成用户注册
     * @param member  用户的注册信息：手机号码+用户的注册时间
     */
    @Override
    public void add(Member member) {
        if(member.getPassword()!=null){
            member.setPassword(MD5Utils.md5(member.getPassword()));
        }
        memberDao.add(member);
    }

    /**
     * 根据月份查找会员数量
     * @param monthsList  月份的集合
     * @return
     */
    // 根据月份统计会员数量
    public List<Integer> findMemberCountByMonth(List<String> monthsList) {
        List<Integer> memeberCountList = new ArrayList<Integer>();
        if(monthsList!=null && monthsList.size()>0){
            for (String months : monthsList) {
                //String regTime = months+"-31";
                // 获取指定月份的最后一天
                String regTime =  DateUtils.getLastDayOfMonth(months);
                //  迭代过去12个月，每个月注册会员的数量，根据注册日期查询
                Integer memeberCount = memberDao.findMemberCountBeforeDate(regTime);
                memeberCountList.add(memeberCount);
            }
        }
        return memeberCountList;
    }

}
