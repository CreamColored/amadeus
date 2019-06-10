package com.amadeus.web.controller;

import com.amadeus.framework.domain.course.CourseImage;
import com.amadeus.framework.domain.course.CourseInfo;
import com.amadeus.framework.domain.course.CourseVideo;
import com.amadeus.framework.model.response.QueryResponseResult;
import com.amadeus.framework.model.response.ResponseResult;
import com.amadeus.web.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course")
@Api(value = "课程管理", tags = "课程管理")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "添加课程", notes = "参数为courseInfo，返回值为ResponseResult")
    @PostMapping("/insert")
    public ResponseResult insert(@ModelAttribute("courseInfo") CourseInfo courseInfo) {
        return courseService.insert(courseInfo);
    }

    @ApiOperation(value = "分页查询热门课程信息", notes = "分页查询")
    @GetMapping("/findHotCourses/{currentPage}/{pageSize}")
    public QueryResponseResult findHotCourses(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize) {
        return courseService.findHotCourses(currentPage, pageSize);
    }

    @ApiOperation(value = "分页查询精品课程信息", notes = "分页查询")
    @GetMapping("/findBoutiqueCourses/{currentPage}/{pageSize}")
    public QueryResponseResult findBoutiqueCourses(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize) {
        return courseService.findBoutiqueCourses(currentPage, pageSize);
    }

    @ApiOperation(value = "分页查询课程信息", notes = "分页查询")
    @GetMapping("/findAllCourseInfo/{currentPage}/{pageSize}")
    public QueryResponseResult findAllCourseInfo(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize) {
        return courseService.findAllCourseInfo(currentPage, pageSize);
    }

    @ApiOperation(value = "查询课程信息表总数据量", notes = "查询总数据量")
    @GetMapping("/getTotalNumber")
    public int getTotalNumber() {
        return courseService.getTotalNumber();
    }

    @ApiOperation(value = "修改课程状态", notes = "参数为CourseInfo，返回值为ResponseResult")
    @PutMapping("/updateCourseState")
    public ResponseResult updateCourseState(CourseInfo courseInfo) {
        return courseService.updateCourseState(courseInfo.getCourseId(), courseInfo.getCourseState());
    }

    @ApiOperation(value = "删除课程信息", notes = "参数为courseId，返回值为ResponseResult")
    @DeleteMapping("/delete/{courseId}")
    public ResponseResult delete(@PathVariable("courseId") String courseId) {
        return courseService.deleteCourse(courseId);
    }

    @ApiOperation(value = "上传课程视频", notes = "参数为courseVideo，返回值为ResponseResult")
    @PostMapping("/uploadVideo")
    public ResponseResult uploadVideo(@RequestBody CourseVideo courseVideo, @RequestParam("courseId") String courseId) {
        return courseService.uploadVideo(courseVideo, courseId);
    }

    @ApiOperation(value = "上传课程图片", notes = "参数为courseImage，返回值为ResponseResult")
    @PostMapping("/uploadImage")
    public ResponseResult uploadImage(@RequestBody CourseImage courseImage, @RequestParam("courseId") String courseId) {
        return courseService.uploadImage(courseImage, courseId);
    }

    @ApiOperation(value = "根据courseId查询课程全部信息", notes = "参数为courseId，返回值为QueryResponseResult")
    @GetMapping("/findCourseByCourseId/{courseId}")
    public QueryResponseResult findCourseByCourseId(@PathVariable("courseId") String courseId) {
        return courseService.findCourseByCourseId(courseId);
    }

    @ApiOperation(value = "根据courseId查询该课程评论", notes = "参数为courseId，返回值为QueryResponseResult")
    @GetMapping("/findCommentByCourseId/{courseId}")
    public QueryResponseResult findCommentByCourseId(@PathVariable("courseId") String courseId) {
        return courseService.findCommentByCourseId(courseId);
    }

    @ApiOperation(value = "根据studentId查询所选课程", notes = "参数为studentId，返回值为QueryResponseResult")
    @GetMapping("/findCoursesByStudentId/{studentId}/{currentPage}/{pageSize}")
    public QueryResponseResult findCoursesByStudentId(@PathVariable("studentId") String studentId, @PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize) {
        return courseService.findCoursesByStudentId(studentId, currentPage, pageSize);
    }
}
