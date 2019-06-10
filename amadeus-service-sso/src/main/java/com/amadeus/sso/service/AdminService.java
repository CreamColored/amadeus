package com.amadeus.sso.service;

import com.amadeus.framework.domain.admin.AdminInfo;

import java.util.Optional;

public interface AdminService {

    /**
     * 通过邮箱查询管理员信息
     * @return optional
     */
    Optional<AdminInfo> findByTelephoneNumber(String telephoneNumber);

    /**
     * 添加管理员
     * @param adminInfo 管理员信息
     * @return adminInfo
     */
    AdminInfo save(AdminInfo adminInfo);
}
