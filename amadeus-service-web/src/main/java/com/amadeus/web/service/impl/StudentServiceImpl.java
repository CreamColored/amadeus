package com.amadeus.web.service.impl;

import com.amadeus.framework.domain.course.CourseComment;
import com.amadeus.framework.domain.student.StudentCourse;
import com.amadeus.framework.domain.student.StudentInfo;
import com.amadeus.framework.domain.student.StudentLogin;
import com.amadeus.framework.model.response.*;
import com.amadeus.framework.utils.PageBean;
import com.amadeus.framework.utils.UUIDUtil;
import com.amadeus.web.client.FdfsClient;
import com.amadeus.web.dao.*;
import com.amadeus.web.service.StudentService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("studentService")
@CacheConfig(cacheNames = "studentService")
public class StudentServiceImpl implements StudentService {


    @Autowired
    private StudentLoginRepository studentLoginRepository;

    @Autowired
    private StudentLoginMapper studentLoginMapper;

    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    @Autowired
    private CourseCommentMapper courseCommentMapper;

    @Autowired
    private FdfsClient fdfsClient;

    @Override
    @Transactional
    public QueryResponseResult findAll(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<StudentInfo> studentInfoList = studentInfoMapper.findAll();
        PageBean<StudentInfo> pageBean = new PageBean<>(currentPage, pageSize, studentInfoList.size());
        pageBean.setItems(studentInfoList);
        QueryResult<StudentInfo> queryResult = new QueryResult<>();
        queryResult.setList(pageBean.getItems());
        queryResult.setTotal(pageBean.getTotalNumber());
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    @Override
    public QueryResponseResult findAllStudentLogin() {
        List<StudentLogin> studentLoginList = (List<StudentLogin>) studentLoginRepository.findAll();
        QueryResult<StudentLogin> queryResult = new QueryResult<>();
        queryResult.setList(studentLoginList);
        queryResult.setTotal(studentLoginList.size());
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    @Override
    @Transactional
    public ResponseResult deleteByStudentId(String studentId) {
        int i = studentLoginRepository.deleteByStudentId(studentId);
        if (i != 0) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    @Transactional
    public ResponseResult updateAccountState(String studentId, int accountState) {
        int i = studentLoginMapper.updateAccountState(studentId, accountState);
        if (i != 0) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    @Transactional
    public int getTotalNumber() {
        return studentInfoMapper.getTotalNumber();
    }

    @Override
    public ResponseResult addCourse(String studentId,String courseId) {
        StudentCourse studentCourse = new StudentCourse(new UUIDUtil().generateUUID(), studentId, courseId);
        StudentCourse save = studentCourseRepository.save(studentCourse);
        if (save != null) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    public ResponseResult hasBought(String studentId, String courseId) {
        Optional<StudentCourse> optional = studentCourseRepository.findByStudentIdAndCourseId(studentId, courseId);
        if (optional.isPresent()) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    @Transactional
    public ResponseResult buyCourseByBalance(Double balance, String studentId) {
        int i = studentInfoMapper.updateBalance(balance, studentId);
        if (i != 0) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    @Transactional
    public ResponseResult buyCourseByPoint(Integer point, String studentId) {
        int i = studentInfoMapper.updatePoint(point, studentId);
        if (i != 0) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    @Transactional
    public String uploadImage(MultipartFile file,String studentId) {
        if (!file.isEmpty()) {
            FdfsResult fdfsResult = fdfsClient.uploadFileToFastDFS(file);
            if (fdfsResult.isSuccess()) {
                int i = studentInfoMapper.updateAvatar(fdfsResult.getFilePath(), studentId);
                if (i != 0) {
                    return fdfsResult.getFilePath();
                }
            }
        }
        return null;
    }

    @Override
    @Transactional
    public ResponseResult update(StudentLogin studentLogin) {
        int i = studentLoginMapper.update(studentLogin);
        if (i != 0) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    @Transactional
    public ResponseResult recharge(Double money, String studentId) {
        int i = studentInfoMapper.recharge(money, studentId);
        if (i != 0) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    @Transactional
    public ResponseResult comment(String content, String courseId, String studentId) {
        CourseComment courseComment = new CourseComment();
        courseComment.setCommentId(new UUIDUtil().generateUUID());
        courseComment.setContent(content);
        courseComment.setPublishTime(new Date());
        int i = courseCommentMapper.insertComment(courseComment, courseId, studentId);
        if (i != 0) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

}
