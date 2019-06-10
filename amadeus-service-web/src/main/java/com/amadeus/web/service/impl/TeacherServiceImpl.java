package com.amadeus.web.service.impl;

import com.amadeus.framework.domain.teacher.TeacherInfo;
import com.amadeus.framework.domain.teacher.TeacherLogin;
import com.amadeus.framework.model.response.CommonCode;
import com.amadeus.framework.model.response.QueryResponseResult;
import com.amadeus.framework.model.response.QueryResult;
import com.amadeus.framework.model.response.ResponseResult;
import com.amadeus.framework.utils.PageBean;
import com.amadeus.web.dao.TeacherInfoMapper;
import com.amadeus.web.dao.TeacherLoginMapper;
import com.amadeus.web.dao.TeacherLoginRepository;
import com.amadeus.web.service.TeacherService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("/teacherService")
@CacheConfig(cacheNames = "teacherService")
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherLoginRepository teacherLoginRepository;

    @Autowired
    private TeacherInfoMapper teacherInfoMapper;

    @Autowired
    private TeacherLoginMapper teacherLoginMapper;

    @Override
    @Transactional
    public QueryResponseResult findAllTeacherInfo(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<TeacherInfo> teacherInfoList = teacherInfoMapper.findAllTeacherInfo();
        PageBean<TeacherInfo> pageBean = new PageBean<>(currentPage, pageSize, teacherInfoList.size());
        pageBean.setItems(teacherInfoList);
        QueryResult<TeacherInfo> queryResult = new QueryResult<>();
        queryResult.setList(pageBean.getItems());
        queryResult.setTotal(pageBean.getItems().size());
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    @Override
    @Transactional
    public QueryResponseResult findAllTeacherLogin(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<TeacherLogin> teacherLoginList = teacherLoginMapper.findAllTeacherLogin();
        PageBean<TeacherLogin> pageBean = new PageBean<>(currentPage, pageSize, teacherLoginList.size());
        pageBean.setItems(teacherLoginList);
        QueryResult<TeacherLogin> queryResult = new QueryResult<>();
        queryResult.setList(pageBean.getItems());
        queryResult.setTotal(pageBean.getTotalNumber());
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    @Override
    @Transactional
    public ResponseResult deleteByTeacherId(String teacherId) {
        int i = teacherLoginRepository.deleteByTeacherId(teacherId);
        if (i != 0) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    @Transactional
    public ResponseResult updateAccountState(String teacherId, int accountState) {
        int i = teacherLoginMapper.updateAccountState(teacherId, accountState);
        if (i != 0) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    @Transactional
    public int getTotalTeacherInfoNumber() {
        return teacherInfoMapper.getTotalNumber();
    }

    @Override
    @Transactional
    public int getTotalTeacherLoginNumber() {
        return teacherLoginMapper.getTotalNumber();
    }
}
