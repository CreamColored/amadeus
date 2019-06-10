package com.amadeus.web.service.impl;

import com.amadeus.framework.domain.admin.AdminInfo;
import com.amadeus.framework.model.response.CommonCode;
import com.amadeus.framework.model.response.QueryResponseResult;
import com.amadeus.framework.model.response.QueryResult;
import com.amadeus.framework.model.response.ResponseResult;
import com.amadeus.web.dao.AdminMapper;
import com.amadeus.web.dao.AdminRepository;
import com.amadeus.web.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminMapper adminMapper;

    @Override
    @Transactional
    public ResponseResult deleteById(String adminId) {
        int i = adminMapper.deleteById(adminId);
        if (i != 0) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    public QueryResponseResult findAll() {
        List<AdminInfo> adminInfoList = (List<AdminInfo>) adminRepository.findAll();
        QueryResult<AdminInfo> queryResult = new QueryResult<>();
        queryResult.setList(adminInfoList);
        queryResult.setTotal(adminInfoList.size());
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    @Override
    @Transactional
    public ResponseResult updateAccountState(AdminInfo adminInfo) {
        if (adminMapper.updateAccountState(adminInfo) != 0) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

}
