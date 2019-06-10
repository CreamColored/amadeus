package com.amadeus.web.controller;


import com.amadeus.framework.domain.admin.AdminInfo;
import com.amadeus.framework.model.response.QueryResponseResult;
import com.amadeus.framework.model.response.ResponseResult;
import com.amadeus.web.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@Api(value = "管理员管理", tags = "管理员管理")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "查询全部管理员信息",notes = "返回值为QueryResponseResult")
    @GetMapping("/findAll")
    public QueryResponseResult findAll() {
        return adminService.findAll();
    }

    @ApiOperation(value = "删除管理员信息",notes = "参数为adminInfo，返回值为ResponseResult，级联删除所管理的院校信息")
    @DeleteMapping("/delete/{adminId}")
    public ResponseResult delete(@PathVariable("adminId") String adminId) {
        return adminService.deleteById(adminId);
    }

    @ApiOperation(value = "修改管理员状态", notes = "参数为AdminInfo，返回值为ResponseResult")
    @PutMapping("/updateAccountState")
    public ResponseResult updateAccountState(AdminInfo adminInfo) {
        return adminService.updateAccountState(adminInfo);
    }
}
