package com.amadeus.web.service;

import com.amadeus.framework.domain.college.CollegeInfo;
import com.amadeus.framework.model.response.QueryResponseResult;
import com.amadeus.framework.model.response.ResponseResult;

public interface CollegeService {

    /**
     * 查询全部院校信息
     *
     * @return QueryResponseResult
     */
    QueryResponseResult findAll();

    /**
     * 通过id获取院校信息
     *
     * @param collegeId 院校id
     * @return CollegeInfo
     */
    CollegeInfo findById(String collegeId);

    /**
     * 添加院校
     *
     * @param collegeInfo 院校信息
     * @return ResponseResult
     */
    ResponseResult save(CollegeInfo collegeInfo);

    /**
     * 更新院校状态
     * @param collegeInfo 院校信息
     * @return ResponseResult
     */
    ResponseResult updateState(CollegeInfo collegeInfo);

    /**
     * 删除院校
     * @param collegeId 院校id
     * @return ResponseResult
     */
    ResponseResult delete(String collegeId);

    /**
     * 更新院校信息
     * @param collegeInfo 院校信息
     * @return ResponseResult
     */
    ResponseResult update(CollegeInfo collegeInfo);

}
