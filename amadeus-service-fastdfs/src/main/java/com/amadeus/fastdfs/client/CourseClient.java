package com.amadeus.fastdfs.client;

import com.amadeus.framework.domain.course.CourseImage;
import com.amadeus.framework.domain.course.CourseVideo;
import com.amadeus.framework.model.response.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "AMADEUS-SERVICE-WEB")
@RequestMapping("/api/course")
public interface CourseClient {

    @PostMapping("/uploadVideo")
    ResponseResult uploadVideo(CourseVideo courseVideo,@RequestParam("courseId") String courseId);

    @PostMapping("/uploadImage")
    ResponseResult uploadImage(CourseImage courseImage,@RequestParam("courseId") String courseId);

}
