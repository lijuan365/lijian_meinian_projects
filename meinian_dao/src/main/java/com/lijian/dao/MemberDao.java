package com.lijian.dao;

import com.lijian.pojo.Member;

public interface MemberDao {
     /**
      * 根据手机号查询会员的信息（唯一）
      * @param telephone   会员的手机号
      * @return   会员信息
      */
     Member findByTelephone(String telephone);

     /**
      * 添加会员的信息
      * @param member  会员的信息
      */
     void add(Member member);


    /**
     * 根据注册日期查询每个月注册会员的数量
     * @param regTime  注册的日期
     * @return
     */
    Integer findMemberCountBeforeDate(String regTime);
}
