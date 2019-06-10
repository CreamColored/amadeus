package com.amadeus.web.service.impl;

import com.amadeus.framework.domain.college.CollegeInfo;
import com.amadeus.framework.model.response.CommonCode;
import com.amadeus.framework.model.response.QueryResponseResult;
import com.amadeus.framework.model.response.QueryResult;
import com.amadeus.framework.model.response.ResponseResult;
import com.amadeus.framework.utils.UUIDUtil;
import com.amadeus.web.dao.CollegeMapper;
import com.amadeus.web.dao.CollegeRepository;
import com.amadeus.web.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("collegeService")
@CacheConfig(cacheNames = "studentService")
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private CollegeMapper collegeMapper;

    @Override
    public QueryResponseResult findAll() {
        List<CollegeInfo> collegeInfoList = (List<CollegeInfo>) collegeRepository.findAll();
        QueryResult<CollegeInfo> queryResult = new QueryResult<>();
        queryResult.setList(collegeInfoList);
        queryResult.setTotal(collegeInfoList.size());
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    @Override
    public CollegeInfo findById(String collegeId) {
        Optional<CollegeInfo> optional = collegeRepository.findById(collegeId);
        return optional.orElse(null);
    }

    @Override
    public ResponseResult save(CollegeInfo collegeInfo) {
        collegeInfo.setCollegeId(new UUIDUtil().generateUUID());
        collegeInfo.setCollegeState(1);
        CollegeInfo save = collegeRepository.save(collegeInfo);
        if (save != null) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    @Transactional
    public ResponseResult updateState(CollegeInfo collegeInfo) {
        int i = collegeMapper.updateState(collegeInfo);
        if (i != 0) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    @Transactional
    public ResponseResult delete(String collegeId) {
        int i = collegeMapper.deleteByCollegeId(collegeId);
        if (i != 0) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    @Transactional
    public ResponseResult update(CollegeInfo collegeInfo) {
        int i = collegeMapper.update(collegeInfo);
        if (i != 0) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

}
