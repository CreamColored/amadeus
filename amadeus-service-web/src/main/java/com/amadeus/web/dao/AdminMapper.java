package com.amadeus.web.dao;

import com.amadeus.framework.domain.admin.AdminInfo;
import org.springframework.stereotype.Component;

@Component
public interface AdminMapper {
    int deleteById(String adminId);

    int updateAccountState(AdminInfo adminInfo);
}
