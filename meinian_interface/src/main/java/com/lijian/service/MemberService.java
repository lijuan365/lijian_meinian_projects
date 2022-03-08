package com.lijian.service;

import com.lijian.pojo.Member;

import java.util.List;

public interface MemberService {

    Member findByTelephone(String telephone);

    void add(Member member);

    List<Integer> findMemberCountByMonth(List<String> monthsList);
}
