package com.amadeus.web.service;

import com.amadeus.framework.domain.admin.AdminInfo;
import com.amadeus.framework.model.response.QueryResponseResult;
import com.amadeus.framework.model.response.ResponseResult;

public interface AdminService {
    ResponseResult deleteById(String adminId);

    QueryResponseResult findAll();

    ResponseResult updateAccountState(AdminInfo adminInfo);
}
