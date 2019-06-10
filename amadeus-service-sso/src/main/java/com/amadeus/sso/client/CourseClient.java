package com.amadeus.sso.client;

import com.amadeus.framework.domain.course.CourseVideo;
import com.amadeus.framework.model.response.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "AMADEUS-SERVICE-WEB")
@RequestMapping("/api/course")
public interface CourseClient {
    @PostMapping("/uploadVideo")
    ResponseResult uploadVideo(@RequestBody CourseVideo courseVideo);
}
