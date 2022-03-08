package com.lijian.dao;

import com.lijian.pojo.Role;

import java.util.Set;

public interface RoleDao {
    Set<Role> findRolesByUserId(Integer userId);
}
