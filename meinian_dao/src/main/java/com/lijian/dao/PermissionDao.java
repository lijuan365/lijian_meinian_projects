package com.lijian.dao;

import com.lijian.pojo.Permission;

import java.util.Set;

public interface PermissionDao {
    Set<Permission> findPermissionsByRoleId(Integer roleId);
}
