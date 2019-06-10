package com.amadeus.sso.service.impl;

import com.amadeus.framework.domain.admin.AdminInfo;
import com.amadeus.sso.dao.AdminInfoRepository;
import com.amadeus.sso.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service("adminService")
@CacheConfig(cacheNames = "adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminInfoRepository adminInfoRepository;

    @Override
    public Optional<AdminInfo> findByTelephoneNumber(String telephoneNumber) {
        return adminInfoRepository.findByTelephoneNumber(telephoneNumber);
    }

    @Override
    public AdminInfo save(AdminInfo adminInfo) {
        return adminInfoRepository.save(adminInfo);
    }

}
