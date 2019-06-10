package com.amadeus.web.service.impl;

import com.amadeus.framework.domain.course.CourseComment;
import com.amadeus.framework.domain.course.CourseImage;
import com.amadeus.framework.domain.course.CourseInfo;
import com.amadeus.framework.domain.course.CourseVideo;
import com.amadeus.framework.model.response.*;
import com.amadeus.framework.utils.PageBean;
import com.amadeus.framework.utils.UUIDUtil;
import com.amadeus.web.dao.*;
import com.amadeus.web.service.CourseService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("courseService")
@CacheConfig(cacheNames = "courseService")

public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseInfoMapper courseInfoMapper;

    @Autowired
    private CourseImageMapper courseImageMapper;

    @Autowired
    private CourseVideoMapper courseVideoMapper;

    @Autowired
    private CourseCommentMapper courseCommentMapper;

    @Autowired
    private CourseInfoRepository courseInfoRepository;

    @Autowired
    private CourseImageRepository courseImageRepository;

    @Override
    public ResponseResult insert(CourseInfo courseInfo) {
        courseInfo.setCourseId(new UUIDUtil().generateUUID());
        courseInfo.setCourseState(1);
        courseInfo.setReleaseDate(new Date());
        CourseInfo save = courseInfoRepository.save(courseInfo);
        if (save != null) {
            CourseImage courseImage = new CourseImage();
            courseImage.setImageId(new UUIDUtil().generateUUID());
            courseImage.setCourseInfo(save);
            CourseImage save1 = courseImageRepository.save(courseImage);
            if (save1 != null) {
                return new ResponseResult(CommonCode.SUCCESS);
            }
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    public QueryResponseResult findHotCourses(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<CourseInfo> hotCourses = courseInfoMapper.findHotCourses();
        if (hotCourses.size() != 0) {
            PageBean<CourseInfo> pageBean = new PageBean<>(currentPage, pageSize, hotCourses.size());
            pageBean.setItems(hotCourses);
            QueryResult<CourseInfo> queryResult = new QueryResult<>();
            queryResult.setList(pageBean.getItems());
            queryResult.setTotal(pageBean.getTotalNumber());
            return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        }
        return new QueryResponseResult(CommonCode.FAIL, null);
    }

    @Override
    @Transactional
    public QueryResponseResult findBoutiqueCourses(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<CourseInfo> hotCourses = courseInfoMapper.findBoutiqueCourses();
        if (hotCourses.size() != 0) {
            PageBean<CourseInfo> pageBean = new PageBean<>(currentPage, pageSize, hotCourses.size());
            pageBean.setItems(hotCourses);
            QueryResult<CourseInfo> queryResult = new QueryResult<>();
            queryResult.setList(pageBean.getItems());
            queryResult.setTotal(pageBean.getTotalNumber());
            return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        }
        return new QueryResponseResult(CommonCode.FAIL, null);
    }

    @Override
    public QueryResponseResult findAllCourseInfo(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<CourseInfo> courseInfoList = courseInfoMapper.findAllCourseInfo();
        PageBean<CourseInfo> pageBean = new PageBean<>(currentPage, pageSize, courseInfoList.size());
        pageBean.setItems(courseInfoList);
        QueryResult<CourseInfo> queryResult = new QueryResult<>();
        queryResult.setList(pageBean.getItems());
        queryResult.setTotal(pageBean.getTotalNumber());
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    @Override
    public int getTotalNumber() {
        return courseInfoMapper.getTotalNumber();
    }

    @Override
    public ResponseResult updateCourseState(String courseId, int courseState) {
        int i = courseInfoMapper.updateCourseState(courseId, courseState);
        if (i != 0) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    public ResponseResult deleteCourse(String courseId) {
        int i = courseInfoMapper.deleteCourse(courseId);
        if (i != 0) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    @Transactional
    public ResponseResult uploadVideo(CourseVideo courseVideo, String courseId) {
        CourseInfo courseInfo = new CourseInfo();
        courseInfo.setCourseId(courseId);
        courseVideo.setCourseInfo(courseInfo);
        int i = courseVideoMapper.insert(courseVideo);
        if (i != 0) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    @Transactional
    public ResponseResult uploadImage(CourseImage courseImage, String courseId) {
        CourseInfo courseInfo = new CourseInfo();
        courseInfo.setCourseId(courseId);
        courseImage.setCourseInfo(courseInfo);
        int i = courseImageMapper.insert(courseImage);
        if (i != 0) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    @Transactional
    public QueryResponseResult findCourseByCourseId(String courseId) {
        List<CourseInfo> course = courseInfoMapper.findCourseByCourseId(courseId);
        if (course.size() != 0) {
            QueryResult<CourseInfo> queryResult = new QueryResult<>();
            queryResult.setList(course);
            queryResult.setTotal(course.size());
            return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        }
        return new QueryResponseResult(CommonCode.FAIL, null);
    }

    @Override
    @Transactional
    public QueryResponseResult findCommentByCourseId(String courseId) {
        List<CourseComment> courseCommentList = courseCommentMapper.findCommentByCourseId(courseId);
        if (courseCommentList.size() != 0) {
            QueryResult<CourseComment> queryResult = new QueryResult<>();
            queryResult.setList(courseCommentList);
            queryResult.setTotal(courseCommentList.size());
            return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        }
        return new QueryResponseResult(CommonCode.FAIL, null);
    }

    @Override
    @Transactional
    public QueryResponseResult findCoursesByStudentId(String studentId, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<CourseInfo> courses = courseInfoMapper.findCoursesByStudentId(studentId);
        if (courses.size() != 0) {
            PageBean<CourseInfo> pageBean = new PageBean<>(currentPage, pageSize, courses.size());
            pageBean.setItems(courses);
            QueryResult<CourseInfo> queryResult = new QueryResult<>();
            queryResult.setList(pageBean.getItems());
            queryResult.setTotal(pageBean.getTotalNumber());
            return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        }
        return new QueryResponseResult(CommonCode.FAIL, null);
    }
}
