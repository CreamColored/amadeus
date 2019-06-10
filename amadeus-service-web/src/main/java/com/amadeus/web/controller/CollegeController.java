package com.amadeus.web.controller;

import com.amadeus.framework.domain.college.CollegeInfo;
import com.amadeus.framework.model.response.QueryResponseResult;
import com.amadeus.framework.model.response.ResponseResult;
import com.amadeus.web.service.CollegeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/college")
@Api(value = "院校管理",tags = "院校管理")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    @ApiOperation(value = "根据id查询院校信息", notes = "参数为collegeId，返回值为CollegeInfo")
    @GetMapping("/findById/{collegeId}")
    public CollegeInfo findById(@PathVariable("collegeId") String collegeId) {
        return collegeService.findById(collegeId);
    }

    @ApiOperation(value = "添加院校", notes = "参数为collegeInfo，返回值为ResponseResult")
    @PostMapping("/save")
    public ResponseResult save(@ModelAttribute("collegeInfo") CollegeInfo collegeInfo) {
        return collegeService.save(collegeInfo);
    }

    @ApiOperation(value = "查询全部院校信息",notes = "返回值为QueryResponseResult")
    @GetMapping("/findAll")
    public QueryResponseResult findAll() {
        return collegeService.findAll();
    }

    @ApiOperation(value = "更新院校状态",notes = "参数为院校id跟院校状态，返回值为ResponseResult")
    @PutMapping("/updateState")
    public ResponseResult updateState(CollegeInfo collegeInfo) {
        return collegeService.updateState(collegeInfo);
    }

    @ApiOperation(value = "根据院校id删除", notes = "参数为院校id，返回值为ResponseResult")
    @DeleteMapping("/delete/{collegeId}")
    public ResponseResult delete(@PathVariable("collegeId") String collegeId) {
        return collegeService.delete(collegeId);
    }

    @ApiOperation(value = "更新院校信息",notes = "参数为院校信息，返回值为ResponseResult")
    @PutMapping("/update")
    public ResponseResult update(@ModelAttribute("collegeInfo") CollegeInfo collegeInfo) {
        System.out.println(collegeInfo);
        return collegeService.update(collegeInfo);
    }
}
